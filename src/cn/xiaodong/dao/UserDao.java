package cn.xiaodong.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.xiaodong.domain.User;
import cn.xiaodong.utils.DataSourceUtils;


/**
 * 管理员用户dao操作
 * 
 * @author 朱晓东
 *
 */
public class UserDao {
	
	/**
	 * 根据用户名和密码，查询到用户的详细信息
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static User getByUsernameAndPassword(String username, String password) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from user where username=? and password=? limit 1";
		return qr.query(sql, new BeanHandler<>(User.class),username,password);
	}

}
