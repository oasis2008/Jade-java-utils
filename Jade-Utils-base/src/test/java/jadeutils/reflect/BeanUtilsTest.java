/**
 * 
 */
package jadeutils.reflect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author SHAN013
 * 
 */
public class BeanUtilsTest {

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
	public void testGetBeanField() throws Exception {
		User a = new User();
		a.setId("001");
		a.setName("aa");
		a.setAge(20);
		a.setIsAdmin(false);
		a.setActived(true);
		assertEquals("001", BeanUtils.getFieldValue(a, "id"));
		assertEquals("aa", BeanUtils.getFieldValue(a, "name"));
		assertEquals(20, BeanUtils.getFieldValue(a, "age"));
		assertEquals(false, BeanUtils.getFieldValue(a, "isAdmin"));
		assertEquals(true, BeanUtils.getFieldValue(a, "actived"));
		//
		a.setName(null);
		assertNull(BeanUtils.getFieldValue(a, "name"));
	}

	@Test(expected = NoSuchMethodException.class)
	public void testGetBeanFieldException() throws Exception {
		User a = new User();
		a.setId("001");
		a.setName("aa");
		a.setAge(20);
		a.setIsAdmin(false);
		a.setActived(true);
		assertNull(BeanUtils.getFieldValue(a, "noThisField"));
	}

	@Test
	public void testCopyField() throws Exception {
		User a = new User();
		a.setId("001");
		a.setName("aa");
		a.setAge(20);
		a.setIsAdmin(false);
		a.setActived(true);
		User b = new User();
		BeanUtils.copyField(a, b, String.class, "id");
		assertEquals("001", b.getId());
		BeanUtils.copyField(a, b, Integer.class, "age");
		assertEquals(new Integer(20), b.getAge());
		BeanUtils.copyField(a, b, Boolean.class, "actived");
		assertEquals(new Boolean(true), b.getActived());
	}

	@Test
	public void testCopyBean() throws Exception {
		User a = new User();
		a.setId("aa");
		a.setName("aa");
		a.setAge(20);
		a.setIsAdmin(false);
		a.setActived(true);
		User b = new User();
		BeanUtils.copyBean(a, b);
		assertTrue(a.equals(b));
	}

}