package kr.or.ddit.basic;

import java.util.Vector;

public class VectorTest {

	public static void main(String[] args) {
		// 객체 생성
		Vector v1 = new Vector();
		// Vector는 데이터가 들어가면 자동으로 늘어나기때문에 처음에 크기는 0이다.
		
		System.out.println("처음 크기 : " + v1.size());
		
		// 데이터 추가하기1 : add(추가할 데이터)
		// 반환값 : 추가성공(true)반환, 추가실패(false)반환
		v1.add("aaaa");//문자열 추가 가능
		
		v1.add(new Integer(111));
		v1.add(123);//안에서 자동으로 포장되고 이것을 '오토박싱'이라고 함,반대 개념인 '오토 언박싱'이 있고 Vetcor는 둘다 지원한다
		// 숫자 추가 가능
		// 예전에는 숫자를 넣기 위해서는 v1.add(new Integer(111));로 객체화 시켜서 넣어줘야 했음
		// Integer,Float와 같은 것들을 래퍼클래스(포자클래스)라고 해준다.
		v1.add('a');
		v1.add(true);
		boolean r = v1.add(3.14);

		System.out.println("현재 크기 : " + v1.size());
		System.out.println("반환값 : " + r);
		
		// 데이터 추가하기2 : addElement(추가할 데이터)
		//  ==> 이전 버전의 츠로그램과의 호환성을 위해서 남아 있는 메서드
		v1.addElement("CCC");
		
		System.out.println("v1 => " + v1);
		
		// 데이터 추가하기3 : add(index, 데이터)
		//   ==> 'index'번째에 '데이터'를 끼워 넣는다.
		//   ==> 'index'는 0부터 시작한다.
		//   ==> 반환값이 없다.
		v1.add(1, "KKK");
		System.out.println("v1 => " + v1);
		
		// 데이터 꺼내오기 : get(index)
		//    ==> 'index'번째 데이터를 꺼내서 반환한다.
		System.out.println("0번째 데이터 : " + v1.get(0));
		int iTemp = (int)v1.get(2); 
		// 여러 데이터가 들어가 있기 때문에 저장을 위해 전부 Object형으로 저장되어 미스 매치가 난다.
		// 실행하기 위해서는 Vector의 값을 형변환을 해서 타입을 맞춰준다.
		System.out.println("2번째 데이터 : " + iTemp);
		
		// 데이터 수정하기 : set(index, 새로운 데이터)
		//   ==> 'index"번째의 데이터를 반환하고 '새로운데이터'로 변경한다.
		//   ==> 반환값 : 원래의 데이터를 반환
		String sTemp = (String)v1.set(0, "zzz");//기존의 데이터가 반환되며 sTemp에 저장되고 빈자리에 새로운 데이터가 저
		System.out.println("v1 ==>  : " + v1);
		System.out.println("원래의 데이터 : " + sTemp);
		
		// 데이터 삭제하기 : remove(index)
		//   ==> 'index'번째 데이터를 삭제한다.
		//   ==> 반환값 : 삭제된 데이터
		v1.remove(0);
		System.out.println("삭제 후 v1 => " + v1);
		
		sTemp = (String)v1.remove(0);
		System.out.println("삭제 후 v1 => " + v1);
		System.out.println("삭제된 데이터 : " + sTemp);
		
		// 데이터 삭제하기2 : remove(삭제할 데이터)
		//   ==> '삭제할 데이터'를 찾아서 삭제한다.
		//   ==> '삭제할 데이터'가 여러개이면 이 중에 제일 첫번째 자료가 삭제된다.
		//   ==> 반환값 : 삭제성공(true)반환, 삭제실패(false)반환
		//   ==> 삭제할 데이터가 '정수형'이거나 'char형'일 경우에는 반드시 객체로 변환해서 사용해야 한다.
		v1.remove("CCC");
		System.out.println("CCC 삭제 후 v1 => " + v1);
		
		// v1.remove(123);//v1의 123번째 값을 지워라라고 인식하기 때문에 오류 발생
		// v1.remove(new Integer(123)); //방법1 ==> 1.9버전부터는 사용 불가
		v1.remove(Integer.valueOf(123)); //방법2
		System.out.println("123 삭제 후 v1 => " + v1);
		
		// v1.remove('a');// char타입의 값들은 저장될 때 아스키 코드 값으로 저장된다. a = 97
		v1.remove(new Character('a'));
		System.out.println("삭제 후 v1 => " + v1);
		
		v1.remove(true);
		System.out.println("삭제 후 v1 => " + v1);
		
		v1.remove(3.14);
		System.out.println("삭제 후 v1 => " + v1);
		
		// remove를 사용할 때에는 Integer과 Character형의 데이터를 주의해서 삭제해야 한다.
		
		v1.clear();// 전부 삭제
		System.out.println("clear 삭제 후 v1 => " + v1);
		//-------------------------------------------------------------------------------
		/*
		 제네릭타입(Generic Type) ==> 클래스 내부에서 사용할 데이터 타입을 객체를 생성할 때
		        외부에서 지정해 주는 기법으로 객체를 선얼할 때 < >고라호 안에 그 객체의 내부에서
		        사용할 데이터으 타일을 정해주는 것을 말한다.
		        
		        - 이런식으로 선언하게 되면 그 데이터 타입 이외의 다른 종류의 데이터를 저장할 수 없다.
		        - 이때 제네릭으로 선언 될 수 있는 데이터 타입은 클래스형 이여야 한다.
		        그래서 int는 Integer, boolean은 Boolean, char는 Character등으로 대체해서
		        사용해야 한다.
		        - 제네릭 타입으로 선언하게 되면 데이터를 꺼내올 때 별도의 형변환이 필요없다. 
		 */
		Vector<Integer> v2 = new Vector<>(); // int형 자료만 저장할 수 있는 벡터
		Vector<String> v3 = new Vector<>(); // String형만 저장할 수 있는 벡터
		
		v3.add("안녕하세요");
		//v3.add(100); // 오류 : 다른 종류의 데이터를 저장할 수 없다.
		
		String sTemp2 = v3.get(0); 
		//v3를 만들 때 String만 저장할 수 있도록 만들었기 때문에 형변환을 않해도 된다. 
		
		Vector<Vector> vv = new Vector<>();
		Vector<Vector<Vector>> vvv = new Vector<>();
		//-------------------------------------------------------------------------------
		
		System.out.println("------------------------------------------------------");
		v3.clear();
		System.out.println("v3의 size : " + v3.size());
		
		v3.add("AAA");
		v3.add("BBB");
		v3.add("CCC");
		v3.add("DDD");
		v3.add("EEE");
		
		Vector<String> v4 = new Vector<>();
		v4.add("BBB");
		v4.add("EEE");
		
		System.out.println("v3 => " + v3);
		System.out.println("v4 => " + v4);
		
		
		// 데이터 삭제하기3 : removeAll(Collection객체)
		//   ==> 벡터의 데이터 중에서 'Collection객체'가 가지고 있는 모든 데이터를 삭제한다.
		//   ==> 반환값 : 성공(true)반환, 실패(false)반환
		
		v3.removeAll(v4); // v3의 데이터들 중에서 v4가 가지고 있는 데이터들을 모두 삭제한다.
		System.out.println("v3 => " + v3);
		System.out.println("------------------------------------------------------");
		
		// 벡터의 데이터를 순서대로 모두 가져와 사용하고 싶으면 반복문을 사용하면 된다.
		// (주로 for문 사용)
		v3.clear();
		
		v3.add("AAA");
		v3.add("BBB");
		v3.add("CCC");
		v3.add("DDD");
		
		for(int i=0; i<v3.size(); i++) {
			System.out.println(i + "번째 자료 : " + v3.get(i));
		}
		
		// 향산된 for문
		for(String str : v3) {
			System.out.println(str);
		}	
	}
}