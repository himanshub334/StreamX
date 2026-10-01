package com.streamx.demo;

import com.streamx.core.*;

public class StreamXDemo {
  public static void main(String[] args) throws Exception {
    Pipeline<String,Integer> p = new Pipeline<>();
    p.addObserver(e -> System.out.printf("%s #%d%n", e.name(), e.sequence()));
    p.addStage(String::trim).addStage(String::length);
    System.out.println("length=" + p.process(" streamx "));
    CommandHistory history = new CommandHistory();
    history.execute(new SetConcurrencyCommand(p, 4));
    System.out.println("concurrency=" + p.concurrency());
    history.undo();
    System.out.println("after undo=" + p.concurrency());
  }
}
