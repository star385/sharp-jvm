package com.sharpjvm.interpreter.command;

import com.sharpjvm.interpreter.ExecuteException;

/**
 * 创建对象指令
 * 
 * User: zhuguoyin
 * Date: 13-3-20
 * Time: 下午9:15
 * To change this template use File | Settings | File Templates.
 */
public class NewExecutor extends AbstractCommandExecutor {
    public void execute(CommandChain commandChain) throws ExecuteException {
//        Command command = commandChain.getCurrentCommand();
//        byte[] parameter = command.getParameter();
//        if (parameter == null || parameter.length != 2) {
//            throw new ExecuteException("new command do not have a parameter");
//        }
//        short classConstantIndex = ByteUtil.byteArray2Short(parameter);
//        ClassInfo classInfo = commandChain.getClassInfo();
//        ClassConstant classConstant = (ClassConstant) classInfo.getConstantList().getConstant(classConstantIndex);
//        String className = classConstant.getNameConstant().getValue();
//        if (className == null || className.isEmpty()) {
//            throw new ExecuteException("new command error, class not found");
//        }
//        className = className.replace("/", ".");
//        try {
//            Object instance = Class.forName(className).newInstance();
//            byte[] reference = RuntimeContext.getInstance().getHeap().getReference(instance);
//            getOperandStack().push(reference);
//        } catch (Exception e) {
//            throw new ExecuteException("new command error, new instance error");
//        }
        getOperandStack().push(new byte[4]);
    }
}
