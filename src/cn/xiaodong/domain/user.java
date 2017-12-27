package cn.xiaodong.domain;
/**
 * 管理员用户类
 * 
 * @author 朱晓东
 *
 */
public class user {
	
	//管理员账户id
	private int id;
	
	//管理员用户名
	private char username;
	
	//管理员安全验证邮箱
	private char email;
	
	//安全验证码
	private char securityCode;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public char getUsername() {
		return username;
	}

	public void setUsername(char username) {
		this.username = username;
	}

	public char getEmail() {
		return email;
	}

	public void setEmail(char email) {
		this.email = email;
	}

	public char getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(char securityCode) {
		this.securityCode = securityCode;
	}

}
