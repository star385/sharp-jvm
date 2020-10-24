package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.constants.Constants;
import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.interpreter.ExecuteException;

/**
 * 所有比较的指令集合
 * 
 * User: zhuguoyin
 * Date: 13-3-17
 * Time: 上午11:41
 * To change this template use File | Settings | File Templates.
 */
public class CmpExecutor extends AbstractCommandExecutor {
    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        int result;
        switch (command.getType()) {
            case Constants.L_CMP:
                byte[] lcmpOperator1Array = getOperandStack().pop8Bytes();
                long lcmpOperator1Long = ByteUtil.byteArray2Long(lcmpOperator1Array);
                byte[] lcmpOperator2Array = getOperandStack().pop8Bytes();
                long lcmpOperator2Long = ByteUtil.byteArray2Long(lcmpOperator2Array);
                if (lcmpOperator2Long > lcmpOperator1Long) {
                    result = 1;
                } else if (lcmpOperator1Long == lcmpOperator2Long) {
                    result = 0;
                } else {
                    result = -1;
                }
                break;
            case Constants.F_CMP_L:
                byte[] fcmplOperator1Array = getOperandStack().pop();
                float fcmplOperator1Float = ByteUtil.byteArray2Float(fcmplOperator1Array);
                byte[] fcmplOperator2Array = getOperandStack().pop();
                float fcmplOperator2Float = ByteUtil.byteArray2Float(fcmplOperator2Array);

                if (Float.isNaN(fcmplOperator1Float) || Float.isNaN(fcmplOperator2Float)) {
                    result = -1;
                }

                if (fcmplOperator2Float > fcmplOperator1Float) {
                    result = 1;
                } else if (fcmplOperator1Float == fcmplOperator2Float) {
                    result = 0;
                } else {
                    result = -1;
                }
                break;
            case Constants.F_CMP_G:
                byte[] fcmpgOperator1Array = getOperandStack().pop();
                float fcmpgOperator1Float = ByteUtil.byteArray2Float(fcmpgOperator1Array);
                byte[] fcmpgOperator2Array = getOperandStack().pop();
                float fcmpgOperator2Float = ByteUtil.byteArray2Float(fcmpgOperator2Array);

                if (Float.isNaN(fcmpgOperator1Float) || Float.isNaN(fcmpgOperator2Float)) {
                    result = 1;
                }

                if (fcmpgOperator2Float > fcmpgOperator1Float) {
                    result = 1;
                } else if (fcmpgOperator1Float == fcmpgOperator2Float) {
                    result = 0;
                } else {
                    result = -1;
                }
                break;
            case Constants.D_CMP_L:
                byte[] dcmplOperator1Array = getOperandStack().pop8Bytes();
                double dcmplOperator1Double = ByteUtil.byteArray2Double(dcmplOperator1Array);
                byte[] dcmplOperator2Array = getOperandStack().pop8Bytes();
                double dcmplOperator2Double = ByteUtil.byteArray2Double(dcmplOperator2Array);

                if (Double.isNaN(dcmplOperator1Double) || Double.isNaN(dcmplOperator2Double)) {
                    result = -1;
                }

                if (dcmplOperator2Double > dcmplOperator1Double) {
                    result = 1;
                } else if (dcmplOperator1Double == dcmplOperator2Double) {
                    result = 0;
                } else {
                    result = -1;
                }
                break;
            case Constants.D_CMP_G:
                byte[] dcmpgOperator1Array = getOperandStack().pop8Bytes();
                double dcmpgOperator1Double = ByteUtil.byteArray2Double(dcmpgOperator1Array);
                byte[] dcmpgOperator2Array = getOperandStack().pop8Bytes();
                double dcmpgOperator2Double = ByteUtil.byteArray2Double(dcmpgOperator2Array);

                if (Double.isNaN(dcmpgOperator1Double) || Double.isNaN(dcmpgOperator2Double)) {
                    result = 1;
                }

                if (dcmpgOperator2Double > dcmpgOperator1Double) {
                    result = 1;
                } else if (dcmpgOperator1Double == dcmpgOperator2Double) {
                    result = 0;
                } else {
                    result = -1;
                }
                break;
            default:
                throw new ExecuteException("do not find this command");
        }
        byte[] resultBytes = ByteUtil.int2ByteArray(result);
        getOperandStack().push(resultBytes);
    }
}
