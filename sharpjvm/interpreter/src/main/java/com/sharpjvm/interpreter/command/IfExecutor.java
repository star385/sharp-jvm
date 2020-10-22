package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.constants.Constants;
import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.interpreter.ExecuteException;
import com.sharpjvm.memory.runtime.RuntimeContext;

/**
 * 所有if的指令集合。
 * 
 * User: zhuguoyin
 * Date: 13-3-17
 * Time: 下午5:40
 * To change this template use File | Settings | File Templates.
 */
public class IfExecutor extends AbstractCommandExecutor {
    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        switch (command.getType()) {
            // 等于0时跳转
            case Constants.IF_EQ :
                byte [] ifEqTopBytes = getOperandStack().pop();
                int ifEqTopValue = ByteUtil.byteArray2Int(ifEqTopBytes);
                if (ifEqTopValue == 0) {
                    byte[] ifEqGotoLineBytes = command.getParameter();
                    short ifEqGotoLine = ByteUtil.byteArray2Short(ifEqGotoLineBytes);
                    commandChain.gotoCommand(ifEqGotoLine + command.getLineNumber());
                }
                break;
             // 不等于0时跳转
            case Constants.IF_NE:
                byte [] ifNeTopBytes = getOperandStack().pop();
                int ifNeTopValue = ByteUtil.byteArray2Int(ifNeTopBytes);
                if (ifNeTopValue != 0) {
                    byte[] ifNeGotoLineBytes = command.getParameter();
                    short ifNeGotoLine = ByteUtil.byteArray2Short(ifNeGotoLineBytes);
                    commandChain.gotoCommand(ifNeGotoLine + command.getLineNumber());
                }
                break;
             // 小于0时跳转
            case Constants.IF_LT:
                byte [] ifLtTopBytes = getOperandStack().pop();
                int ifLtTopValue = ByteUtil.byteArray2Int(ifLtTopBytes);
                if (ifLtTopValue < 0) {
                    byte[] ifLtGotoLineBytes = command.getParameter();
                    short ifLtGotoLine = ByteUtil.byteArray2Short(ifLtGotoLineBytes);
                    commandChain.gotoCommand(ifLtGotoLine + command.getLineNumber());
                }
                break;
             // 大于等于0时跳转
            case Constants.IF_GE:
                byte [] ifGeTopBytes = getOperandStack().pop();
                int ifGeTopValue = ByteUtil.byteArray2Int(ifGeTopBytes);
                if (ifGeTopValue >= 0) {
                    byte[] ifGeGotoLineBytes = command.getParameter();
                    short ifGeGotoLine = ByteUtil.byteArray2Short(ifGeGotoLineBytes);
                    commandChain.gotoCommand(ifGeGotoLine + command.getLineNumber());
                }
                break;
             // 大于0时跳转
            case Constants.IF_GT:
                byte [] ifGtTopBytes = getOperandStack().pop();
                int ifGtTopValue = ByteUtil.byteArray2Int(ifGtTopBytes);
                if (ifGtTopValue > 0) {
                    byte[] ifGtGotoLineBytes = command.getParameter();
                    short ifGtGotoLine = ByteUtil.byteArray2Short(ifGtGotoLineBytes);
                    commandChain.gotoCommand(ifGtGotoLine + command.getLineNumber());
                }
                break;
             // 大于等于0时跳转
            case Constants.IF_LE:
                byte [] ifLeTopBytes = getOperandStack().pop();
                int ifLeTopValue = ByteUtil.byteArray2Int(ifLeTopBytes);
                if (ifLeTopValue <= 0) {
                    byte[] ifLeGotoLineBytes = command.getParameter();
                    short ifLeGotoLine = ByteUtil.byteArray2Short(ifLeGotoLineBytes);
                    commandChain.gotoCommand(ifLeGotoLine + command.getLineNumber());
                }
                break;
             // 栈顶两数相等时跳转
            case Constants.IF_ICMP_EQ:
                byte [] ifIcmpEqTopBytes = getOperandStack().pop();
                int ifIcmpEqTopValue = ByteUtil.byteArray2Int(ifIcmpEqTopBytes);

                byte [] ifIcmpEqNextBytes = getOperandStack().pop();
                int ifIcmpEqNextValue = ByteUtil.byteArray2Int(ifIcmpEqNextBytes);

                if (ifIcmpEqTopValue == ifIcmpEqNextValue) {
                    byte[] ifIcmpEqGotoLineBytes = command.getParameter();
                    if (ifIcmpEqGotoLineBytes == null || ifIcmpEqGotoLineBytes.length != Constants.SHORT_BYTE_COUNT) {
                        throw new ExecuteException("command:ifeq, goto line must be " + Constants.SHORT_BYTE_COUNT + " bytes");
                    }
                    short ifIcmpEqGotoLine = ByteUtil.byteArray2Short(ifIcmpEqGotoLineBytes);
                    commandChain.gotoCommand(ifIcmpEqGotoLine + command.getLineNumber());
                }
                break;
             // 栈顶两数不相等时跳转
            case Constants.IF_ICMP_NE:
                byte [] ifIcmpNeTopBytes = getOperandStack().pop();
                int ifIcmpNeTopValue = ByteUtil.byteArray2Int(ifIcmpNeTopBytes);

                byte [] ifIcmpNeNextBytes = getOperandStack().pop();
                int ifIcmpNeNextValue = ByteUtil.byteArray2Int(ifIcmpNeNextBytes);

                if (ifIcmpNeTopValue != ifIcmpNeNextValue) {
                    byte[] ifIcmpNeGotoLineBytes = command.getParameter();
                    if (ifIcmpNeGotoLineBytes == null || ifIcmpNeGotoLineBytes.length != Constants.SHORT_BYTE_COUNT) {
                        throw new ExecuteException("command:ifeq, goto line must be " + Constants.SHORT_BYTE_COUNT + " bytes");
                    }
                    short ifIcmpNeGotoLine = ByteUtil.byteArray2Short(ifIcmpNeGotoLineBytes);
                    commandChain.gotoCommand(ifIcmpNeGotoLine + command.getLineNumber());
                }
                break;
             // 栈顶第二个元素大于栈顶数时跳转
            case Constants.IF_ICMP_LT:
                byte [] ifIcmpLtTopBytes = getOperandStack().pop();
                int ifIcmpLtTopValue = ByteUtil.byteArray2Int(ifIcmpLtTopBytes);

                byte [] ifIcmpLtNextBytes = getOperandStack().pop();
                int ifIcmpLtNextValue = ByteUtil.byteArray2Int(ifIcmpLtNextBytes);

                if (ifIcmpLtNextValue < ifIcmpLtTopValue) {
                    byte[] ifIcmpLtGotoLineBytes = command.getParameter();
                    if (ifIcmpLtGotoLineBytes == null || ifIcmpLtGotoLineBytes.length != Constants.SHORT_BYTE_COUNT) {
                        throw new ExecuteException("command:ifeq, goto line must be " + Constants.SHORT_BYTE_COUNT + " bytes");
                    }
                    short ifIcmpLtGotoLine = ByteUtil.byteArray2Short(ifIcmpLtGotoLineBytes);
                    commandChain.gotoCommand(ifIcmpLtGotoLine + command.getLineNumber());
                }
                break;
             // 栈顶第二个元素大于等于栈顶数时跳转
            case Constants.IF_ICMP_GE:
                byte [] ifIcmpGeTopBytes = getOperandStack().pop();
                int ifIcmpGeTopValue = ByteUtil.byteArray2Int(ifIcmpGeTopBytes);

                byte [] ifIcmpGeNextBytes = getOperandStack().pop();
                int ifIcmpGeNextValue = ByteUtil.byteArray2Int(ifIcmpGeNextBytes);

                if (ifIcmpGeNextValue >= ifIcmpGeTopValue) {
                    byte[] ifIcmpGeGotoLineBytes = command.getParameter();
                    short ifIcmpGeGotoLine = ByteUtil.byteArray2Short(ifIcmpGeGotoLineBytes);
                    commandChain.gotoCommand(ifIcmpGeGotoLine + command.getLineNumber());
                }
                break;
             // 栈顶第二个元素大于栈顶数时跳转
            case Constants.IF_ICMP_GT:
                byte [] ifIcmpGtTopBytes = getOperandStack().pop();
                int ifIcmpGtTopValue = ByteUtil.byteArray2Int(ifIcmpGtTopBytes);

                byte [] ifIcmpGtNextBytes = getOperandStack().pop();
                int ifIcmpGtNextValue = ByteUtil.byteArray2Int(ifIcmpGtNextBytes);

                if (ifIcmpGtNextValue > ifIcmpGtTopValue) {
                    byte[] ifIcmpGtGotoLineBytes = command.getParameter();
                    if (ifIcmpGtGotoLineBytes == null || ifIcmpGtGotoLineBytes.length != Constants.SHORT_BYTE_COUNT) {
                        throw new ExecuteException("command:ifeq, goto line must be " + Constants.SHORT_BYTE_COUNT + " bytes");
                    }
                    short ifIcmpGtGotoLine = ByteUtil.byteArray2Short(ifIcmpGtGotoLineBytes);
                    commandChain.gotoCommand(ifIcmpGtGotoLine + command.getLineNumber());
                }
                break;
             // 栈顶第二个元素小于栈顶数时跳转
            case Constants.IF_ICMP_LE:
                byte [] ifIcmpLeTopBytes = getOperandStack().pop();
                int ifIcmpLeTopValue = ByteUtil.byteArray2Int(ifIcmpLeTopBytes);

                byte [] ifIcmpLeNextBytes = getOperandStack().pop();
                int ifIcmpLeNextValue = ByteUtil.byteArray2Int(ifIcmpLeNextBytes);

                if (ifIcmpLeNextValue <= ifIcmpLeTopValue) {
                    byte[] ifIcmpLeGotoLineBytes = command.getParameter();
                    short ifIcmpLeGotoLine = ByteUtil.byteArray2Short(ifIcmpLeGotoLineBytes);
                    commandChain.gotoCommand(ifIcmpLeGotoLine + command.getLineNumber());
                }
                break;
             // 栈顶两引用类型元素相等时跳转
            case Constants.IF_ACMP_EQ:
                byte [] ifAcmpEqTopBytes = getOperandStack().pop();
                if (ifAcmpEqTopBytes == null || ifAcmpEqTopBytes.length != Constants.INT_BYTE_COUNT) {
                    throw new ExecuteException("command ifeq's parameter must be " + Constants.INT_BYTE_COUNT + "bytes");
                }

                byte [] ifAcmpEqNextBytes = getOperandStack().pop();
                if (ByteUtil.contentEquals(ifAcmpEqTopBytes, ifAcmpEqNextBytes)) {
                    byte[] ifAcmpEqGotoLineBytes = command.getParameter();
                    short ifAcmpEqGotoLine = ByteUtil.byteArray2Short(ifAcmpEqGotoLineBytes);
                    commandChain.gotoCommand(ifAcmpEqGotoLine + command.getLineNumber());
                }
                break;
             // 栈顶两引用类型元素不相等时跳转
            case Constants.IF_ACMP_NE:
                byte [] ifAcmpNeTopBytes = getOperandStack().pop();
                byte [] ifAcmpNeNextBytes = getOperandStack().pop();

                if (!ByteUtil.contentEquals(ifAcmpNeTopBytes, ifAcmpNeNextBytes)) {
                    byte[] ifAcmpNeGotoLineBytes = command.getParameter();
                    short ifAcmpNeGotoLine = ByteUtil.byteArray2Short(ifAcmpNeGotoLineBytes);
                    commandChain.gotoCommand(ifAcmpNeGotoLine + command.getLineNumber());
                }
                break;
             // 栈顶元素为null时跳转
            case Constants.IF_NULL:
            	byte [] ifNullTopBytes = getOperandStack().pop();
                if (ifNullTopBytes == null || ifNullTopBytes.length != Constants.REFERENCE_BYTE_COUNT) {
                    throw new ExecuteException("command ifeq's parameter must be " + Constants.INT_BYTE_COUNT + "bytes");
                }
                Object reference = RuntimeContext.getInstance().getHeap().get(ifNullTopBytes);
                if (reference == null) {
                	byte[] ifNullGotoLineBytes = command.getParameter();
                    if (ifNullGotoLineBytes == null || ifNullGotoLineBytes.length != Constants.SHORT_BYTE_COUNT) {
                        throw new ExecuteException("command:ifnull, goto line must be " + Constants.SHORT_BYTE_COUNT + " bytes");
                    }
                    short ifNullGotoLine = ByteUtil.byteArray2Short(ifNullGotoLineBytes);
                    commandChain.gotoCommand(ifNullGotoLine + command.getLineNumber());
                }
                break;
             // 栈顶元素不为null时跳转
            case Constants.IF_NON_NULL:
            	byte [] ifNonNullTopBytes = getOperandStack().pop();
                if (ifNonNullTopBytes == null || ifNonNullTopBytes.length != Constants.REFERENCE_BYTE_COUNT) {
                    throw new ExecuteException("command ifeq's parameter must be " + Constants.INT_BYTE_COUNT + "bytes");
                }
                Object nonNullReference = RuntimeContext.getInstance().getHeap().get(ifNonNullTopBytes);
                if (nonNullReference != null) {
                	byte[] ifNonNullGotoLineBytes = command.getParameter();
                    if (ifNonNullGotoLineBytes == null || ifNonNullGotoLineBytes.length != Constants.SHORT_BYTE_COUNT) {
                        throw new ExecuteException("command:ifNonNull, goto line must be " + Constants.SHORT_BYTE_COUNT + " bytes");
                    }
                    short ifNonNullGotoLine = ByteUtil.byteArray2Short(ifNonNullGotoLineBytes);
                    commandChain.gotoCommand(ifNonNullGotoLine + command.getLineNumber());
                }
                break;
        }
    }
}
