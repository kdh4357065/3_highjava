package kr.or.ddit.basic;

import java.io.FileReader;
import java.io.IOException;

public class FileIOTest03 {

	public static void main(String[] args) {
		//FileReader() : 문자 기반 스트림을 이용하여 파일의 내용을 읽어 출력해준다.
		//               (문자 단위로 데이터를 읽기 때문에 텍스트 파일만 읽을 수 있다.)
		FileReader fr = null; //문자 기반 파일 입력용 스트림 객체 변수 선언
		try {
			//스트림 객체 생성
			fr = new FileReader("d:/d_other/test.txt");
			
			int c;
			
			while((c=fr.read()) != -1) {
				System.out.print((char)c);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fr!=null) try {fr.close();} catch (IOException e) {}
		}

	}

}
