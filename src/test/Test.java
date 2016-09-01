package test;



import thread.JobOne;
import thread.JobTwo;

public class Test {

	private static volatile int  sync = 0;

	private static  int  index = 0;

	public static void main(String[] args) {

		JobOne job1 = new JobOne();
		JobTwo job2 = new JobTwo();
		Thread thrd1 = new Thread(job1);
		Thread thrd2 = new Thread(job2);
		thrd1.start();
		thrd2.start();

	}

	public synchronized boolean synchronizeToOne() {

		if (sync == 0) {
            sync = 1;
			System.out.println(Thread.currentThread().getName() + " take and set 1");
			return true;
		} else
			return false;

	}
	
	
	public synchronized boolean synchronizeToZero() {

		if (sync == 1) {

			sync = 0;
			System.out.println(Thread.currentThread().getName() + " leave and set 0");
			return true;
		} else
			return false;

	}

	public void printlnTh1(String log) throws InterruptedException {
		if (synchronizeToOne()) {
			
			System.out.println(log);
			Thread.sleep(30000);
			if(synchronizeToZero()){
			System.out.println("job finished by Thread 0........");
			return;
			}
			
		}

		else {
			System.out.println("Thread 0 waiting........");
			Thread.sleep(1000);
			printlnTh1(Thread.currentThread().getName());

		}

	}

	public void printlnTh2(String log) throws InterruptedException {
		if (synchronizeToOne()) {
			System.out.println(log);
			Thread.sleep(30000);
			if(synchronizeToZero()){
				System.out.println("job finished by Thread 1........");
				return;
				}
		} else {
			System.out.println("Thread 1 wait........");
			Thread.sleep(1000);
			printlnTh2(Thread.currentThread().getName());

		}

	}
}
