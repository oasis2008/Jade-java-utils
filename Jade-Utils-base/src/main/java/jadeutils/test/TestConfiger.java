package jadeutils.test;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Properties;

public class TestConfiger {
	// 配置文件位置
	private static final String CONF_FILE_PROP = "test-conf.properties";

	private static String TEST_LEVEL;

	static {
		try {
			InputStream confIn = new BufferedInputStream(Thread.currentThread()
					.getContextClassLoader()
					.getResourceAsStream(CONF_FILE_PROP));
			Properties p = new Properties();
			p.load(confIn);
			TEST_LEVEL = p.getProperty("test.conf.level", "");
		} catch (Exception e) {
			// TODO: do Log
		}
	}

	/**
	 * 判断是否在测试级别
	 * 
	 * @param level
	 * @return
	 */
	public static boolean isTestLevel(String level) {
		return null != level && null != TEST_LEVEL //
				&& TEST_LEVEL.equals(level);
	}

	/**
	 * 设置测试级别
	 * 
	 * @param level
	 */
	public static void setTestLevel(String level) {
		TEST_LEVEL = level;
	}

}
