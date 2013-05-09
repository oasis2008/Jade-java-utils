package jadeutils.reflect;

import java.lang.reflect.Method;

public class BeanUtils {

	public static Object getFieldValue(Object bean, String fieldName)
			throws Exception {
		String getterName = (new StringBuffer("get"))
				.append(fieldName.substring(0, 1).toUpperCase())//
				.append(fieldName.substring(1)).toString();

		return bean.getClass().getDeclaredMethod(getterName).invoke(bean);
	}

	/**
	 * 
	 * @param srcBean
	 * @param tagBean
	 * @param fieldName
	 */
	public static void copyField(Object srcBean, Object tagBean,
			Class<?> fieldType, String fieldName) throws Exception {
		String getterName = (new StringBuffer("get"))
				.append(fieldName.substring(0, 1).toUpperCase())//
				.append(fieldName.substring(1)).toString();
		String setterName = (new StringBuffer("set"))
				.append(fieldName.substring(0, 1).toUpperCase())//
				.append(fieldName.substring(1)).toString();
		Method setter = tagBean.getClass().getDeclaredMethod(setterName,
				fieldType);
		Method getter = srcBean.getClass().getDeclaredMethod(getterName);
		setter.invoke(tagBean, getter.invoke(srcBean));
	}

	/**
	 * 复制bean对象的值
	 * 
	 * @param srcBean
	 *            原对象
	 * @param tb
	 *            目标对象
	 * @author
	 */
	public static void copyBean(Object srcBean, Object tagBean)
			throws Exception {
		Method[] mthsTarget = tagBean.getClass().getMethods();
		for (Method setter : mthsTarget) {
			if (setter.getName().startsWith("set")) {
				String name = "get" + setter.getName().substring(3);
				Method getter = srcBean.getClass().getDeclaredMethod(name);
				setter.invoke(tagBean, getter.invoke(srcBean));
			}
		}
	}

}
