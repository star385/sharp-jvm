package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.constants.Constants;
import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.interpreter.ExecuteException;

/**
 * 所有的位操作指令。
 *
 * User: zhuguoyin
 * Date: 13-3-10
 * Time: 下午10:17
 * To change this template use File | Settings | File Templates.
 */
public class BitOperateExecutor extends AbstractCommandExecutor {

    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        switch (command.getType()) {
        	// 整型左移
            case Constants.I_SHL:
                byte[] ishlOperator1Array = getOperandStack().pop();
                int ishlOperator1Int = ByteUtil.byteArray2Int(ishlOperator1Array);
                byte[] ishlOperator2Array = getOperandStack().pop();
                int ishlOperator2Int = ByteUtil.byteArray2Int(ishlOperator2Array);
                int result = ishlOperator2Int << ishlOperator1Int;
                byte[] resultBytes = ByteUtil.int2ByteArray(result);
                getOperandStack().push(resultBytes);
                break;
             // 长整型左移
            case Constants.L_SHL:
                byte[] lshlOperator1Array = getOperandStack().pop();
                int lshlOperator1Int = ByteUtil.byteArray2Int(lshlOperator1Array);
                byte[] lshlOperator2Array = getOperandStack().pop();
                long lshlOperator2Int = ByteUtil.byteArray2Int(lshlOperator2Array);
                long lshlResult = lshlOperator2Int << lshlOperator1Int;
                byte[] lshlResultBytes = ByteUtil.long2ByteArray(lshlResult);
                getOperandStack().push(lshlResultBytes);
                break;
             // 整型右移
            case Constants.I_SHR:
                byte[] ishrOperator1Array = getOperandStack().pop();
                int ishrOperator1Int = ByteUtil.byteArray2Int(ishrOperator1Array);
                byte[] ishrOperator2Array = getOperandStack().pop();
                int ishrOperator2Int = ByteUtil.byteArray2Int(ishrOperator2Array);
                int ishrResult = ishrOperator2Int >> ishrOperator1Int;
                byte[] ishrResultBytes = ByteUtil.int2ByteArray(ishrResult);
                getOperandStack().push(ishrResultBytes);
                break;
             // 长整型右移
            case Constants.L_SHR:
                byte[] lshrOperator1Array = getOperandStack().pop();
                int lshrOperator1Int = ByteUtil.byteArray2Int(lshrOperator1Array);
                byte[] lshrOperator2Array = getOperandStack().pop();
                long lshrOperator2Int = ByteUtil.byteArray2Long(lshrOperator2Array);
                long lshrResult = lshrOperator2Int >> lshrOperator1Int;
                byte[] lshrResultBytes = ByteUtil.long2ByteArray(lshrResult);
                getOperandStack().push(lshrResultBytes);
                break;
             // 整型无符号右移
            case Constants.I_U_SHR:
                byte[] iushrOperator1Array = getOperandStack().pop();
                int iushrOperator1Int = ByteUtil.byteArray2Int(iushrOperator1Array);
                byte[] iushrOperator2Array = getOperandStack().pop();
                int iushrOperator2Int = ByteUtil.byteArray2Int(iushrOperator2Array);
                int iushrResult = iushrOperator2Int >>> iushrOperator1Int;
                byte[] iushrResultBytes = ByteUtil.int2ByteArray(iushrResult);
                getOperandStack().push(iushrResultBytes);
                break;
             // 整型无符号右移
            case Constants.L_U_SHR:
                byte[] lushrOperator1Array = getOperandStack().pop();
                int lushrOperator1Int = ByteUtil.byteArray2Int(lushrOperator1Array);
                byte[] lushrOperator2Array = getOperandStack().pop();
                long lushrOperator2Int = ByteUtil.byteArray2Long(lushrOperator2Array);
                long lushrResult = lushrOperator2Int >>> lushrOperator1Int;
                byte[] lushrResultBytes = ByteUtil.long2ByteArray(lushrResult);
                getOperandStack().push(lushrResultBytes);
                break;
            // 整型&操作
            case Constants.I_AND:
                byte[] iandOperator1Array = getOperandStack().pop();
                int iandOperator1Int = ByteUtil.byteArray2Int(iandOperator1Array);
                byte[] iandOperator2Array = getOperandStack().pop();
                int iandOperator2Int = ByteUtil.byteArray2Int(iandOperator2Array);
                int iandResult = iandOperator2Int & iandOperator1Int;
                byte[] iandResultBytes = ByteUtil.int2ByteArray(iandResult);
                getOperandStack().push(iandResultBytes);
                break;
             // 长整型&操作
            case Constants.L_AND:
                byte[] longAndOperator1Array = getOperandStack().pop();
                long longAndOperator1 = ByteUtil.byteArray2Long(longAndOperator1Array);
                byte[] longAndOperator2Array = getOperandStack().pop();
                long longAndOperator2 = ByteUtil.byteArray2Long(longAndOperator2Array);
                long longResult = longAndOperator1 & longAndOperator2;
                byte[] longResultBytes = ByteUtil.long2ByteArray(longResult);
                getOperandStack().push(longResultBytes);
                break;
             // 整型|操作
            case Constants.I_OR:
                byte[] iorOperator1Array = getOperandStack().pop();
                int iorOperator1Int = ByteUtil.byteArray2Int(iorOperator1Array);
                byte[] iorOperator2Array = getOperandStack().pop();
                int iorOperator2Int = ByteUtil.byteArray2Int(iorOperator2Array);
                int iorResult = iorOperator2Int | iorOperator1Int;
                byte[] iorResultBytes = ByteUtil.int2ByteArray(iorResult);
                getOperandStack().push(iorResultBytes);
                break;
             // 长整型|操作
            case Constants.L_OR:
                byte[] lorOperator1Array = getOperandStack().pop();
                long lorOperator1 = ByteUtil.byteArray2Long(lorOperator1Array);
                byte[] lorOperator2Array = getOperandStack().pop();
                long lorOperator2 = ByteUtil.byteArray2Long(lorOperator2Array);
                long lorResult = lorOperator1 | lorOperator2;
                byte[] lorResultBytes = ByteUtil.long2ByteArray(lorResult);
                getOperandStack().push(lorResultBytes);
                break;
             // 整型^操作
            case Constants.I_XOR:
                byte[] ixorOperator1Array = getOperandStack().pop();
                int ixorOperator1Int = ByteUtil.byteArray2Int(ixorOperator1Array);
                byte[] ixorOperator2Array = getOperandStack().pop();
                int ixorOperator2Int = ByteUtil.byteArray2Int(ixorOperator2Array);
                int ixorResult = ixorOperator2Int ^ ixorOperator1Int;
                byte[] ixorResultBytes = ByteUtil.int2ByteArray(ixorResult);
                getOperandStack().push(ixorResultBytes);
                break;
             // 长整型^操作
            case Constants.L_XOR:
                byte[] lxorOperator1Array = getOperandStack().pop();
                long lxorOperator1 = ByteUtil.byteArray2Long(lxorOperator1Array);
                byte[] lxorOperator2Array = getOperandStack().pop();
                long lxorOperator2 = ByteUtil.byteArray2Long(lxorOperator2Array);
                long lxorResult = lxorOperator1 ^ lxorOperator2;
                byte[] lxorResultBytes = ByteUtil.long2ByteArray(lxorResult);
                getOperandStack().push(lxorResultBytes);
                break;
        }

    }
}
