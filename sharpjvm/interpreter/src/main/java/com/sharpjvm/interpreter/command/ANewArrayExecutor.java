package com.sharpjvm.interpreter.command;

import java.lang.reflect.Array;

import com.sharpjvm.bytecode.bean.ClassInfo;
import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.bean.constant.ClassConstant;
import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.interpreter.ExecuteException;
import com.sharpjvm.interpreter.util.InterpreterUtil;
import com.sharpjvm.memory.runtime.RuntimeContext;

/**
 * 创建引用类型数组命令。
 * 
 * User: zhuguoyin
 * Date: 13-3-20
 * Time: 下午10:42
 * To change this template use File | Settings | File Templates.
 */
public class ANewArrayExecutor extends AbstractCommandExecutor {
    public void execute(CommandChain commandChain) throws ExecuteException {
        Command command = commandChain.getCurrentCommand();
        ClassInfo classInfo = commandChain.getClassInfo();
        byte [] parameter = command.getParameter();
        int classConstantIndex = ByteUtil.byteArray2Int(parameter);
        ClassConstant constant = (ClassConstant) classInfo.getConstantList().getConstant(classConstantIndex);
        Class<?> type = InterpreterUtil.getTypeByDescriptionString(constant.getNameConstant().getValue());
        byte[] lengthBytes = getOperandStack().pop();
        int length = ByteUtil.byteArray2Int(lengthBytes);
        // 用反射创建数组
        Object instance = Array.newInstance(type, length);
        byte[] reference = RuntimeContext.getInstance().getHeap().getReference(instance);
        getOperandStack().push(reference);
    }

}
