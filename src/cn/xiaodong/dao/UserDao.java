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
	
	public User getByUsernameAndPassword(String username, String password) throws Exception {
		// TODO 自动生成的方法存根
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from user where username=? and password=? limit 1";
		return qr.query(sql, new BeanHandler<>(User.class),username,password);
	}

}
