package cn.xiaodong.domain;

/**
 * 防伪码类
 * 
 * @author 朱晓东
 *
 */
public class Code {
	
	//防伪码id
	private String id;
	
	//防伪码内容
	private String code;
	
	//产品生产厂家
	private String company;
	
	//防伪码查询次数
	private int queryTimes;

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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getQueryTimes() {
		return queryTimes;
	}

	public void setQueryTimes(int queryTimes) {
		this.queryTimes = queryTimes;
	}

	
	

}
