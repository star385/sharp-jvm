package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.ClassInfo;
import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.bean.constant.ClassConstant;
import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.interpreter.ExecuteException;
import com.sharpjvm.memory.runtime.RuntimeContext;

/**
 * instanceofÖ¸ÁîÖ´ÐÐÆ÷
 * 
 * User: zhuguoyin
 * Date: 13-3-20
 * Time: ÏÂÎç11:13
 * To change this template use File | Settings | File Templates.
 */
public class InstanceOfExecutor extends AbstractCommandExecutor {
    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        byte [] parameter = command.getParameter();
        int classConstantIndex = ByteUtil.byteArray2Int(parameter);
        ClassInfo classInfo = commandChain.getExecuteClassInfo();
        ClassConstant constant = (ClassConstant) classInfo.getConstantList().getConstant(classConstantIndex);
        String className = constant.getNameConstant().getValue();
        className = className.replace("/", ".");
        Class<?> type = null;
        try {
            type = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new ExecuteException("didn't find class:" + className);
        }
        byte[] reference = getOperandStack().pop();
        Object instance = RuntimeContext.getInstance().getHeap().get(reference);
        int result = 0;
        if (type.isAssignableFrom(instance.getClass())) {
            result = 1;
        }
        byte[] resultBytes = ByteUtil.int2ByteArray(result);
        getOperandStack().push(resultBytes);
    }
}
