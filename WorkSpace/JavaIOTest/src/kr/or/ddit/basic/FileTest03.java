package kr.or.ddit.basic;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileTest03 {

	public static void main(String[] args) {
		FileTest03 test = new FileTest03();
		
		File file = new File("d:/d_other");
		test.dir(file);
	}

	//디렉토리 정보를 매개변수로 받아서 해당 디렉토리에 있는
	//모든 파일 및 디렉토리 목록을 출력하는 메서드
	public void dir(File d) {
		if(!d.isDirectory()) {
			System.out.println("디렉토리(폴더)만 가능합니다...");
			return;
		}
		
		System.out.println("[" + d.getAbsolutePath() + "] 디렉토리 내용");
		
		//해당 디렉토리 안에 있는 모든 파일 및 디렉토리 목록을 구한다.
		//listFiles() : 디렉토리 경로에 있는 파일들을 배열로 리턴
		File[] files = d.listFiles();
		//String[] filesStr = d.list();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd a HH:mm");
		
		//가져온 파일과 디렉토리 목록 개수만큼 반복 처리
		for(int i=0; i<files.length; i++) {
			String fileName = files[i].getName();
			String attr = ""; //파일의 속성 (읽기, 쓰기, 히든. 디렉토리 구분)
			String size = ""; //파일의 크기
			
			if(files[i].isDirectory()) {
				attr = "<DIR>";
			}else {
				size = files[i].length() + "";
				attr = files[i].canRead() ? "R" : "";
				attr += files[i].canWrite() ? "W" : "";
				attr += files[i].isHidden() ? "H" : "";
			}
			
			//lastModified() : 파일의 마지막 수정 날짜는 얻는다.
			//format() : 문자열의 형식을 설정하는 메서드
			String strDate = df.format(new Date(files[i].lastModified()));
			System.out.printf("%s %5s %12s %s\n", strDate, attr, size, fileName);
		}
	}
	
}
