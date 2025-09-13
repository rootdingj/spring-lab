package com.dj.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: RuntimeConstantPoolOOM
 * @Description: 运行时常量池内存溢出， 不同版本JDK运行时常量池的位置
 * @author Steven
 * @date 2020年7月23日 
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 */
public class RuntimeConstantPoolOOM {

	public static void test1() {
		// 使用List保持常量池引用，避免Full GC 回收常量池行为
		List<String> list = new ArrayList<String>();
		int i = 0;
		while (true) {
			list.add(String.valueOf(i++).intern());
		}
	}
	
	public static void test2() {
		String str1 = new StringBuffer("java").append("虚拟机").toString();
		System.out.println(str1.intern() == str1);
		String str2 = new StringBuffer("java").append("JVM").toString();
		System.out.println(str2.intern( )== str2);
	}

	public static void main(String[] args) {
//		test1();
		test2();
	}

}
