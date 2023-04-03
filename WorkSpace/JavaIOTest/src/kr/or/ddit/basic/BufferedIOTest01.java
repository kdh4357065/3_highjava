package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedIOTest01 {

	public static void main(String[] args) {
		//입출력의 성능 향상을 위해서 Buffered스트림을 사용한다.
		FileOutputStream fout = null;
		BufferedOutputStream bout =null;
		
		try {
			fout = new FileOutputStream("d:/d_other/bufferTest.txt");
			
			//버퍼의 크기가 5인 버퍼스트림 객체 생성
			//버퍼는 5까지의 크기를 가지고 있고 1,2,3,4,5까지 들어가게 되고
			//5가 되면 크기가 다 찾기 때문에 파일에 지금까지 가지고 있는 내용을 출력하고 버퍼를 비운다
			//그리고 다시 6,7,8,9까지 받고 반복문은 끝나기 때문에 버퍼가 가지고 있는 4개의 데이터를
			//출력하지 못하고 끝나게 된다.
			bout = new BufferedOutputStream(fout, 5);
			
			for(char ch='1'; ch<='9'; ch++) {
				bout.write(ch);
			}
			//출력 잡업이 끝나면 출력 버퍼에 남아 있는 데이터를 강제로 모두 출력시켜줘야 한다.
			//bout.flush();
			
			System.out.println("작업 끝...");
		} catch (IOException e) {
			// TODO: handle exception
		}finally {
//			if(fout!=null) try { fout.close(); } catch(IOException e) {}
			//보조 스트림을 닫으면 보조 스트림에서 사용한 기반이 되는 스트림도 같이 닫힌다.
			if(bout!=null) try { bout.close(); } catch(IOException e) {}
			//bout.close()에서 close()에도 flush()기능이 있어 남아있는 데이터를 출력해준다.
		}

	}

}
