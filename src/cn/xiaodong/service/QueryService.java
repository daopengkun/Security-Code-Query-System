package cn.xiaodong.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import cn.xiaodong.dao.CodeDao;
import cn.xiaodong.dao.QueryHistoryDao;
import cn.xiaodong.domain.Code;
import cn.xiaodong.domain.QueryHistory;
import cn.xiaodong.domain.QueryRequest;
import cn.xiaodong.utils.MailUtils;
import cn.xiaodong.utils.UUIDUtils;

/**
 * 防伪码查询的具体过程实现
 * @author 朱晓东
 *
 */
public class QueryService {

	public static void Query(QueryRequest qr) throws SQLException, AddressException, MessagingException{
		
		boolean CodeExit; //防伪码真实存在  true
		boolean CodeQueried;//防伪码被查询过 true
		
		//1.查询数据库中是否有这个防伪码
		Code c = CodeDao.getByCode(qr.getCode());
		
		//2.1检查防伪码是否存在
		if ( c == null ){
			CodeExit = false;
		}else{
			CodeExit = true;
		}
		//2.2 检查防伪码是否被查询过
		if(c.getQueryTimes() != 0){
			CodeQueried = true;
		}
		else{
			CodeQueried = false;
			//3.如果被查询过，读取最后一次查询历史
			//QueryHistory qh = QueryHistoryDao.getByCode(qr.getCode());
		}
			
		//4.生成查询结果信息
		String emailMsg = ""+
		"您的查询结果如下：<br>"+
		"防伪码："+qr.getCode()+"<br>";
		
		String msg;
		if ( CodeExit == true ){
			msg = "正品";
			emailMsg+="产品真伪："+msg+"<br>";
			
			emailMsg+="生产公司："+c.getCompany()+"<br>";
			
			if ( CodeQueried == true ){
				msg = "您的防伪码被其他人查询过！";
			}else{
				msg = "这是本防伪码第一次被查询。";
			}
			emailMsg+="查询历史："+msg+"<br>";
			
		}else{
			msg = "假货";
			emailMsg+="产品真伪："+msg+"<br>";
		}
		
		//测试信息
		System.out.println(emailMsg);
		
		//5.将结果发送到用户提供的邮箱
		String email = qr.getEmail();
		String subject = "商品防伪码查询结果";
		MailUtils.sendMail(email, emailMsg, subject);
		
		//6.更新查询信息
		if (c != null ){
			//6.1 修改防伪码被查询的次数
			c.setQueryTimes( c.getQueryTimes()+1 );
			CodeDao.update(c);
		}
		
		//6.2 添加本次查询历史
		QueryHistory newqh = new QueryHistory();
		newqh.setId( UUIDUtils.getCode() );
		newqh.setCode( qr.getCode() );
		newqh.setEmail( qr.getEmail() );
		//获取本次查询的时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String time = df.format(new Date());// new Date()为获取当前系统时间
		newqh.setTime(time);
		
		QueryHistoryDao.add(newqh);
		
	}
}
