package jadeutils.cache;

import jadeutils.test.TestConfiger;
import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BaseXmemcachedTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test001Instance() throws Exception {
		if (TestConfiger.isTestLevel("module")) {
			BaseCacheFactory<String> factory = new BaseCacheFactory<String>();
			BaseCache<String> cache = factory
					.createCache("jadeutils.cache.impl.XmemcachedImpl");
			Assert.assertEquals("jadeutils.cache.impl.XmemcachedImpl",
					cache.getImplTypeName());
			cache.shutdown();
			//
			cache = factory.createCache("jadeutils.cache.impl.EhCacheImpl");
			Assert.assertEquals("jadeutils.cache.impl.EhCacheImpl",
					cache.getImplTypeName());
			cache.shutdown();
		}
	}

	@Test
	public void test002GetEntry() throws Exception {
		if (TestConfiger.isTestLevel("module")) {
			BaseCacheFactory<String> factory = new BaseCacheFactory<String>();
			BaseCache<String> cache = factory
					.createCache("jadeutils.cache.impl.XmemcachedImpl");
			//
			cache.set("hello", "hello, xmemcached", 0);
			Assert.assertEquals("hello, xmemcached", cache.get("hello"));
			//
			cache.delete("hello");
			Assert.assertNull(cache.get("hello"));
			//
			cache.shutdown();
		}
	}
}
