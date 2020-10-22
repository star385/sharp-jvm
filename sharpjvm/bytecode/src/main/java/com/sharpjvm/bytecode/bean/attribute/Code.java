package com.sharpjvm.bytecode.bean.attribute;

import com.sharpjvm.bytecode.bean.CodeExceptionTable;
import com.sharpjvm.bytecode.bean.constant.Constant;
import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.bean.constant.Utf8Constant;
import com.sharpjvm.bytecode.util.AttributeUtil;
import com.sharpjvm.bytecode.util.ByteUtil;
import com.sharpjvm.bytecode.util.CommandUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * code属性
 *
 * User: zhuguoyin
 * Date: 13-2-5
 * Time: 上午11:04
 * To change this template use File | Settings | File Templates.
 */
public class Code extends Attribute {

    // 最大栈深
    private short maxStack;

    // 最大的局部变量数目
    private short maxLocals;

    // 指令长度
    private int commandLength;

    // 指令列表，亦即此方法的code包含多少指令
    private List<Command> commandList;

    // 异常表数量
    private short exceptionTableCount;

    // 异常表列表
    private List<CodeExceptionTable> exceptionTableList;

    // 属性数目
    private short attributeCount;

    // 属性列表
    private List<Attribute> attributeList;

    public byte[] toBytes() {
        return new byte[0];
    }

    @Override
    protected void parseOtherInfo(byte[] bytes, int startIndex) {
        // 设置一个本地的开始索引变量，每次解析完毕后，往后加上解析过的字节数。
        int startIndexLocal = startIndex;
        byte[] maxStackBytes = new byte[2];
        System.arraycopy(bytes, startIndexLocal, maxStackBytes, 0, 2);
        this.maxStack = ByteUtil.byteArray2Short(maxStackBytes);

        startIndexLocal += 2;
        byte[] maxLocalsBytes = new byte[2];
        System.arraycopy(bytes, startIndexLocal, maxLocalsBytes, 0, 2);
        this.maxLocals = ByteUtil.byteArray2Short(maxLocalsBytes);

        startIndexLocal += 2;
        byte[] codeLengthBytes = new byte[4];
        System.arraycopy(bytes, startIndexLocal, codeLengthBytes, 0, 4);
        this.commandLength = ByteUtil.byteArray2Int(codeLengthBytes);

        startIndexLocal += 4;
        parseCommands(bytes, startIndexLocal);

        startIndexLocal += commandLength;
        byte [] exceptionTableCountBytes = new byte[2];
        System.arraycopy(bytes, startIndexLocal, exceptionTableCountBytes, 0, 2);
        this.exceptionTableCount = ByteUtil.byteArray2Short(exceptionTableCountBytes);

        startIndexLocal += 2;
        if (exceptionTableCount > 0) {
            exceptionTableList = new ArrayList<CodeExceptionTable>();
            for (int i = 0;  i < exceptionTableCount; i++) {
                CodeExceptionTable codeExceptionTable = new CodeExceptionTable();
                codeExceptionTable.fromBytes(bytes, startIndexLocal);
                exceptionTableList.add(codeExceptionTable);
                startIndexLocal += 8;
            }
        }

        byte[] attributeCountBytes = new byte[2];
        System.arraycopy(bytes, startIndexLocal, attributeCountBytes, 0, 2);
        this.attributeCount = ByteUtil.byteArray2Short(attributeCountBytes);

        startIndexLocal += 2;
        if (attributeCount > 0) {
            attributeList = new ArrayList<Attribute>();
            for (int i = 0; i < attributeCount; i++) {
                byte[] attributeBytes = new byte[2];
                System.arraycopy(bytes, startIndexLocal, attributeBytes, 0, 2);
                short attributeNameIndex = ByteUtil.byteArray2Short(attributeBytes);
                Constant constant = constantList.getConstant(attributeNameIndex);
                if (!(constant instanceof Utf8Constant)) {
                    throw new RuntimeException("byte code format wrong");
                }
                String attributeName = ((Utf8Constant) constant).getValue();
                Attribute attribute = AttributeUtil.createAttributeByName(attributeName);
                attribute.setConstantList(constantList);
                attribute.fromBytes(bytes, startIndexLocal);
                attributeList.add(attribute);
                startIndexLocal += attribute.getLength();
            }

        }
    }

    private void parseCommands(byte[] bytes, int startIndex) {
        if (commandLength <= 0) {
            return;
        }
        int parsedByteCount = 0;
        commandList = new ArrayList<Command>();
        while ( parsedByteCount < commandLength) {
            byte commandType = bytes[startIndex + parsedByteCount];
            int parameterCount = CommandUtil.getParameterByteCount(commandType);
            if (parsedByteCount + parameterCount > commandLength) {
                throw new RuntimeException("byte code format wrong!");
            }
            Command command = new Command();
            command.setType(commandType);
            command.setName(CommandUtil.getDescription(commandType));
            if (parameterCount > 0) {
                byte[] parameters = new byte[parameterCount];
                System.arraycopy(bytes, startIndex + parsedByteCount + 1, parameters, 0, parameterCount);
                command.setParameter(parameters);
            }
            command.setLineNumber(parsedByteCount);
            commandList.add(command);
            parsedByteCount += (1 + parameterCount);
        }
    }

    public short getMaxStack() {
        return maxStack;
    }

    public void setMaxStack(short maxStack) {
        this.maxStack = maxStack;
    }

    public short getMaxLocals() {
        return maxLocals;
    }

    public void setMaxLocals(short maxLocals) {
        this.maxLocals = maxLocals;
    }

    public int getCommandLength() {
        return commandLength;
    }

    public void setCommandLength(int commandLength) {
        this.commandLength = commandLength;
    }

    public List<Command> getCommandList() {
        return commandList;
    }

    public void setCommandList(List<Command> commandList) {
        this.commandList = commandList;
    }

    public short getExceptionTableCount() {
        return exceptionTableCount;
    }

    public void setExceptionTableCount(short exceptionTableCount) {
        this.exceptionTableCount = exceptionTableCount;
    }

    public List<CodeExceptionTable> getExceptionTableList() {
        return exceptionTableList;
    }

    public void setExceptionTableList(List<CodeExceptionTable> exceptionTableList) {
        this.exceptionTableList = exceptionTableList;
    }

    public short getAttributeCount() {
        return attributeCount;
    }

    public void setAttributeCount(short attributeCount) {
        this.attributeCount = attributeCount;
    }

    public List<Attribute> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<Attribute> attributeList) {
        this.attributeList = attributeList;
    }

    public void setAllConstants(List<Constant> allConstants) {
    }
}
