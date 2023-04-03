package kr.or.ddit.basic;

import java.io.File;
/*
 path(상대 경로) : 어떤 기준 경로(현재 디렉토리 혹은 지정한 기준)을 잡아서 그곳에서부터 파일을 찾는다.
 absolutePath(절대 경로) : 경로만 존재한다면 시작점(최상위 폴더)에서부터 파일까지 모든 경로를 표시해준다.
 
 .isFile() : 경로가 File인지 확인한다.
 .isDirectory() : 경로가 Directory인지 확인한다.
 .exists() : 경로에 파일이 존재하는지 확인한다. 
 */
public class FileTest02 {

	public static void main(String[] args) {
		File f1 = new File("d:/d_other/test.txt");
		
		System.out.println(f1.getName() + "의 크기 : " + f1.length() + "byte(s)");
		System.out.println("path : " + f1.getPath());
		System.out.println("absolutePath : " + f1.getAbsolutePath());
		System.out.println();
		
		//현재 위치 나타내기
//		File f2 = new File("");
		File f2 = new File(".");
		System.out.println("path : " + f2.getPath());
		System.out.println("absolutePath : " + f2.getAbsolutePath());
		System.out.println();
		
		if(f1.isFile()) {
			System.out.println(f1.getName() + "은 파일입니다.");
		}else if(f1.isDirectory()) {
			System.out.println(f1.getName() + "은 디렉토리입니다.");
		}else {
			System.out.println(f1.getName() + "은 뭘까요???");
		}
		System.out.println();
		
		if(f2.isFile()) {
			System.out.println(f2.getName() + "은 파일입니다.");
		}else if(f2.isDirectory()) {
			System.out.println(f2.getName() + "은 디렉토리입니다.");
		}else {
			System.out.println(f2.getName() + "은 뭘까요???");
		}
		System.out.println();
		
		File f3 = new File("d:/d_other/sample.exe");//현재 존재하지 않는 파일 지정
		if(f3.isFile()) {
			System.out.println(f3.getName() + "은 파일입니다.");
		}else if(f3.isDirectory()) {
			System.out.println(f3.getName() + "은 디렉토리입니다.");
		}else {
			System.out.println(f3.getName() + "은 뭘까요???");
		}

	}

}
