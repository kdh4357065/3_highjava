package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest05 {

	public static void main(String[] args) {
		//파일의 인코딩 방식을 지정해서 읽어오기
		try {
//			FileReader fr = new FileReader("d:/d_other/test_ansi.txt");
//			FileReader fr = new FileReader("d:/d_other/test_utf8.txt");
			
			//인코딩 방식을 지정해서 입출력하는 보조 스트림
			//InputStreamReader ==> 입력용
			//outputStreamWriter ==> 출력용
			
//			FileInputStream fin = new FileInputStream("d:/d_other/test_ansi.txt");
			FileInputStream fin = new FileInputStream("d:/d_other/test_utf8.txt");
			
			//기본 인코딩 방식으로 읽어온다.
			//인코딩 방식 예시
			// - MS949 ==> 윈도우의 기본 한글 인코딩 방식(ANSI방식과 같다.)
			// - UTF-8 ==> 유니코드 UTF-8 인코딩 방식
			// - US-ASCII ==> 영문 전용 인코딩 방식
			
//			InputStreamReader isr = new InputStreamReader(fin, "ms949");
			InputStreamReader isr = new InputStreamReader(fin, "utf-8");
			
			int c;
			
			while((c=isr.read()) != -1) {
				System.out.print((char) c);
				//한번에 출력되는게 아니라 한 글자씩 출력된다.
			}
			
			isr.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
