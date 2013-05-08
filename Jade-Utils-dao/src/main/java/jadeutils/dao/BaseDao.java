package jadeutils.dao;

import java.util.List;
import java.util.Map;

public interface BaseDao<T> {
	/**
	 * 根据主键取出记录
	 * 
	 * @param id
	 *            整数主键
	 * @return 对象
	 */
	public T getByIntId(Number id);

	/**
	 * 根据主键取出记录
	 * 
	 * @param id
	 *            整数主键
	 * @return 对象
	 */
	public T getByStringId(String id);

	/**
	 * 取出所有的记录
	 * 
	 * @return 所有的记录
	 */
	public List<T> getAll();

	/**
	 * 根据model新建记录
	 * 
	 * @param model
	 *            model
	 */
	public void insert(Object model);

	/**
	 * 根据model更新记录
	 * 
	 * @param model
	 *            model
	 */
	public void update(Object model);

	/**
	 * 根据model删除记录
	 * 
	 * @param model
	 *            model
	 */
	public void delete(Object model);

	/**
	 * 根据model新建或更新记录
	 * 
	 * @param model
	 *            model
	 */
	public void insertOrUpdate(Object model);

	/**
	 * 根据分页查询
	 * 
	 * @param sql
	 *            查询的sql
	 * @param conditions
	 *            查询条件
	 * @param dto
	 *            保存分页信息结构
	 * @return
	 * @throws JadeDaoException
	 */
	public <K> List<K> findBySql(String sql, Map<String, Object> conditions,
			PageSplitDto dto) throws JadeDaoException;

	/**
	 * 在分布查询前取得结果的总数
	 * 
	 * @param hql
	 * @param conditions
	 * @return
	 * @throws JadeDaoException
	 */
	public int getSqlResultCount(String sql, Map<String, Object> conditions)
			throws JadeDaoException;

	/**
	 * 测试连接
	 */
	public T test();
}
