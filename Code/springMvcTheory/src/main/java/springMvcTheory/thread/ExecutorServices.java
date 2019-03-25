package springMvcTheory.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/** 
* @author 作者：huangpeng E-mail: 
* @date 创建时间：2019年3月18日 下午5:44:41 
* @version 1.0 
* @parameter  
* @since  
* @return  
* @deprecated 有返回值的线程
*/
public class ExecutorServices {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws ExecutionException, InterruptedException{
		System.out.println("----程序开始运行----");
		Date startDate = new Date();
		int taskSize = 5;
		// 创建一个线程池
		ExecutorService pool = Executors.newFixedThreadPool(taskSize);
		// 创建多个有返回值的任务
		List<Future> list = new ArrayList<Future>();
		for(int i = 0;i < taskSize; i ++) {
			Callable callable = new MyCallable(i + "");
			// 执行任务并获取Future对象
			Future future  = pool.submit(callable);
			System.out.println(">>>" + future.get().toString());
			list.add(future);
		}
		// 关闭线程池 
		pool.shutdown();
		 // 获取所有并发任务的运行结果
		for(Future future : list) {
			// 从Future对象上获取任务的返回值，并输出到控制台
			System.out.println(">>>" + future.get().toString());
		}
		Date endDate = new Date();  
		System.out.println("----程序结束运行----，程序运行时间【"  + (endDate.getTime() - startDate.getTime()) + "毫秒】");

	}

}
