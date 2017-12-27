package cn.xiaodong.domain;

/**
 * 防伪码类
 * 
 * @author 朱晓东
 *
 */
public class code {
	
	//防伪码编号
	private int id;
	
	//产品防伪码编号
	private char code;
	
	//产品生产公司	
	private char company;
	
	//防伪码查询的次数
	private char queryTimes;

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

	public char getCompany() {
		return company;
	}

	public void setCompany(char company) {
		this.company = company;
	}

	public char getQueryTimes() {
		return queryTimes;
	}

	public void setQueryTimes(char queryTimes) {
		this.queryTimes = queryTimes;
	}
	
	

}
