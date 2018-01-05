package cn.xiaodong.domain;
/**
 * 管理员用户类
 * 
 * @author 朱晓东
 *
 */
public class User {
	
	//管理员账户id
	private int id;
	
	//管理员用户名
	private String username;
	
	//管理员安全验证邮箱
	private String email;
	
	//安全验证码
	private String securityCode;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

}
