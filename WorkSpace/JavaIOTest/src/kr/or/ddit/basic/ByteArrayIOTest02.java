package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest02 {

	public static void main(String[] args) {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		byte[] temp = new byte[4];

		ByteArrayInputStream input = null; //입력용 스트림 객체 변수 선언
		ByteArrayOutputStream output = null; //출력용 스트림 객체 변수 선언
		
		try {
			input = new ByteArrayInputStream(inSrc); //입력용 스트림 객체 생성
			output = new ByteArrayOutputStream(); //출력용 스트림 객체 생성
			
			while(input.available() > 0) { //읽어올 데이터가 있는지 확인
//				input.read(temp);
//				output.write(temp);
				
				//배열을 이용한 read()메서드는 실제 읽어온 데이터 개수를 반환한다.
				int len = input.read(temp);
				//System.out.println("실제 읽어온 개수 : " + len);
				
				//temp배열의 내용 중에서 0번째부터 len개 만큼 출력한다. 
				output.write(temp, 0, len);
				
				System.out.println("반복문 안에서 temp = " + Arrays.toString(temp));
			}
			
			outSrc = output.toByteArray();
			
			input.close();
			output.close();
			
			System.out.println("inSrc => " + Arrays.toString(inSrc));
			System.out.println("outSrc => " + Arrays.toString(outSrc));
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
