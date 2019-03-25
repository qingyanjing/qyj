package springMvcTheory.webmvc.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import springMvcTheory.webmvc.annotation.HPAutowired;
import springMvcTheory.webmvc.annotation.HPController;
import springMvcTheory.webmvc.annotation.HPRequestMapping;
import springMvcTheory.webmvc.annotation.HPService;

/** 
* @author 作者：huangpeng E-mail: 
* @date 创建时间：2019年3月14日 下午3:00:55 
* @version 1.0 
* @parameter  
* @since  
* @return  
*   重写servlet的doget、dopost、init方法
*/
@SuppressWarnings("serial")
public class HPDispatcherServlet extends HttpServlet{
	
	//全局变量声明
	
	//属性配置
	private Properties contextConfig = new Properties();
	//类名集合
	private List<String> classNames = new ArrayList<String>();
	//这就是传说中的ioc容器
	private Map<String, Object> ioc = new HashMap<String, Object>();
	//初始化Handlermapping
	private Map<String, Method> handlermapping = new HashMap<String, Method>();
	
	@Override
	protected void doGet(HttpServletRequest res, HttpServletResponse req) throws ServerException
	{
		this.doPost(res, req);
	}
	
	@Override
	protected void doPost(HttpServletRequest res, HttpServletResponse req) throws ServerException
	{
		doDispacth(res, req);
	}
	
	private void doDispacth(HttpServletRequest res, HttpServletResponse req) {
		//获取到绝对路径
		String url = res.getRequestURI();
		String contextPath = res.getContextPath();
		//把绝对路径转换成相对路径
		url = url.replace(contextPath, "").replaceAll("/+", "/");
		//判断handlermapping中是否有这个url
		if(!this.handlermapping.containsKey(url)) {
			try {
				//没有到话返回404
				req.getWriter().write("404 not found!");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		//如果有url，获取到相应的方法
		Method method = this.handlermapping.get(url);
		
		System.out.println(method);
	}

	@Override
	public void init(ServletConfig config)
	{
		//搭框架
		
		//1、加载配置文件
		doLoadConfig(config.getInitParameter("contextConfigLocation"));
		//2、扫描所有的相关类
		doScanner(contextConfig.getProperty("scanPackage"));
		//3、初始化所有的相关类，并将所有的扫描到类实例化放入到ioc容器中
		doInstance();
		//4、自动化的依赖注入
		doAutowired();
		//5、初始化Handlermapping
		initHandlerMapping();
		//打印初始化完成信息
		System.out.println("HP spring mvc is init!!!");
	}

	private void initHandlerMapping() {
		//判断ioc是否为空
		if(ioc.isEmpty()) {
			return;
		}
		for(Map.Entry<String, Object> entry : ioc.entrySet()) {
			//获取所有的class
			Class<?> clazz = entry.getValue().getClass();
			// 判断注解是否是HPController
			if(!clazz.isAnnotationPresent(HPController.class)) {
				continue;
			}
			
			String baseUrl = "";
			if(clazz.isAnnotationPresent(HPRequestMapping.class)) {
				HPRequestMapping requestMapping = clazz.getAnnotation(HPRequestMapping.class);
				//获取到baseurl
				baseUrl = requestMapping.value();
			}
			//获取所有的方法
			Method[] methods = clazz.getMethods();
			//递归
			for (Method method : methods) {
				//判断方法是否加了ReauestMapping注解
				if(!method.isAnnotationPresent(HPRequestMapping.class)) {
					continue;
				}
				HPRequestMapping requestMapping = method.getAnnotation(HPRequestMapping.class);
				//获取到url,通过正则表达式来处理多斜杠处理成 一个斜杠
				String url = (baseUrl + requestMapping.value()).replaceAll("/+", "/");
				// 把url与method放入handlermapping中
				handlermapping.put(url, method);
				//打印出url与method
				System.out.println("Mpped " + url + "into " + method);
				
			}
		}
	}

	private void doAutowired() {
		//判断ioc容器是否为空
		if(ioc.isEmpty()) {
			return;
		}
		//递归
		for (Map.Entry<String, Object> entry : ioc.entrySet()) {
			//拿到所有的字段
			Field [] feFields = entry.getValue().getClass().getDeclaredFields();
			for (Field field : feFields) {
				if(!field.isAnnotationPresent(HPAutowired.class)) {
					continue;
				}
				HPAutowired autowired = field.getAnnotation(HPAutowired.class);
				//取到bean的名称
				String beanName = autowired.value();
				if("".equals(beanName.trim()))
				{
					beanName = field.getType().getName();
				}
				//直接赋值
				
				//如果这个字段是private，在反射面前都是透明的
				try {
					field.set(entry.getValue(), ioc.get(beanName));
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	private void doInstance() {
		//把之前保存的所有的类通过反射机制初始化，并且放到ioc容器中
		//1、判断有没有扫描到类
		if(classNames.isEmpty()) {
			return;
		}
		//递归classNames
		for (String className : classNames) {
			//第一步拿到class对象，接下来就可以反射
			try {
				Class<?> clazz = Class.forName(className);
				//通过反射机制把类的实例搞出来
				if(clazz.isAnnotationPresent(HPController.class)) {
					//获取到instance对象
					try {
						Object instance = clazz.newInstance();
						//把instance放入到ioc容器中
						//key的规则时首字母小写通过lowFirstCase实现,这个方法私有，自己用。
						String key = lowFirstCase(clazz.getSimpleName());
						//骚操作处理key
						ioc.put(key, instance);
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					
					
				}else if(clazz.isAnnotationPresent(HPService.class)) {
					//1、没有任何配置，类名首字母小写
					
					//2、如果自己之定义了名称或者beanid，优先使用自定义名称
					
					//3、子类对象复制给父类引用，根据接口类型来进行实例化（把接口类型这一串全类名作为一个key，把接口实现类作为value预先放到action容器中去，然后通过key取到这个接口的类型取出）
					
					//判断是否声明了自定义的名字
					HPService  service = clazz.getAnnotation(HPService.class);
					//取到bean的名称
					String beanName = service.value();
					if("".equals(beanName.trim())) {
						beanName = lowFirstCase(clazz.getSimpleName());
					}
					try {
						Object instance = clazz.newInstance();
						ioc.put(beanName, instance);
						//根据接口类型来进行实例化
						Class<?> [] interfaces = clazz.getInterfaces();
						//递归迭代
						for (Class<?> itf : interfaces) {
							ioc.put(itf.getName(), instance);
						}
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					
				}else {
					continue;
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * 处理类名首字母小写
	 * @param simpleName
	 * @return
	 */
	private String lowFirstCase(String simpleName) {
		char [] chars = simpleName.toCharArray();
		//由于大写字母与小写字母的Ascall码相差32
		chars[0] += 32;
		return String.valueOf(chars);
	}

	private void doScanner(String scanPackage) {
		// 拿到包名
		URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
		if(url != null)
		{
			// url转换成file
			File classDir = new File(url.getFile());
			// 递归扫描
			for (File file : classDir.listFiles()) {
				//如果是一个文件夹，说明是一个子包
				// 需要递归读取到子包下面的所有文件夹
				if(file.isDirectory()) {
					//扫描所有文件夹
					doScanner(scanPackage + "." + file.getName());
				}else{
					//去掉class文件的后缀
					String className = (scanPackage + "." + file.getName()).replace(".class", "");
					//把className放入classNameslist中
					classNames.add(className);
				}
			}
		}
	}

	private void doLoadConfig(String contextConfigLocation) {
		//拿到spring配置文件的路径，并读取文件中的所有内容
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
		try {
			contextConfig.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(is != null) {
				try {
					//关闭流
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
