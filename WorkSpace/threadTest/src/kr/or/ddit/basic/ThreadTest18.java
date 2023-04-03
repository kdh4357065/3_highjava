package kr.or.ddit.basic;

/*
 wait(), notify()메서드를 이용한 두 쓰레드가 번갈아 한번씩 실행되는 예제
 
 wait(), notify(), notifyAll()메서드는 동기화 영역에서만 사용 가능하다.
 */
public class ThreadTest18 {

	public static void main(String[] args) {
		WorkObject workObj = new WorkObject();
		
		ThreadA thA = new ThreadA(workObj);
		ThreadB thB = new ThreadB(workObj);
		
		thA.start();
		thB.start();

	}

}

//공통으로 사용할 객체
class WorkObject{
	public synchronized void testMethodA() {
		System.out.println("TestMethodA() 메서드 실행중...");
		notify();
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
	
	public synchronized void testMethodB() {
		System.out.println("TestMethodB() 메서드 실행중...");
		notify();
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
}

//WorkObject의 testMethodA()메서드만 호출하는 쓰레드
class ThreadA extends Thread{
	private WorkObject workObj;
	
	public ThreadA(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			workObj.testMethodA();
		}
		//마지막에 waiting pool에 있는 쓰레드 깨워주기
		synchronized (workObj) { //notify는 동기화 영역에서만 실행되기 때문에 동기화 영역을 만들어서 사용
			workObj.notify();			
		}
	}
}

//WorkObject의 testMethodB()메서드만 호출하는 쓰레드
class ThreadB extends Thread{
	private WorkObject workObj;
	
	public ThreadB(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			workObj.testMethodB();
		}
		//마지막에 waiting pool에 있는 쓰레드 깨워주기
		synchronized (workObj) {
			workObj.notify();			
		}
	}
}

