package com.streamx.core;

public final class Event {
    private long sequence;
    private String type;
    private long createdAtNanos;
    private boolean processed;

    public Event reset(long sequence, String type) {
        this.sequence = sequence;
        this.type = type;
        this.createdAtNanos = System.nanoTime();
        this.processed = false;
        return this;
    }
    public long sequence(){ return sequence; }
    public String type(){ return type; }
    public long createdAtNanos(){ return createdAtNanos; }
    public boolean processed(){ return processed; }
    public void markProcessed(){ processed = true; }
}
