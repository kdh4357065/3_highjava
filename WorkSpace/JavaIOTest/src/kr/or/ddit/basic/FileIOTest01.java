package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

//FileInputStream() : File 객체가 가리키는 파일을 바이트 스트림으로 읽기 위해서 
//                    FileInputStream객체를 생성함

public class FileIOTest01 {

	public static void main(String[] args) {
		// 바이트 기반의 파일 입력용 스트림으로 파일 내용 읽기

		FileInputStream fin = null; // 파일 입력용 스트림 객체 변수 선언

		try {
			// 읽어올 파일을 인수값으로 지정해서 FileInputStream객체 생성하기

			// 방법1
			// fin = new FileInputStream("d:/d_other/test.txt");

			// 방법2
			File file = new File("d:/d_other/test.txt");
			//FileInputStream으로 "d:/d_other/test.txt"가리키는 객체 생성
			fin = new FileInputStream(file);

			int c; // 읽어온 데이터를 저장할 변수
			while ((c = fin.read()) != -1) {
				// 읽어온 데이터를 화면에 출력하기
				System.out.print((char) c);
			}

		} catch (IOException e) {
			System.out.println("입출력 오류입니다...\n" + e.getMessage());
		} finally {
			if (fin != null)try {fin.close();} catch (IOException e) {}
		}
	}

}
