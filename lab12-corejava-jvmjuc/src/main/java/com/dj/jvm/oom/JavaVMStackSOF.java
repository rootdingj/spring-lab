package com.dj.jvm.oom;

/**
 * 
 * @ClassName: JavaVMStackSOF
 * @Description: 虚拟机栈和本地方法栈溢出，两种异常： 1.线程请求栈深度>虚拟机最大深度，将抛出StackOverflowError；
 *               2.VM扩展栈时无法申请足够的内存空间，将抛出OutOfMemoryError。
 * @author Steven
 * @date 2020年7月23日
 * VM Args: -Xss128k
 */
public class JavaVMStackSOF {

	private int stackLength = 1;

	public void stackLeak() {
		stackLength++;
		// 死循环
		stackLeak();
	}

	public static void main(String[] args) {
		JavaVMStackSOF stackSOF = new JavaVMStackSOF();
		try {
			stackSOF.stackLeak();
		} catch (Throwable e) {
			System.out.println("stackLength:" + stackSOF.stackLength);
//			e.printStackTrace();
			throw e;
		}
	}

	/*
	 * stackLength:987 Exception in thread "main" java.lang.StackOverflowError at
	 * com.dj.corejava.jvm.oom.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:16) at
	 * com.dj.corejava.jvm.oom.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:18) ....
	 * 
	 * 
	 */

}
