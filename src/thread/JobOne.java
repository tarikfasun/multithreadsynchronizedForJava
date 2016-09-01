package thread;

import test.Test;

public class JobOne implements Runnable{

	@Override
	public void run() {
	Test test=new Test();
	try {
	
		test.printlnTh1(Thread.currentThread().getName());
		
		
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}

}
