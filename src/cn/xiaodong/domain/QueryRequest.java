package cn.xiaodong.domain;

/**
 * 查询请求类
 * 包含用户查询时提交的表单信息
 * @author 朱晓东
 *
 */
public class QueryRequest {

	//存贮用户提交的防伪码
	private String code;
	
	//存贮用户提交的邮箱
	private String email;

	
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
	
	
}
