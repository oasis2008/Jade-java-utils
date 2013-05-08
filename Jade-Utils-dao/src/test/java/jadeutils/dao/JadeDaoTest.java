package jadeutils.dao;

import org.junit.Test;

import test.BaseTest;

public class JadeDaoTest extends BaseTest {

	@Test
	public void test001Instance() throws Exception {
		ResidentDao rd = (ResidentDao) this.context.getBean("residentDao");
		System.out.println(rd.getAll().size());
	}

}
