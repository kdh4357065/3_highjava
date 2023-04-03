package kr.or.ddit.basic.enumTest;

/*
    enum(열거형) ==>  서로 관련있는 상수드르이 집합을 나타낸다.
           ==> 클래스처럼 보이게하는 상수
           ==> 열거형은 클래스처럼 독립된 java파일에 만들 수 있고,
               하나의 java파일에 클래스와 같이 만들 수 있고,
               클래스 안에 내부 클래스처럼 만들 수 있다.
               
    - 열거형의 속성 및 메서드
      name() ==> 열거형 상수의 이름을 문자열로 반환한다.
      ordinal() ==> 열거형 상수가 정의된 순서값(index)을 반환한다. (0부터 시작)
      valueOf("열거형상수명") ==> 지정된 열거형에서 '열거형상수명'과 일지하는 
                              일치하는 열거형 상수를 반환한다.           
      열거형이름.상수명 ==> valueOf()메서드와 같다.
      열거형이름.values() ==> 모든 열거형 상수들을 배열로 반환한다.
      
    - 열거형 선언하기
    방법1)
       enum 열거형이름 {상수명1, 상수명2, 상수명3, ...}
    방법2)
       enum 열거형이름{
           상수명1(설정할 값들,...),
           상수명2(설정할 값들,...),
           ...
           상수명n(설정할 값들,...);
           
           //'값들'이 저장도리 변수들 선언
           private 자료형이름 변수명;
           ...
           
           //열거형의 생성자를 만든다.
           //열거형의 생성자는 '열거형 상수'의 '값들'을 변수에 세팅하는 역할을 한다.
           //열거형 생성자는 묵시적으로 private이다.
            
           //'변수명'의 개수는 '값들'의 개수와 같고, 자료형도 각각 맞아야 된다. 
           private 열거형이름(자료형 변수명.///){
               위에 선언된 변수들을 초기화한다.
               ...
           }
           
           //구성된 '값들'이 외부에서 불러올 수 있는 getter메서드를 만든다.
           
           
           
       }
*/

public class EnumTest {
	public enum Color{RED, GREEN, BLUE}
	public enum Count{ONE, TWO, THREE}
	
	public enum Season{
		//상수명 (값들...)형식의 선언
		봄("3월부터 5월까지", 15),
		여름("6월부터 8월까지", 32),
		가을("9월부터 11월까지", 17),
		겨울("12월부터 2월까지", -4);
		
		//'값들'이 저장될 변수들 선언
		private String span;
		private int temp;
		
		//생성자
		Season(String span, int temp){//==>private Season(String span, int temp)과 같다.
			this.span = span;
			this.temp = temp;
		}

		//getter메서드 만들기
		public String getSpan() {
			return this.span;
		}


		public int getTemp() {
			return this.temp;
		}
	}

	public static void main(String[] args) {
//		System.out.println("RED : " + ConstTest.RED);
//		System.out.println("THREE : " + ConstTest.THREE);
//		
//		if(ConstTest.RED == ConstTest.THREE) {
//			System.out.println("....");
//		}else {
//			System.out.println("@@@@@");
//		}
		Color mycol = Color.valueOf("RED"); //Color.RED와 같다.
		Count mycnt = Count.THREE; //Count.valueOF("THREE")와 같다.

		System.out.println("mycol : " + mycol.name());
		System.out.println("mycol : " + mycnt.name());
		System.out.println();
		
		System.out.println("mycol ordinal : " + mycol.ordinal()); 
		//RED값의 위치를 반환
		System.out.println("mycnt ordinal : " + mycnt.ordinal());
		//THREE값의 위치를 반환
		System.out.println();
		
		//서로 다른 종류의 열거형끼리의 비교는 불가능하다.
//		if(mycol==mycnt) {
//			System.out.println("....");
//		}
		if(mycol == Color.RED) {
			System.out.println("같다...");
		}else {
			System.out.println("다르다...");
		}
		
		//switch의 case문에 열거형을 배치할 때는 '열거형이름'을 생략하고
		//상수명만 기술해야 한다.
		switch (mycnt) {
		case ONE : System.out.println("ONE"); break;
		case TWO : System.out.println("TWO"); break;
		case THREE : System.out.println("THREE"); break;
		}
		System.out.println("--------------------");
		
		
		Season ss = Season.valueOf("봄");
		System.out.println("name : " + ss.name());
		System.out.println("ordinal : " + ss.ordinal());
		System.out.println("기간 : " + ss.getSpan());
		System.out.println("temp : " + ss.getTemp());
		System.out.println();
		
		for(Color col : Color.values()) {
			System.out.println(col + " ==> " + col.ordinal());
		}
		System.out.println();
		
		for(Season s : Season.values()) {
			System.out.println(s.name() + " == " + s + " ==> " + s.getSpan()
			                   + "의 평균온도 : " + s.getTemp());
		}
	}

}
