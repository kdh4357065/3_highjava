package kr.or.ddit.basic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

//FileOutputStream(file) : - 주어진 File 객체가 가리키는 파일을 쓰기 위한 객체를 생성
//                         - 기존의 파일이 존재할 경우 그 내용을 지우고 새로운 파일 생성

public class FileIOTest02 {

	public static void main(String[] args) {
		//바이트 기반의 출력용 스트림을 이용해서 파일로 출력하기
		FileOutputStream fout = null; //파일 출력용 스트림 객체 변수 선언
		try {
			//방법1
			File f = new File("d:/d_other/out.txt");
			//d:/d_other/out.txt경로로 파일 생성
			
			fout = new FileOutputStream(f); //파일 출력용 스트림 객체 생성
			//FileOutputStream() : 주어진 이름의 파일을 쓰기 위한 객체를 생성
			
			for(char ch='A'; ch<'Z'; ch++) {
				fout.write(ch);
				//.write : 파일에 텍스트를 작성
			}
			fout.flush(); //출력 버퍼에 남아 있는 자료를 강제로 출력한다.
			System.out.println("작업 완료...");
			
		} catch (IOException e) {
			//printStackTrace() : 에러의 발생근원지를 찾아서 단계별로 에러를 출력
			e.printStackTrace();
		}finally {
			//사용했던 스트림 닫기
			if(fout != null) try {fout.close();} catch (IOException e) {}
		}

	}

}
