package com.sharpjvm.interpreter.simple;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Stack;

import com.sharpjvm.bytecode.bean.ClassInfo;
import com.sharpjvm.bytecode.bean.MethodInfo;
import com.sharpjvm.bytecode.bean.attribute.Code;
import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.bean.constant.ClassConstant;
import com.sharpjvm.bytecode.bean.constant.Constant;
import com.sharpjvm.bytecode.bean.constant.FieldRefConstant;
import com.sharpjvm.bytecode.bean.constant.MethodRefConstant;
import com.sharpjvm.bytecode.bean.constant.NameAndTypeConstant;
import com.sharpjvm.bytecode.bean.constant.StringConstant;
import com.sharpjvm.bytecode.bean.constant.Utf8Constant;
import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.interpreter.ExecuteException;
import com.sharpjvm.interpreter.Interpreter;
import com.sharpjvm.interpreter.util.InterpreterUtil;

/**
 * 一个最简单的解释器
 *
 * User: zhuguoyin
 * Date: 13-2-8
 * Time: 下午4:02
 * To change this template use File | Settings | File Templates.
 */
public class SimpleInterpreter implements Interpreter {

    public void interpret(ClassInfo classInfo) throws ExecuteException {
        System.out.println("********************* sharp jvm start ****************");
        if (classInfo == null) {
            throw new ExecuteException("没有入口类");
        }
        // 从字节码信息中找到main方法
        MethodInfo mainMethodInfo;
        List<Object> methodAndClassInfo = InterpreterUtil.findMethod(classInfo, "main", new Class<?>[] {String[].class});
        if (methodAndClassInfo == null) {
            throw new ExecuteException("入口类没有main方法");
        }
        mainMethodInfo = (MethodInfo) methodAndClassInfo.get(0);
        if (mainMethodInfo == null) {
            throw new ExecuteException("入口类没有main方法");
        }

        // 找到main方法的
        Code codeAttribute = mainMethodInfo.getCodeAttribute();
        if (codeAttribute == null) {
            throw new ExecuteException("main方法没有代码");
        }

        // 指令列表
        List<Command> commandList = codeAttribute.getCommandList();
        if (commandList == null || commandList.isEmpty()) {
            throw new ExecuteException("main方法没有代码");
        }

        // 运行时的操作数栈
        Stack<Object> operationStack = new Stack<Object>();

        // 顺序执行操作命令
        for (Command command : commandList) {
            executeCommand(command, classInfo, operationStack);
        }
        System.out.println("********************* sharp jvm end ****************");
    }

    private void executeCommand(Command command, ClassInfo classInfo, Stack<Object> operationStack) throws ExecuteException {
        // getStatic执行方式
        if (command.getType() == com.sharpjvm.bytecode.constants.Constants.GET_STATIC) {
            executeGetStatic(command, classInfo, operationStack);
            // LDC执行方式
        } else if (command.getType() == com.sharpjvm.bytecode.constants.Constants.LDC) {
            executeLDC(command, classInfo, operationStack);
        } else if (command.getType() == com.sharpjvm.bytecode.constants.Constants.INVOKE_VIRTUAL) {
            executeInvokeVirtual(command, classInfo, operationStack);
        }
    }

    /**
     * 将int，float或String型常量值从常量池中推送至栈顶
     * 解释此指令直接从常量池中将相应的参数获取出来，push到栈顶就行
     *
     * @param command
     * @param classInfo
     * @param operationStack
     */
    private void executeLDC(Command command, ClassInfo classInfo, Stack<Object> operationStack) {
        byte[] parameter = command.getParameter();
        short parameterIndex = ByteUtil.byteArray2Short(parameter);
        StringConstant stringConstant = (StringConstant) classInfo.getConstantList().getConstant(parameterIndex);
        String parameterValue = stringConstant.getNameConstant().getValue();
        operationStack.push(parameterValue);
    }

    /**
     * invoke virtual命令。
     * 解析此命令的思路是从栈中获取出参数和执行的实例，然后通过反射执行该方法。
     *
     * @param command
     * @param classInfo
     * @param operationStack
     * @throws ExecuteException
     */
    private void executeInvokeVirtual(Command command, ClassInfo classInfo, Stack<Object> operationStack) throws ExecuteException {
        Object parameter = operationStack.pop();
        Object instance = operationStack.pop();
        byte[] methodConstantsIndexBytes = command.getParameter();
        short methodConstantIndex = ByteUtil.byteArray2Short(methodConstantsIndexBytes);
        Constant methodConstant = classInfo.getConstantList().getConstant(methodConstantIndex);
        if (!(methodConstant instanceof MethodRefConstant)) {
            throw new ExecuteException("字节码不正确。");
        }
        MethodRefConstant methodConstantEx = (MethodRefConstant) methodConstant;
        ClassConstant classConstant = methodConstantEx.getClassConstant();
        String className = classConstant.getNameConstant().getValue();
        className = className.replace("/", ".");
        NameAndTypeConstant nameAndTypeConstant = methodConstantEx.getMethodConstant();
        String methodName = nameAndTypeConstant.getNameConstant().getValue();
        String methodDescription = nameAndTypeConstant.getDescriptionConstant().getValue();
        Class<?> type = null;
        try {
            type = Class.forName(className);
            Method method = type.getMethod(methodName, InterpreterUtil.parseMethodParameterTypes(methodDescription).getParameterTypes());
            Object[] parameters = {parameter};
            method.invoke(instance, parameters);
        } catch (Exception e) {
            throw new ExecuteException("error occurred when execute invoke virtual", e);
        }

    }

    /**
     * get static命令。
     * 通过反射，获取出
     *
     * @param command
     * @param classInfo
     * @param operationStack
     * @throws ExecuteException
     */
    private void executeGetStatic(Command command, ClassInfo classInfo, Stack<Object> operationStack) throws ExecuteException {
        byte[] parameter = command.getParameter();
        short staticMethodIndex = ByteUtil.byteArray2Short(parameter);
        Constant constant = classInfo.getConstantList().getConstant(staticMethodIndex);
        if (!(constant instanceof FieldRefConstant)) {
            throw new ExecuteException("执行GetStatic时出错，参数常量索引指向了错误的位置");
        }
        FieldRefConstant fieldRefConstant = (FieldRefConstant) constant;
        ClassConstant classConstant = fieldRefConstant.getClassConstant();
        String className = classConstant.getNameConstant().getValue();
        className = className.replace("/", ".");
        try {
            Class<?> clazz = Class.forName(className);
            NameAndTypeConstant fieldNameAndTypeConstant = fieldRefConstant.getFieldConstant();
            if (fieldNameAndTypeConstant == null) {
                throw new ExecuteException("执行GetStatic时出错，参数常量索引指向了错误的位置");
            }
            Utf8Constant fieldNameConstant = fieldNameAndTypeConstant.getNameConstant();

            String fieldName = fieldNameConstant.getValue();
            Field field = clazz.getField(fieldName);
            Object o = field.get(null);
            operationStack.push(o);
        } catch (Exception e) {
            throw new ExecuteException("error occurred when execute get static", e);
        }

    }
}
