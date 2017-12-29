package cn.xiaodong.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;


import cn.xiaodong.domain.Code;
import cn.xiaodong.utils.DataSourceUtils;

/**
 * 防伪码dao操作
 * 
 * @author 朱晓东
 *
 */
public class CodeDao {
	
	/**
	 * 根据防伪码编号，查询完整的防伪码信息
	 * @param code 防伪码编号
	 * @return
	 * @throws SQLException
	 */
	public static Code getByCode(String code) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from code where code=? limit 1";
		return qr.query(sql, new BeanHandler<>(Code.class),code);
	}

}
