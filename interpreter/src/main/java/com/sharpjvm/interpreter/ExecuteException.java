package com.sharpjvm.interpreter;

/**
 * 执行虚拟机异常
 *
 * User: zhuguoyin
 * Date: 13-2-8
 * Time: 下午4:31
 * To change this template use File | Settings | File Templates.
 */
public class ExecuteException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private byte commandType;

    public ExecuteException() {
    }

    public ExecuteException(String message) {
        super(message);
    }

    public ExecuteException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExecuteException(Throwable cause) {
        super(cause);
    }

    public byte getCommandType() {
        return commandType;
    }

    public void setCommandType(byte commandType) {
        this.commandType = commandType;
    }
}
