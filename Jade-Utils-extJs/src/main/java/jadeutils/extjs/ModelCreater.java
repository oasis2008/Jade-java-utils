/**
 * 
 */
package jadeutils.extjs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SHAN013
 * 
 */
public class ModelCreater {

	public static List<String[]> loadModel(String fileName) {
		List<String[]> fieldList = new ArrayList<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileName));
			String line = br.readLine();// 一次读入一行，直到读入null为文件结束
			while (line != null) {
				System.out.println(line);
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
}
