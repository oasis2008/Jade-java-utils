/**
 * 
 */
package test;

import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author morgan
 * 
 */
public class BaseTest {
	private final String LOG4J_PATH = "src/test/resources/log4j.properties";

	public FileSystemXmlApplicationContext context = null;
	private String[] CONFIG_FILES = { "classpath:AppCtx.xml", };

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.context = new FileSystemXmlApplicationContext(this.CONFIG_FILES);
		PropertyConfigurator.configure(this.LOG4J_PATH);
		// DOMConfigurator.configure(this.LOG4J_PATH);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

}
