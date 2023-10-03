/**==================================
 * 작성자 : qbnyp
 * 작성명 : 2023. 9. 19.
 * 파일명 : BinsertService.java
 * 프로그램 설명 : 
*=====================================*/
package cs.dit.board;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * @author qbnyp
 *
 */
public class BinsertService implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");

		
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		String filename = null;
		String dir = null;
		
		String contentType = request.getContentType();
		if(contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
			dir = request.getServletContext().getRealPath("/uploadfiles"); //실제 경로
		}
		
		File f = new File(dir);
		if(!f.exists()) {
			f.mkdir();
		}
		
		Collection<Part> parts = request.getParts();
		
		for(Part p : parts) {
			if(p.getHeader("Content-Disposition").contains("filename=")) {
				if(p.getSize() > 0) {
					filename = p.getSubmittedFileName();
					String filePath = dir + File.separator + filename; //separator OS에 맞게 경로 설정해주는 separator
					
					p.write(filePath);
					
					p.delete();
				}
			}
		}
		
		BoardDto dto = new BoardDto(0, subject, content, writer, null, filename); 
		BoardDao dao = new BoardDao();
		
		dao.insert(dto);
	}

}
