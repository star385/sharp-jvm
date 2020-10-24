package com.sharpjvm.jvm.test;

/**
 * �ڲ�������ࡣ
 * 
 * User: zhuguoyin
 * Date: 13-3-31
 * To change this template use File | Settings | File Templates.
 */
public class InnerClassTest {

	/**
	 * ���Խӿ�
	 * 
	 * @author zhuguoyin
	 *
	 */
    public static interface TestInterface {
        void doSomeThing();
    }

    /**
     * �����ڲ���
     * 
     * @author zhuguoyin
     *
     */
    public static class TestClass implements TestInterface {

        public void doSomeThing() {
            System.out.println("this is inner class!");
        }
    }

    public static void main(String[] args) {
        TestInterface t1 = new TestClass();
        // ����������
        TestInterface t2 = new TestInterface() {
            public void doSomeThing() {
                System.out.println("this is anonymous class!");
            }
        };
        t1.doSomeThing();
        t2.doSomeThing();
    }
}
