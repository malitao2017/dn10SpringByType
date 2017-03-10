/*
 * userBean.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package ioc.annotation.b01byname;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 
 * @author LT
 * @version 1.0, 2015年10月22日
 */
@Service("useBeanByName")
@Scope(value="singleton")
public class UseBean {
	
	@Resource(name="helloBeanZHByName")//推荐使用
	
//	@Autowired
//	@Qualifier("helloBeanZHByName")
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
	public UseBean() {
//		System.out.println(" 已经创建 UseBean");
	}
	/**
	 * 初始化
	 */
	@PostConstruct
	public void init(){
//		System.out.println(" init... UseBean");
	}
	/**
	 * 销毁
	 */
	@PreDestroy
	public void destroy(){
//		System.out.println(" destroy... UseBean");
	}
	
}
