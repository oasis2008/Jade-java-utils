/**
 * 
 */
package jadeutils.dao;

import jadeutils.dao.impl.BaseHibernateDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

/**
 * @author morgan
 * 
 */

@Component("residentDao")
public class ResidentDao extends BaseHibernateDao<Resident> {

	@Override
	@Resource(name = "hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		System.out.println(hibernateTemplate);
		this.configHibernateTemplate(hibernateTemplate);
	}

	public List<Resident> findDetail(ResidentDto dto) throws JadeDaoException {
		List<Resident> resultList = new ArrayList<Resident>();
		Map<String, Object> conditions = new HashMap<String, Object>();
		String sql = "select id, userName, password, nick,createTime from Resident";
		List<Object[]> recs = this.findBySql(sql, conditions, dto);
		if (null != recs && recs.size() > 0) {
			for (Object[] rec : recs) {
				Resident r = new Resident();
				int c = 0;
				r.setId(null == rec[c] ? null : (String) rec[c]);
				c++;
				r.setUserName(null == rec[c] ? null : (String) rec[c]);
				c++;
				r.setPassword(null == rec[0] ? null : (String) rec[0]);
				c++;
				r.setNick(null == rec[0] ? null : (String) rec[0]);
				c++;
				resultList.add(r);
			}
		}
		return resultList;
	}
}
