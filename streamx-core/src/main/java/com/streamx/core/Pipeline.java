package com.streamx.core;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

/** Composable generic pipeline. Stages are Strategy objects and can be independently tested. */
public final class Pipeline<T,R> {
    private final List<Stage<?,?>> stages = new CopyOnWriteArrayList<>();
    private final List<PipelineObserver> observers = new CopyOnWriteArrayList<>();
    private final AtomicLong sequence = new AtomicLong();
    private volatile int concurrency = 1;

    public <A,B> Pipeline<T,R> addStage(Stage<A,B> stage){ stages.add(stage); return this; }
    public Pipeline<T,R> addObserver(PipelineObserver observer){ observers.add(observer); return this; }
    public int concurrency(){ return concurrency; }
    public void setConcurrency(int value){ if(value < 1) throw new IllegalArgumentException("concurrency must be >= 1"); concurrency=value; }

    @SuppressWarnings("unchecked")
    public R process(T input) throws Exception {
        long seq=sequence.incrementAndGet(); Object value=input;
        emit(new PipelineEvent("START",seq,System.nanoTime()));
        for(Stage<?,?> raw : stages) value=((Stage<Object,Object>)raw).process(value);
        emit(new PipelineEvent("COMPLETE",seq,System.nanoTime()));
        return (R)value;
    }
    public void subscribe(Consumer<PipelineEvent> consumer){ addObserver(consumer::accept); }
    public R processUnchecked(T input){ try { return process(input); } catch(Exception e){ throw new RuntimeException(e); } }
    private void emit(PipelineEvent e){ observers.forEach(o -> o.onEvent(e)); }
}
