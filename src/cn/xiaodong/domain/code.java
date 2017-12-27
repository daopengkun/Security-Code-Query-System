package cn.xiaodong.domain;

/**
 * 防伪码类
 * 
 * @author 朱晓东
 *
 */
public class code {
	
	//防伪码id
	private int id;
	
	//防伪码内容
	private char code;
	
	//产品生产厂家
	private char company;
	
	//防伪码查询次数
	private char queryTimes;


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
