package com.novelot.lib;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 流式反射
 * 
 * @author 刘云龙
 *
 */
public class Reflect {

	/**
	 * 反射调用
	 * 
	 * @param obj
	 * @return
	 */
	public static Reflect on(Object obj) {
		return new Reflect(obj);
	}

	public static Reflect on(String className) throws Exception {
		return on(className, null);
	}

	public static Reflect on(String className, Object... initargs) throws Exception {
		Class<?> clazz = Class.forName(className);
		if (initargs == null) {
			Constructor<?> constructor = clazz.getDeclaredConstructor(null);
			constructor.setAccessible(true);
			return on(constructor.newInstance(null));
		} else {
			int length = initargs.length;
			Class[] paramClazz = new Class[length];
			for (int i = 0; i < length; i++) {
				paramClazz[i] = initargs[i].getClass();
			}
			Constructor<?> constructor = clazz.getDeclaredConstructor(paramClazz);
			constructor.setAccessible(true);
			return on(constructor.newInstance(initargs));
		}
	}

	private Object obj;
	private Object result;

	private Reflect(Object obj) {
		this.obj = obj;
	}

	/**
	 * 调用方法，不出入参数
	 * 
	 * @param methodName
	 */
	public Reflect call(String methodName) {
		return call(methodName, null);
	}

	/**
	 * 调用方法，并出入参数
	 * 
	 * @param methodName
	 * @param param
	 */
	public Reflect call(String methodName, Object... param) {
		if (this.obj == null)
			return this;
		try {
			Method method;
			if (param == null) {
				method = this.obj.getClass().getDeclaredMethod(methodName, null);
				method.setAccessible(true);
				Class<?> returnType = method.getReturnType();
				this.result = returnType.cast(method.invoke(this.obj, null));

			} else {
				int length = param.length;
				Class[] paramClazz = new Class[length];
				for (int i = 0; i < length; i++) {
					paramClazz[i] = param[i].getClass();
				}
				method = this.obj.getClass().getDeclaredMethod(methodName, paramClazz);
				method.setAccessible(true);
				Class<?> returnType = method.getReturnType();
				this.result = returnType.cast(method.invoke(this.obj, param));
			}

		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}

		return this;
	}

	public Object get() {
		return this.result;
	}

	/**
	 * 设置字段值
	 * 
	 * @param fieldName
	 * @param value
	 */
	public Reflect set(String fieldName, Object value) {
		try {
			Field field = obj.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(obj, value);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}

		return this;
	}

	/**
	 * 获取字段值
	 * 
	 * @param fieldName
	 * @return
	 */
	public Object get(String fieldName) {
		Object result = null;
		try {
			Field field = obj.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			result = field.get(obj);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;

	}

}
