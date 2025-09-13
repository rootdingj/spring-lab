package com.dj.ch06;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射机制
 * 通过在运行时探查字段和方法，从而可以帮助写出通用性很好的程序，这项能力对系统编程来说特别有用，
 * 但它并不适合于应用编程。而且，反射是脆弱的——编译不能帮助你发现编译错误，任何错误在运行时被发现并且都会导致异常。
 * 
 */
public class ReflectTest {
	
	public static void main(String[] args) throws Exception {
		ReflectBean s = new ReflectBean("dj");

		// 获得一个类的Class类型对象，虚拟机中已经有了Class的实例对象
		Class c1 = s.getClass();
		Class c2 = ReflectBean.class;
		// 虚拟机中没有Class的实例对象,通过全类名获取
		String className = "com.dj.base.ch06.ReflectBean";
		Class c3 = Class.forName(className);
		// 测试属性抽象类，和访问权限的设置
		// 只有public可以直接访问，私有...属性，需设置访问权限可见性 setAccessible()
		Field f = c1.getField("pubName");
		Field f2 = c1.getField("priName");
		System.out.println(f.getType());
		System.out.println(f.getModifiers());
		// 获得修饰符参数值
		System.out.println(f.getName());
		// 获得修饰符参数值,镜子里面：属性.get(包含属性的对象)
		System.out.println(f.get(s));
		Field[] fields = c1.getDeclaredFields();
		for (Field fi : fields) {
			// 设置私有属性等的可见性
			fi.setAccessible(true);
			System.out.println(fi.getType());
			System.out.println(fi.getModifiers());
			System.out.println(fi.getName());
			System.out.println(fi.get(s));
		}

		// 方法抽象类
		// 获得Method类的对象
		Method method1 = c1.getMethod("say");
		// invoke()通过ReflectBean的对象s找到ReflectBean类中的方法
		method1.invoke(s);
		// 通过方法名和参数列表（将参数列表封装成一个Class对象）确定你要的方法
		@SuppressWarnings("unchecked")
		Method method2 = c1.getMethod("say", new Class[] { String.class, int.class });
		method2.invoke(s, new Object[] { "dj", 6 });

		// 无参构造函数
		ReflectBean o = (ReflectBean) c1.newInstance();
		ReflectTest re = new ReflectTest();
		ReflectBean bean1 = (ReflectBean) re.getObj("com.dj.base.ch06.ReflectBean");
		bean1.say();
		// 有参构造
		Constructor constructor = c1.getConstructor(new Class[] { String.class });
		ReflectBean bean2 = (ReflectBean) constructor.newInstance(new Object[] { "dj" });
		System.out.println(bean2.getPubName());
	}

	public Object getObj(String className)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class c = Class.forName(className);
		Object o = c.newInstance();
		return o;
//		return Class.forName(className).newInstance();
	}

}
