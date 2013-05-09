package jadeutils.reflect;

import java.lang.reflect.Method;

public class BeanUtils {

	public static Object getFieldValue(Object bean, String fieldName) {
		Object value = null;
		try {
			String getterName = (new StringBuffer("get"))
					.append(fieldName.substring(0, 1).toUpperCase())//
					.append(fieldName.substring(1)).toString();
			for (Method m : bean.getClass().getMethods()) {
				if (getterName.equals(m.getName())) {
					value = m.invoke(bean);
				}
			}
		} catch (Exception e) {
			// do nothing
		}
		return value;
	}

	/**
	 * 复制bean对象的值
	 * 
	 * @param sb
	 *            原对象
	 * @param tb
	 *            目标对象
	 * @author
	 */
	public static void copyBean(Object sb, Object tb) {

		Method[] mthsSource = sb.getClass().getMethods();

		Method[] mthsTarget = tb.getClass().getMethods();
		for (Method method : mthsTarget) {
			if (method.getName().startsWith("set")) {
				// 复制成员变量值
				copyValue(method, mthsSource, sb, tb);
			}
		}

	}

	/**
	 * 复制成员变量值
	 * 
	 * @param methodTarget
	 *            目标对象的方法
	 * @param mthsSource
	 *            原对象方法数组
	 * @param sb
	 *            原对象
	 * @param tb
	 *            目标对象
	 * @author
	 */
	private static void copyValue(Method methodTarget, Method[] mthsSource,
			Object sb, Object tb) {

		String name = "get" + methodTarget.getName().substring(3);
		try {
			for (Method method : mthsSource) {
				if (name.equals(method.getName())) {
					methodTarget.invoke(tb, method.invoke(sb));
					break;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
