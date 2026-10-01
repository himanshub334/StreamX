package com.streamx.core;

public final class SetConcurrencyCommand implements Command {
    private final Pipeline<?,?> pipeline; private final int next; private int previous;
    public SetConcurrencyCommand(Pipeline<?,?> pipeline, int next){ this.pipeline=pipeline; this.next=next; }
    public void execute(){ previous=pipeline.concurrency(); pipeline.setConcurrency(next); }
    public void undo(){ pipeline.setConcurrency(previous); }
}
