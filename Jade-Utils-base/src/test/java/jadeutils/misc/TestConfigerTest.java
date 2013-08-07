/**
 * 
 */
package jadeutils.misc;

import static org.junit.Assert.*;
import jadeutils.test.TestConfiger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author SHAN013
 *
 */
public class TestConfigerTest {

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
	public void testLevelConfig() {
		assertTrue(TestConfiger.isTestLevel("unit"));
		TestConfiger.setTestLevel("module");
		assertTrue(TestConfiger.isTestLevel("module"));
	}

}
