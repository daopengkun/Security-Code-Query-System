package cn.xiaodong.domain;

/**
 * 查询历史类
 * 
 * @author 朱晓东
 *
 */
public class QueryHistory {
	
	//查询历史id
	private String id;
	
	//查询的防伪码
	private String code;
	
	//本次接受查询结果使用的邮箱
	private String email;
	
	//本次查询的时间
	private String time;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	
	
}
