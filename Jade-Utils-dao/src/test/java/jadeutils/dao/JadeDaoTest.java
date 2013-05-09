package jadeutils.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:AppCtx*.xml" })
public class JadeDaoTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	ResidentDao residentDao;

	@Test
	public void test001Instance() throws Exception {
		System.out.println(this.residentDao.getAll().size());
	}

}
