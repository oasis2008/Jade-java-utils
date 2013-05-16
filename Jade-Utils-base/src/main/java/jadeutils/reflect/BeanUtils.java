package jadeutils.reflect;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

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

	private static String getPropLabel(String methodName) {
		String result = "";
		if (methodName.startsWith("set") || methodName.startsWith("get")) {
			result = methodName.substring(3);
		}
		return result;
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
		Map<String, Method> getterMap = new HashMap<String, Method>();
		for (Method m : srcBean.getClass().getMethods()) {
			String name = getPropLabel(m.getName());
			if (StringUtils.isNotBlank(name)) {
				getterMap.put(name, m);
			}
		}
		Map<String, Method> setterMap = new HashMap<String, Method>();
		for (Method m : tagBean.getClass().getMethods()) {
			String name = m.getName();
			if (StringUtils.isNotBlank(name)) {
				setterMap.put(name, m);
			}
		}
		for (Entry<String, Method> t : setterMap.entrySet()) {
			Method setter = t.getValue();
			Method getter = getterMap.get(t.getKey());
			if (null != getter) {
				try {
					setter.invoke(tagBean, getter.invoke(srcBean));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
