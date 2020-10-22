package com.sharpjvm.bytecode.bean.constant;

import com.sharpjvm.bytecode.bean.ByteArrayable;
import com.sharpjvm.bytecode.constants.Constants;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: zhuguoyin
 * Date: 13-1-24
 * Time: ÏÂÎç9:20
 * To change this template use File | Settings | File Templates.
 */
public abstract class Constant implements ByteArrayable, Constants {

    protected byte tag;

    protected ConstantList constantList;

    public byte getTag() {
        return tag;
    }

    public abstract byte [] toBytes();

    public abstract String toString();

    public void setConstantList(ConstantList constantList) {
        this.constantList = constantList;
    }
}
