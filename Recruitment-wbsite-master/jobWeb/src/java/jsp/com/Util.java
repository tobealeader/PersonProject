package jsp.com;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Random;

public class Util {
	public static void main(String[] args) {
		final Queue3 q3 = new Queue3(); //封装共享的数据、读写锁和待执行的任务的类

		for (int i = 0; i < 3; i++) {
			new Thread() { // 开启三个线程写数据
				public void run() {
					while (true) {
						q3.put(new Random().nextInt(10000));
					}
				}
			}.start();

			new Thread() { // 开启三个线程读数据
				public void run() {
					while (true) {
						q3.get();
					}
				}
			}.start();
		}
	}
}
class Queue3 {

	private Object data = null; // 共享的数据
//	private ReadWriteLock rwl = new ReentrantReadWriteLock();// 定义读写锁

	// 读取数据的任务方法
	public void get() {
//		rwl.readLock().lock(); // 上读锁
		try {
			System.out.println(Thread.currentThread().getName()
					+ ":before read: " + data); // 读之前打印数据显示

			Thread.sleep((long) (Math.random() * 1000)); // 睡一会儿~

			System.out.println(Thread.currentThread().getName()
					+ ":after read: " + data); // 读之后打印数据显示
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
//			rwl.readLock().unlock();// 释放读锁
		}
	}

	// 写数据的任务方法
	public void put(Object data) {
//		rwl.writeLock().lock(); // 上写锁
		try {
			System.out.println(Thread.currentThread().getName()
					+ ":before write: " + this.data); // 读之前打印数据显示

			Thread.sleep((long) (Math.random() * 1000)); // 睡一会儿~
			this.data = data; //写数据

			System.out.println(Thread.currentThread().getName()
					+ ":after write: " + this.data); // 读之后打印数据显示
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
//			rwl.writeLock().unlock();// 释放写锁
		}
	}
}
class GenericDAO<T> {
	private Class<T> entityClass;
	protected GenericDAO() {
		System.out.println(getClass().getGenericSuperclass());
		Type type = getClass().getGenericSuperclass();
		Type trueType = ((ParameterizedType) type).getActualTypeArguments()[0];
		this.entityClass = (Class<T>) trueType;
	}
}
class OptionManager extends GenericDAO<String> {
}
class OracleTest {
	public static void main(String[] args) throws Exception {
		OptionManager manager = new OptionManager();
	}
}
