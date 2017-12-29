package cn.xiaodong.service;

import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import cn.xiaodong.dao.CodeDao;
import cn.xiaodong.dao.QueryHistoryDao;
import cn.xiaodong.domain.Code;
import cn.xiaodong.domain.QueryHistory;
import cn.xiaodong.domain.QueryRequest;
import cn.xiaodong.utils.MailUtils;

/**
 * 防伪码查询的具体过程实现
 * @author 朱晓东
 *
 */
public class QueryService {

	public void Query(QueryRequest qr) throws SQLException, AddressException, MessagingException{
		
		boolean QueryResult = true;
		
		//1.查询数据库中是否有这个防伪码
		Code CodeQueried = CodeDao.getByCode(qr.getCode());
		
		//2.检查防伪码是否被查询过
		if(CodeQueried.getQueryTimes() == 0){
			QueryResult = true;
		}
		else{
			QueryResult = false;
			//3.如果被查询过，读取最后一次查询历史
			QueryHistory qh = QueryHistoryDao.getByCode(qr.getCode());
		}
			
		//4.生成查询结果信息
		String emailMsg = "";
		
		//5.将结果发送到用户提供的邮箱
		String email = qr.getEmail();
		String subject = "商品防伪码查询结果";
		MailUtils.sendMail(email, emailMsg, subject);
	}
}
