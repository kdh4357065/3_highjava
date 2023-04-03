package kr.or.ddit.basic.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieLoginServlet.do")
public class CookieLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//클라이언트가 보내온 데이터 받기
		String userID = request.getParameter("userid");
		String userPASS = request.getParameter("userpass");
		String chkid = request.getParameter("chkid");
		
		//쿠키 객체 생성 (쿠키이름 : 'USERID', 쿠키값 : 로그인한 ID)
		Cookie loginCookie = new Cookie("USERID", userID);
		
		//checkbox가 체크된 상태이면 쿠키 저장, 해제된 상태이면 쿠키 삭제
		if(chkid == null) { //checkbox의 해제 여부 검사
			loginCookie.setMaxAge(0);
		}
		
		response.addCookie(loginCookie);
		//--------------------------------------------------------------
		//로그인 성공 여부(id : test, password: 1234)
		if("test".equals(userID) && "1234".equals(userPASS)) { //로그인 성공
			response.sendRedirect(request.getContextPath() + "/basic/cookie/cookieMain.jsp");
		}else { //로그인 실패
			response.sendRedirect(request.getContextPath() + "/basic/cookie/cookieLogin.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
