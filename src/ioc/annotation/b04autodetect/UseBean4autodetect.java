/*
 * userBean.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package ioc.annotation.b04autodetect;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

import ioc.annotation.b01byname.HelloBean;

/**
 * 
 * @author LT
 * @version 1.0, 2015年10月22日
 */
@Scope(value="singleton")
public class UseBean4autodetect {
	
	@Autowired
	@Qualifier("helloBeanZHByName")
	private HelloBean helloBean;
	
	public void use(){
		helloBean.printHello();
	}

	/**
	 * 注解可以不使用setter
	 */
//	public void setHelloBean(HelloBean helloBean) {
//		this.helloBean = helloBean;
//	}
	/**
	 * 创建
	 */
//	@Autowired
	public UseBean4autodetect(HelloBean hb) {
		System.out.println("b04autodetect：使用构造器进行装配，注解不能实现多个继承类的情况，故使用xml");
		this.helloBean = hb;
	}
	
	public UseBean4autodetect() {
	
	}
	
	/**
	 * 初始化
	 */
	@PostConstruct
	public void init(){
//		System.out.println(" init... UseBean3constructor");
	}
	/**
	 * 销毁
	 */
	@PreDestroy
	public void destroy(){
//		System.out.println(" destroy... UseBean3constructor");
	}
	
}
