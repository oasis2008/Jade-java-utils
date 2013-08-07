package jadeutils.dao;

import jadeutils.test.TestConfiger;

import java.util.Date;
import java.util.List;

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
	public void testInsert() throws Exception {
		if (TestConfiger.isTestLevel("module")) {
			for (int i = 0; i < 100; i++) {
				Resident r = new Resident();
				r.setId("UID" + i);
				r.setNick("user" + i);
				r.setUserName("user" + i);
				r.setPassword("user" + 1);
				r.setStatus(ResidentDto.Statue.ACTIVE.name());
				r.setCreateTime(new Date());
				this.residentDao.insert(r);
			}
		}
	}

	@Test
	public void test001Instance() throws Exception {
		System.out.println(this.residentDao.getAll().size());
	}

	@Test
	public void testFind() throws Exception {
		if (TestConfiger.isTestLevel("module")) {
			QueryConditions cdts = new QueryConditions();
			cdts.setCurrPageNo(1);
			cdts.setPageSize(10);
			cdts.addCondition("status", ResidentDto.Statue.ACTIVE.name());
			cdts.addCondition("nick", QueryConditions.MatchType.LK, "%1%");
			List<Resident> recs = this.residentDao.findBySql(cdts);
			for (Resident r : recs) {
				System.out.println(r.getNick());
			}
			recs = this.residentDao.findByHql(cdts);
			for (Resident r : recs) {
				System.out.println(r.getNick());
			}
		}
	}

}
