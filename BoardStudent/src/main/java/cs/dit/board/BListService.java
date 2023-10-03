/**==================================
 * 작성자 : qbnyp
 * 작성명 : 2023. 9. 13.
 * 파일명 : mListService.java
 * 프로그램 설명 : 
*=====================================*/
package cs.dit.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

/**
 * @author qbnyp
 *
 */
public class BListService implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1. board.dao를 생성 
		BoardDao dao = new BoardDao();
		int numOfRecords = 10; // 한번에 가져올 레코드의 개수	
		int count = dao.recordCount(); // 전체 레코드의 개수
		int numOfPages = 5; // 한화면에 표시될 페이지의 개수
		
		String page = request.getParameter("p"); 
		int p = 1; // 페이지 초기 값;	
		
		if(page != null && !page.equals("")) {
			p = Integer.parseInt(page);
		}

		//2. dao 해당 필요한 메소드를 호출
	    ArrayList<BoardDto> dtos = dao.list(p, numOfRecords);
	    
		int startNum = p-((p-1) % numOfPages); //시작될 페이지 숫자
		
		int lastNum = (int)Math.ceil(count/10.0); //전체 페이지 숫자
		
		//3. 호출 결과 처리
	    request.setAttribute("dtos", dtos);
	    request.setAttribute("p", p);
	    request.setAttribute("startNum", startNum);
	    request.setAttribute("lastNum", lastNum);
	    
	    System.out.println("p : " + p);
	    System.out.println("startNum : " + startNum);
	    System.out.println("lastNum : " + lastNum);
	    
	}

}
