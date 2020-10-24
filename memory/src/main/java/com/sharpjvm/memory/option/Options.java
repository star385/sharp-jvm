package com.sharpjvm.memory.option;

/**
 * java程序运行的参数。
 *
 * User: zhuguoyin
 * Date: 13-2-17
 * Time: 上午11:44
 * To change this template use File | Settings | File Templates.
 */
public class Options {

    private String classPath;

    /**
     * 初始堆大小
     */
    private int Xms = 32 * 1024 * 1024;

    /**
     * 最大堆内存大小
     */
    private int Xmx = 128 * 1024 * 1024;

    /**
     * 栈内存大小
     */
    private int Xss = 4 * 1024 * 1024;

    private int permSize = 16 * 1024 * 1024;

    /**
     * 方法区内存最大值
     */
    private int maxPermSize = 32 * 1024 * 1024;

    private String mainClass;

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    public int getXms() {
        return Xms;
    }

    public void setXms(int xms) {
        Xms = xms;
    }

    public int getXss() {
        return Xss;
    }

    public void setXss(int xss) {
        Xss = xss;
    }

    public int getMaxPermSize() {
        return maxPermSize;
    }

    public void setMaxPermSize(int maxPermSize) {
        this.maxPermSize = maxPermSize;
    }

    public String getMainClass() {
        return mainClass;
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    public int getXmx() {
        return Xmx;
    }

    public void setXmx(int xmx) {
        Xmx = xmx;
    }

    public int getPermSize() {
        return permSize;
    }

    public void setPermSize(int permSize) {
        this.permSize = permSize;
    }
}
