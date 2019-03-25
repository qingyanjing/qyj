package springMvcTheory.thread;

import java.util.Date;
import java.util.concurrent.Callable;

/** 
* @author 作者：huangpeng E-mail: 
* @date 创建时间：2019年3月18日 下午5:55:37 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
public class MyCallable implements Callable<Object>{

	private String taskNum;
	
	public MyCallable(String taskNum) {
		this.taskNum = taskNum;
	}
	@Override
	public Object call() throws Exception {
		System.out.println(">>>" + taskNum + "任务启动");
	    Date startDate = new Date();
	    Thread.sleep(1000);
	    Date endDate = new Date();
	    long time = endDate.getTime() - startDate.getTime();
	    System.out.println(">>>" + taskNum + "任务终止");
	    return taskNum + "任务返回运行结果,当前任务时间【" + time + "毫秒】";

	}

}