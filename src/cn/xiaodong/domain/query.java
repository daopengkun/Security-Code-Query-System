package cn.xiaodong.domain;

/**
 * 查询历史类
 * 
 * @author 朱晓东
 *
 */
public class query {
	
	//查询id	
	private int id;
	
	//被查询的验证码
	private char code;
	
	//本次查询使用的邮箱
	private char email;
	
	//本次查询的时间
	private char time;

	//Get和Set函数
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public char getCode() {
		return code;
	}

	public void setCode(char code) {
		this.code = code;
	}

	public char getEmail() {
		return email;
	}

	public void setEmail(char email) {
		this.email = email;
	}

	public char getTime() {
		return time;
	}

	public void setTime(char time) {
		this.time = time;
	}
	
	
}
