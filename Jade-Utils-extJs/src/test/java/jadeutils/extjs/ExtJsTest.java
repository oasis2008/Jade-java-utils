package jadeutils.extjs;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

import org.junit.Test;

public class ExtJsTest {

	@Test
	public void test() {
		String result = ModelCreater.genConfigForExtJS("OrderReturnModel");
		assertNotNull(result);
		System.out.println(result);
	}

	@Test
	public void testCmdParser() {
		OptionParser parser = new OptionParser("fc:q::");

		OptionSet options = parser.parse("-f", "-c", "foo", "-q");

		assertTrue(options.has("f"));

		assertTrue(options.has("c"));
		assertTrue(options.hasArgument("c"));
		assertEquals("foo", options.valueOf("c"));
		assertEquals(asList("foo"), options.valuesOf("c"));

		assertTrue(options.has("q"));
		assertFalse(options.hasArgument("q"));
		assertNull(options.valueOf("q"));
		assertEquals(emptyList(), options.valuesOf("q"));
	}
}
