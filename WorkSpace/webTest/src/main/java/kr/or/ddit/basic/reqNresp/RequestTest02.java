package kr.or.ddit.basic.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestTest02
 */
@WebServlet("/requestTest02.do")
public class RequestTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");	
		
		String firstNum = request.getParameter("firstNum");
		String secondNum = request.getParameter("secondNum");
		String choice = request.getParameter("choice");
		double result = 0;
		boolean check = true;
		
		double Num1 = Double.parseDouble(firstNum);
		double Num2 = Double.parseDouble(secondNum);
		
		if ("+".equals(choice)) {
			result = Num1 + Num2;
		} else if ("-".equals(choice)) {
			result = Num1 - Num2;
		} else if ("*".equals(choice)) {
			result = Num1 * Num2;
		} else if ("/".equals(choice)) {
			if(Num2 ==0) {
				check = false;
			}else {
				result = Num1 / Num2;
			}
		} else if ("%".equals(choice)) {
			if(Num2 ==0) {
				check = false;
			}else {
				result = Num1 % Num2;
			}
		}
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><meta charset='utf-8'><title>계산 결과</title></head>");
		out.println("<body>");
		
		out.println("<h2>계산 결과</h2><hr>");
		
		out.println(firstNum + " " + choice + " " + secondNum + " = ");
		
		if(check==true) {
			out.println(result);
		}else {
			out.println("계산 불능 (0으로 나누기)");
		}
		
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
