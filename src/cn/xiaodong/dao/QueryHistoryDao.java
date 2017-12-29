package cn.xiaodong.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.xiaodong.domain.QueryHistory;
import cn.xiaodong.utils.DataSourceUtils;


/**
 * 防伪码查询历史dao操作
 * 
 * @author 朱晓东
 *
 */
public class QueryHistoryDao {

	/**
	 * 根据防伪码，获得查询历史
	 * @param code
	 * @return
	 * @throws SQLException
	 */
	public static QueryHistory getByCode(String code) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from query where code=? limit 1";
		return qr.query(sql, new BeanHandler<>(QueryHistory.class),code);
		// TODO 自动生成的方法存根
		
	}
}
