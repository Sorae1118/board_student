/**==================================
 * 작성자 : qbnyp
 * 작성명 : 2023. 9. 19.
 * 파일명 : BUpdateService.java
 * 프로그램 설명 : 
*=====================================*/
package cs.dit.board;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author qbnyp
 *
 */
public class BUpdateService implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		int bcode = Integer.parseInt(request.getParameter("bcode"));
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		Date regDate = Date.valueOf(request.getParameter("regDate"));
		
		BoardDto dto = new BoardDto(bcode, subject, content, writer, regDate, null); 
		BoardDao dao = new BoardDao();
		
		dao.update(dto);		
	}

}
