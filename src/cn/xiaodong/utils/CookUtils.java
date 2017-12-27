package cn.xiaodong.utils;

//手动删除大量的代码

//这一行代码和PC2上面写的是不一样的，肯定有冲突

import javax.servlet.http.Cookie;

public class CookUtils {
	/**
	 * 通过名称在cookie数组获取指定的cookie
	 * @param name cookie名称
	 * @param cookies  cookie数组
	 * @return
	 */
	public static Cookie getCookieByName(String name, Cookie[] cookies) {
		if(cookies!=null){
			for (Cookie c : cookies) {
				//通过名称获取
				if(name.equals(c.getName())){
					//返回
					return c;
				}
			}
		}
		return null;
	}
}


//在文本的末尾，在PC1上添加本行代码，但是PC2上是没有本行代码的