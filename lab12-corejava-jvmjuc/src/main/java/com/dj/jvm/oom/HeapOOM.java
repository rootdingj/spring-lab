package com.dj.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: HeapOOM
 * @Description: java 堆溢出：只要不断的创建对象，并且保证GC Root到对象之间有可达路径来避免垃圾回收器来清除这些对象，那么对象到达最大容量限制后就会产生堆内存溢出异常。
 * @author Steven
 * @date 2020年7月23日
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *
 */
public class HeapOOM {
	
	static class OOMObject{}
	
	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<>();
		while(true) {
			list.add(new OOMObject());
		}
	}
	/*
		java.lang.OutOfMemoryError: Java heap space
		Dumping heap to java_pid6664.hprof ...
		Heap dump file created [28019150 bytes in 0.147 secs]
		Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
		
		通过内存映像分析工具（如：Eclipse Memory analyzer）对dump文件进行分析：
		1.内存泄露（Memory Leak）: 用工具查看泄露对象到GC root的引用链，找出垃圾回收器无法自动回收对象的原因；
		2.内存溢出（Memory Overflow）:检查虚拟机参数（-Xms、-Xmx）和物理机内存，代码中某些对象生命周期过长。
	*/
}
