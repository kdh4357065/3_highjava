package kr.or.ddit.basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpFileClient {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new TcpFileClient().clientStart();
	}
	public void clientStart() {
		Socket socket = null;
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		DataOutputStream dout = null;
		
		//전송할 파일 정보를 갖는 File객체 생성
		File file = new File("d:/d_other/펭귄.jpg");
		String fileName = file.getName();
		
		if(!file.exists()) {
			System.out.println("전송할 " + fileName + " 파일이 없습니다.");
			return;
		}
		
		try {
			socket = new Socket("localhost",7777);
			
			System.out.println("파일 전송 시작...");
			dout = new DataOutputStream(socket.getOutputStream());
			dout.writeUTF(fileName);
			
			//파일 읽기용 스트림 객체 생성
			bin = new BufferedInputStream(new FileInputStream(file));
			
			//서버로 전송할 출력용 스트림 객체 생성
			bout = new BufferedOutputStream(socket.getOutputStream());
			
			byte[] temp = new byte[1024];
			int len = 0;
			
			//파일 내용을 읽어서 서버로 전송한다.
			while((len = bin.read(temp))>0) {
				bout.write(temp,0,len);
			}
			bout.flush();
			
			System.out.println("파일 전송 완료...");
		} catch (Exception e) {
			System.out.println("파일 전송 실패!!");
			e.printStackTrace();
		}finally {
			if(dout!=null) try {dout.close();}catch(IOException e) {}
			if(bout!=null) try {bout.close();}catch(IOException e) {}
			if(bin!=null) try {bin.close();}catch(IOException e) {}
			if(socket!=null) try {socket.close();}catch(IOException e) {}
		} 
	}

}

//File file = new File("d:/d_other/펭귄.jpg");
//if(!file.exists()) {
//	System.out.println("파일이 존재하지 않습니다.");
//	return;
//}
//System.out.println("서버에 연결 중입니다...");
//
//Socket socket = new Socket("localHost",7777);
//
//System.out.println("서버에 연결되었습니다...");
//
//String name = file.getName();
//
//OutputStream out = socket.getOutputStream();
//DataOutputStream dout = new DataOutputStream(out);
//
//FileInputStream inFile = new FileInputStream(file);
//int data;
//
//dout.writeUTF(file.getName());
//System.out.println("파일명을 전송했습니다.");
//try {
//	while((data = inFile.read()) != -1) {
//		dout.write(data);
//	}
//	dout.flush();
//	
//	System.out.println("파일 데이터를 전송했습니다...");
//} catch (IOException e) {
//	// TODO: handle exception
//}
//
//inFile.close();
//dout.close();
//socket.close();