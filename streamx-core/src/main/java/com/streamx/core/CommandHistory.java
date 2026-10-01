package com.streamx.core;

import java.util.ArrayDeque;
import java.util.Deque;

public final class CommandHistory {
    private final Deque<Command> undo = new ArrayDeque<>();
    private final Deque<Command> redo = new ArrayDeque<>();
    public void execute(Command c){ c.execute(); undo.push(c); redo.clear(); }
    public void undo(){ if(!undo.isEmpty()){ Command c=undo.pop(); c.undo(); redo.push(c); } }
    public void redo(){ if(!redo.isEmpty()){ Command c=redo.pop(); c.execute(); undo.push(c); } }
}
