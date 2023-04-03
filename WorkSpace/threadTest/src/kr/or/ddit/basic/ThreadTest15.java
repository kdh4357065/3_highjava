package kr.or.ddit.basic;

public class ThreadTest15 {

	public static void main(String[] args) {
		ShareObject sObj = new ShareObject();//공통 객체 생성
		
		TestThread th1 = new TestThread("1번쓰레드", sObj);
		TestThread th2 = new TestThread("2번쓰레드", sObj);
		
		th1.start();
		th2.start();

	}

}

//공통으로 사용할 클래스
class ShareObject{
	private int sum = 0;
	
	//동기화 처리
//	public synchronized void add() { //방법1 : 메서드에 동기화 설정하기
	public void add() {
		synchronized (this) { //this자리에는 동기화 시켜줄 객체를 넣어주는데 현재는
			                  //ShareObject기 때문에 this를 넣어준다.
			int n = sum;      //방법2 : 동기화 블럭으로 설정하기
			
			n += 10; //10씩 증가
			
			sum = n;
			
			System.out.println(Thread.currentThread().getName() + "합계 : " + sum);
		}
	}
}

//처리할 쓰레드 클래스
class TestThread extends Thread{
	private ShareObject sObj;
	
	//생성자
	public TestThread(String name, ShareObject sObj) {
		super(name);
		this.sObj = sObj;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			sObj.add();
		}
	}
}