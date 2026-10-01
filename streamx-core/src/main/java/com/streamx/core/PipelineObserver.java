package com.streamx.core;

@FunctionalInterface
public interface PipelineObserver {
    void onEvent(PipelineEvent event);
}
