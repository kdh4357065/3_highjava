package kr.or.ddit.basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

//클라이언트가 보내온 파일을 받아서 'd:/d_other/연습용'폴더에 저장한다.
public class TcpFileServer {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new TcpFileServer().serverStart();
	}

	public void serverStart() {
		ServerSocket server = null;
		Socket socket = null;
		
		DataInputStream din = null;
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		
		//저장할 폴더 정보를 갖는 File객체 생성
		File saveDir = new File("d:/d_other/연습용");
		if(!saveDir.exists()) { //저장할 폴더가 없으면 새로 만든다.
			saveDir.mkdir();
		}
		
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 준비되었습니다...");
			socket = server.accept(); //클라이언트의 요청 기다리기...
			
			System.out.println("파일 수신 시작...");
			
			//클라이언트가 첫번쨰로 보낸 데이터(파일명)를 받을 스트림 객체 생성
			din = new DataInputStream(socket.getInputStream());
			
			String fileName = din.readUTF(); //파일명 받기
			
			//저장할 폴더와 수신받은 파일명을 결합한 File객체 생성
			File saveFile = new File(saveDir,fileName);
			
			bin = new BufferedInputStream(socket.getInputStream()); // 수신용
			bout = new BufferedOutputStream(new FileOutputStream(saveFile)); //파일 저장용
			
			byte[] temp = new byte[1024];
			int len = 0;
			while((len=bin.read(temp))>0) {
				bout.write(temp, 0, len);
			}
			bout.flush();
			System.out.println("파일 수신 안료...");
			
		} catch (Exception e) {
			System.out.println("파일 수신 실패!!!");
			e.printStackTrace();
		}finally {
			if(din!=null) try {din.close(); } catch(IOException e) {}
			if(bin!=null) try {bin.close(); } catch(IOException e) {}
			if(bout!=null) try {bout.close(); } catch(IOException e) {}
			if(socket!=null) try {socket.close(); } catch(IOException e) {}
			if(server!=null) try {server.close(); } catch(IOException e) {}
		}
	}
}

//File file = new File("d:/d_other/연습용");
//if(!file.exists()) {
//	System.out.println("파일이 존재하지 않으므로 파일을 생성합니다.");
//	file.mkdir();
//	System.out.println("파일이 생성되었습니다...");
//}
//ServerSocket server = new ServerSocket(7777);
//
//System.out.println("서버가 준비 중입니다...");
//
//Socket socket = server.accept();
//System.out.println("서버의 연결되었습니다...");
//
//InputStream in = socket.getInputStream();
//DataInputStream din = new DataInputStream(in);
//
//File nFile = new File(file,din.readUTF());
//System.out.println("파일명을 받았습니다...");
//
//FileOutputStream fout = new FileOutputStream(nFile);
//
//int inData;
//
//System.out.println("복사본 저장을 시작합니다...");
//try {
//	
//	while((inData = din.read()) != -1) {
//		fout.write(inData);
//	}
//	
//	System.out.println("전송된 파일 저장이 완료되었습니다...");
//	System.out.println();
//} catch (IOException e) {
//	// TODO: handle exception
//}
//
//fout.close();
//din.close();
//socket.close();
//server.close();