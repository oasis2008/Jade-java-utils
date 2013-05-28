/**
 * 
 */
package jadeutils.extjs;

import jadeutils.message.MessageMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * @author SHAN013
 * 
 */
public class ModelCreater {

	public static Pattern FIELD_PATTERN = Pattern.compile("(\\w)+(\\w)+");

	public static void printConfigForExtJS(String modelName) {
		List<String[]> fieldList = loadModel(modelName);
		String text = genFields(modelName, fieldList);
		System.out.println(text);
	}

	public static List<String[]> loadModel(String modelName) {
		List<String[]> fieldList = new ArrayList<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(modelName));
			String line = br.readLine();// 一次读入一行，直到读入null为文件结束
			while (line != null) {
				Matcher m = FIELD_PATTERN.matcher(line);
				String[] strArr = new String[m.groupCount()];
				for (int i = 0; i < m.groupCount(); i++) {
					if (m.find()) {
						strArr[i] = m.group();
					}
				}
				if (StringUtils.isNotBlank(strArr[0])) {
					fieldList.add(strArr);
				}
				line = br.readLine(); // 接着读下一行
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != br) {
				try {
					br.close();
				} catch (Exception e2) {
					//
				}
			}
		}
		return fieldList;
	}

	public static String genFields(String modelName, List<String[]> fieldList) {
		StringBuffer sb = new StringBuffer();
		/* */
		sb.append("fields:[\n");
		MessageMap mm = new MessageMap("modelFields");
		for (int i = 0; i < fieldList.size(); i++) {
			String[] f = fieldList.get(i);
			if (null != f && f.length > 1 && StringUtils.isNotBlank(f[0])//
					&& StringUtils.isNotBlank(f[1])) {
				String line = mm.getMessage(f[0]);
				line = line.replaceAll("\\{fieldName\\}", f[1]);
				if (StringUtils.isNotBlank(line)) {
					sb.append("\t").append(line);
					if (i < fieldList.size() - 1) {
						sb.append(",");
					}
					sb.append("\n");
				}
			}
		}
		sb.append("]\n");
		/* */
		sb.append("fields:[\n");
		sb.append("\t{width: 50,hidden:false,sortable:false,xtype:'rownumberer',text:messages['common.table.seq']},\n");
		mm = new MessageMap("tableFields");
		for (int i = 0; i < fieldList.size(); i++) {
			String[] f = fieldList.get(i);
			if (null != f && f.length > 1 && StringUtils.isNotBlank(f[0])//
					&& StringUtils.isNotBlank(f[1])) {
				String line = mm.getMessage(f[0]);
				line = line.replaceAll("\\{modelName\\}", modelName);
				line = line.replaceAll("\\{fieldName\\}", f[1]);
				if (StringUtils.isNotBlank(line)) {
					sb.append("\t").append(line);
					if (i < fieldList.size() - 1) {
						sb.append(",");
					}
					sb.append("\n");
				}
			}
		}
		sb.append("]\n");
		/* */
		/* */
		sb.append("filterItems:[\n");
		mm = new MessageMap("tableFilter");
		for (int i = 0; i < fieldList.size(); i++) {
			String[] f = fieldList.get(i);
			if (null != f && f.length > 1 && StringUtils.isNotBlank(f[0])//
					&& StringUtils.isNotBlank(f[1])) {
				String line = mm.getMessage(f[0]);
				line = line.replaceAll("\\{fieldName\\}", f[1]);
				if (StringUtils.isNotBlank(line)) {
					sb.append("\t").append(line);
					if (i < fieldList.size() - 1) {
						sb.append(",");
					}
					sb.append("\n");
				}
			}
		}
		sb.append("]\n");

		/* */
		sb.append("i18n:\n");
		for (int i = 0; i < fieldList.size(); i++) {
			String[] f = fieldList.get(i);
			sb.append(modelName).append(".").append(f[1]).append("=\n");
		}
		sb.append("\n");
		return sb.toString();
	}
}
