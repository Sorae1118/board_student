/*================================================
 * user : sorae
 * date : 2023. 10. 10.
 * file_name : CommentsController.java
 * comments :
================================================*/
package cs.dit.comments;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
/**
 * @author sorae
 *
 */
@WebServlet("*.ct")
public class CommentsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String uri = request.getRequestURI();
		
		String com = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf(".ct"));
		
		CommentsService cService = new CommentsService();
		
		if (com != null && com.trim().equals("cList")) {
			System.out.println("cList");
			cService.listComments(request, response);
			
		} else if (com != null && com.trim().equals("cInsert")) {
			System.out.println("cInsert");
			cService.insertComments(request, response);
		}
		
	}


}
