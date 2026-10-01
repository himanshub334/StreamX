package com.streamx.core;

import org.junit.jupiter.api.Test;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.jupiter.api.Assertions.*;

class PipelineTest {
 @Test void strategiesCompose(){
   Pipeline<String,Integer> p=new Pipeline<>();
   p.addStage(String::trim).addStage(String::length);
   assertEquals(4,p.processUnchecked(" test "));
 }
 @Test void observerReceivesLifecycle(){
   Pipeline<String,String> p=new Pipeline<>(); AtomicInteger count=new AtomicInteger();
   p.addObserver(e -> count.incrementAndGet()); p.addStage(String::toUpperCase);
   p.processUnchecked("x"); assertEquals(2,count.get());
 }
 @Test void commandUndoRedo(){
   Pipeline<String,String> p=new Pipeline<>(); CommandHistory h=new CommandHistory();
   h.execute(new SetConcurrencyCommand(p,8)); assertEquals(8,p.concurrency()); h.undo(); assertEquals(1,p.concurrency()); h.redo(); assertEquals(8,p.concurrency());
 }
}
