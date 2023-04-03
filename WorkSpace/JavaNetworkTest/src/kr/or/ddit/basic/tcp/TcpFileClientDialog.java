package kr.or.ddit.basic.tcp;

import java.awt.Panel;
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

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TcpFileClientDialog {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new TcpFileClientDialog().clientStart();
	}
	public void clientStart() {
		Socket socket = null;
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		DataOutputStream dout = null;
		
		//전송할 파일 정보를 갖는 File객체 생성
		//File file = new File("d:/d_other/펭귄.jpg");
		File file = showDialog("OPEN");
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
	
	// openType은 열기용일 때는 "OPEN", 저장용일 때는 "SAVE"로 저장한다.
		private File showDialog(String openType) {
			// SWING의 파일 열기, 저장 창 연습

			JFileChooser chooser = new JFileChooser();

			// 선택한 파일의 확장자 설정
			FileNameExtensionFilter doc = new FileNameExtensionFilter("MS Word", "doc", "docx");
			FileNameExtensionFilter img = new FileNameExtensionFilter("Image Files", new String[] { "png", "jpg", "gif" });
			FileNameExtensionFilter pdf = new FileNameExtensionFilter("PDF 파일", "pdf");
			FileNameExtensionFilter txt = new FileNameExtensionFilter("txt 파일", "txt");

			// 구성한 확장자 추가
			chooser.addChoosableFileFilter(doc);
			chooser.addChoosableFileFilter(img);
			chooser.addChoosableFileFilter(pdf);
			chooser.addChoosableFileFilter(txt);

			// 구성한 확장자 목록 중에서 현재 선택된 상태가 될 확장자 지정
			// ==> 이것을 설정하지 않으면 첫번째로 추가한 확장자가 기본적으로 선택된다.
			// chooser.setFileFilter(txt);

			// 확장자 목록에 '모든 파일'목록을 표시 여부 설정
//					chooser.setAcceptAllFileFilterUsed(false);
//					chooser.setAcceptAllFileFilterUsed(true); //기본값

			// Dialog창이 나타날 때 기본적으로 보여줄 기본 경로 설정하기
			chooser.setCurrentDirectory(new File("d:/d_other"));

			// Dialog참 나타내기 (열기용과 저장용을 구분해서 나타낸다.)
			int result;
			if ("OPEN".equals(openType.toUpperCase())) {
				result = chooser.showOpenDialog(new Panel()); // 열기용
			} else if ("SAVE".equals(openType.toUpperCase())) {
				result = chooser.showSaveDialog(new Panel()); // 저장용
			} else {
				System.out.println("opneType은 'OPEN' 또는 'SAVE'로 지정해야 합니다");
				return null;
			}

			File selectFile = null;
			if (result == JFileChooser.APPROVE_OPTION) { // '저장' 또는 '열기'버튼을 눌렀을 경우...
				selectFile = chooser.getSelectedFile();
			}
			return selectFile;
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