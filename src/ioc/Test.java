package ioc;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ioc.annotation.b02byType.UseBean2ByType;
import ioc.annotation.b03constructor.UseBean3constructor;
import ioc.annotation.b04autodetect.UseBean4autodetect;

public class Test {

	public static void main(String[] args) {
//		byName();
//		byType();
//		BYconstructor();
		BYautodetect();
	}
	public static void byName(){
		System.out.println("-------------byName:正常使用的按照名称来匹配方式");
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("ioc_annotation.xml");
		ioc.annotation.b01byname.UseBean useBeanByName = (ioc.annotation.b01byname.UseBean)ac.getBean("useBeanByName");
		System.out.println("已经完成spring扫描");
		useBeanByName.use();
		ac.close();//单例可支持程序销毁
	}
	/**
	如果只写@Service，默认情况下相当于在 applicationContext.xml 中这样配置
	<bean id="zhHelloBean" class="tarena.demo5.ZhHelloBean"></bean>
	默认情况下，容器将类名 ZhHelloBean 首字母小写，作为<bean>的 id
	 */
	public static void byType(){
		System.out.println("-------------byType:按类型匹配");
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("ioc_annotation.xml");
		System.out.println("已经完成spring扫描");
		//只写 @Service 默认的是类名首字母变成小写的 id
		ioc.annotation.b02byType.HelloBean HelloBean = (ioc.annotation.b02byType.HelloBean)ac.getBean("helloBeanZH");
		System.out.println("第一、只@Service 注解 默认生成的名字是类名开头变小写：");HelloBean.printHello();
		HelloBean = (ioc.annotation.b02byType.HelloBean)ac.getBean("helloBeanEN");HelloBean.printHello();
		
		UseBean2ByType useBean2ByType = (UseBean2ByType) ac.getBean("useBean2ByType");
		System.out.println("第二、应用时只使用 @Resource ，会有符合条件两个实例进行竞争，会报错，必须进行指定名称");useBean2ByType.use();
		
		System.out.println("第三、若ioc.annotation.b01byname 和 ioc.annotation.b02byType 下不同包的 HelloBeanEN 若都只@Service 注解，则会报错，原因是有两个id一样的bean生成");
		ac.close();//单例可支持程序销毁
	}
	public static void BYconstructor(){
		System.out.println("-------------constructor:构造器匹配");
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("ioc_annotation.xml");
		UseBean3constructor useBean3constructor = (UseBean3constructor)ac.getBean("useBean3constructorXML");
		System.out.println("已经完成spring扫描");
		System.out.println("第一、只@Service 注解 默认生成的名字是类名开头变小写：");
		System.out.println("第二、使用构造器的自动装配模式;因为一个接口有多个实现，所以需要配置具体的名称，而使用注解不能完成");
		System.out.println("第三、xml中定义了id，而在类中也进行了注解姓名如@Service，则会按照注解的名称走(即类名开头小写)，这里因为需要构造器，所以只能走xml");
		useBean3constructor.use();
		ac.close();//单例可支持程序销毁
	}
	public static void BYautodetect(){
		System.out.println("-------------autodetect:先尝试用constructor匹配，若失败尝试byType匹配");
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("ioc_annotation.xml");
		UseBean4autodetect useBean4autodetectXML = (UseBean4autodetect)ac.getBean("useBean4autodetectXML");
		System.out.println("已经完成spring扫描");
		System.out.println("在构造函数和加载对象两处加@Autowired ，因为构造函数地方不能处理多个实现类的问题，故构造函数使用xml配置");
		useBean4autodetectXML.use();
		ac.close();//单例可支持程序销毁
	}
}
