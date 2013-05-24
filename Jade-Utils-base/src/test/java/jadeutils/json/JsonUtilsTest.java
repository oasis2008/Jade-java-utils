/**
 * 
 */
package jadeutils.json;

import jadeutils.reflect.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author SHAN013
 * 
 */
public class JsonUtilsTest {
	private static JsonUtils util = JsonUtils.newInstance();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		User a = new User();
		a.setId("001");
		a.setName("aa");
		a.setAge(20);
		a.setIsAdmin(false);
		a.setActived(true);
		System.out.println(util.toJson(a));
	}

}
