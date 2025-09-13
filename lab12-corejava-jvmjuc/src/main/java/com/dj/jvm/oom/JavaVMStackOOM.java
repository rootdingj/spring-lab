package com.dj.jvm.oom;

/**
 * 
 * @ClassName: JavaVMStackOOM
 * @Description:创建线程导致内存溢出。注意：先保存当前工作，可能会导致系统假死
 * @author Steven
 * @date 2020年7月23日
 * VM Args: -Xss2M
 */
public class JavaVMStackOOM {

	private void dontStop() {
		while (true) {

		}
	}

	public void stackLeakByThread() {
		while (true) {
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					dontStop();
				}
			});
			thread.start();
		}
	}

	// 注意：运行后机器可能会卡死！！！
	public static void main(String[] args) {
		JavaVMStackOOM oom = new JavaVMStackOOM();
		oom.stackLeakByThread();
	}

}
