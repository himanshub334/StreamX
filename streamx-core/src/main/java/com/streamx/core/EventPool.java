package com.streamx.core;

import java.util.ArrayDeque;
import java.util.Deque;

/** Thread-local object reuse for the hot path; bounded to avoid unbounded retention. */
public final class EventPool {
    private final ThreadLocal<Deque<Event>> local = ThreadLocal.withInitial(ArrayDeque::new);
    private final int capacity;
    public EventPool(int capacity){ this.capacity = capacity; }
    public Event acquire(){
        Event e = local.get().pollFirst();
        return e != null ? e : new Event();
    }
    public void release(Event e){
        Deque<Event> q = local.get();
        if(q.size() < capacity) q.offerFirst(e);
    }
}
