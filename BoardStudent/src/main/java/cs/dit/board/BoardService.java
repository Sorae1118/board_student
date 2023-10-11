package cs.dit.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//BoardService 라는 인터페이스를 만듬. 인터페이스의 장점 이해하기. 해당 인터페이스를 상속받은 클래스는 꼭 해당 메소드를 구현해야 함.
public interface BoardService {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
}