package kr.or.ddit.basic;

import org.apache.log4j.Logger;

public class LoggingTest {
	//Logger 클래스의 인스턴스를 받아온다.
	static Logger logger = Logger.getLogger(LoggingTest.class);

	public static void main(String[] args) {
		//로그 기록을 남기는 방법
		//형식) logger객체변수.로그레벨("출력할 메시지")
		logger.trace("이것은 Log4j의 Trace 레벨의 메시지입니다.");
		logger.debug("이것은 Log4j의 Debug 레벨의 메시지입니다.");
		logger.info("이것은 Log4j의 Info 레벨의 메시지입니다.");
		logger.warn("이것은 Log4j의 Warn 레벨의 메시지입니다.");
		logger.error("이것은 Log4j의 Error 레벨의 메시지입니다.");
		logger.fatal("이것은 Log4j의 Fatal 레벨의 메시지입니다.");

	}

}
