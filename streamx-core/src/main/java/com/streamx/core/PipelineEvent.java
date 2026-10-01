package com.streamx.core;

public record PipelineEvent(String name, long sequence, long timestampNanos) {}
