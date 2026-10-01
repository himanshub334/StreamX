package com.streamx.core;

@FunctionalInterface
public interface Stage<T, R> {
    R process(T input) throws Exception;
}
