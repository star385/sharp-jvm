package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.ClassInfo;
import com.sharpjvm.bytecode.bean.MethodInfo;
import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.interpreter.ExecuteException;

import java.util.List;

/**
 * chain of command, when one command executed, auto find next command and execute it.
 *
 * User: zhuguoyin
 * Date: 13-3-16
 * Time: ÉÏÎç8:40
 * To change this template use File | Settings | File Templates.
 */
public class CommandChain {

    private List<Command> commandList;

    private Command currentCommand;

    private ClassInfo classInfo;

    private ClassInfo executeClassInfo;

    private MethodInfo methodInfo;

    private boolean isEnd = false;

    public CommandChain(List<Command> commandList, ClassInfo classInfo, MethodInfo methodInfo) {
        if (commandList == null || commandList.isEmpty()
                || classInfo == null || methodInfo == null) {
            throw new RuntimeException("command list cannot be empty, class info cannot be null" +
                    ", method info cannot be null");
        }

        this.commandList = commandList;
        this.classInfo = classInfo;
        this.methodInfo = methodInfo;
    }

    private boolean hasNext() {
        if (currentCommand == null) {
            return true;
        }
        int index = commandList.indexOf(currentCommand);
        if (index < commandList.size() - 1) {
            return true;
        }
        return false;
    }

    private void executeNext() throws ExecuteException {
        if (currentCommand == null) {
            currentCommand = commandList.get(0);
        } else {
            int index = commandList.indexOf(currentCommand);
            if (index >= commandList.size() - 1) {
                throw new RuntimeException("do not have next command");
            }
            currentCommand = commandList.get(index + 1);
        }
        executeCommand(currentCommand);
    }

    private void executeCommand(Command command) throws ExecuteException {
        CommandExecutor commandExecutor = CommandExecutorFactory.getCommandExecutor(command);
        commandExecutor.execute(this);
    }

    public void execute() throws ExecuteException {
        while (hasNext() && !isEnd) {
            executeNext();
        }
    }

    /**
     * why goto is a key word?
     *
     * @param lineNumber
     */
    public void gotoCommand(int lineNumber) throws ExecuteException {
        currentCommand = findCommand(lineNumber);
        executeCommand(currentCommand);
        execute();
    }

    private Command findCommand(int lineNumber) {
        for (Command command : commandList) {
            if (command.getLineNumber() == lineNumber) {
                return command;
            }
        }
        return null;
    }

    public Command getCurrentCommand() {
        return currentCommand;
    }

    public ClassInfo getClassInfo() {
        return classInfo;
    }

    public MethodInfo getMethodInfo() {
        return methodInfo;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public ClassInfo getExecuteClassInfo() {
        return executeClassInfo;
    }

    public void setExecuteClassInfo(ClassInfo executeClassInfo) {
        this.executeClassInfo = executeClassInfo;
    }
}
