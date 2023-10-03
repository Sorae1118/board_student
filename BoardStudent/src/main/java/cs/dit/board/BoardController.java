package cs.dit.board;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.MultipartConfig;


@MultipartConfig(
		maxFileSize = 1024 * 1024 * 5,
		maxRequestSize = 1024 * 1024 * 54)

@WebServlet(description = "게시판컨트롤러", urlPatterns = { "*.do" })
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public BoardController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = request.getRequestURI();
		//System.out.println(url.lastIndexOf("/"));
		//System.out.println();url.lastIndexOf(".do");
		String com = url.substring(url.lastIndexOf("/")+1, url.lastIndexOf(".do"));
		
		System.out.println(com);
		String viewPage = null;
		
		if(com != null && com.trim().equals("list")) {
			BoardService service = new BListService();
			service.execute(request, response);
			viewPage = "/WEB-INF/View/list.jsp";
		
		}else if(com != null && com.trim().equals("insertForm")) {
			viewPage = "/WEB-INF/View/insertForm.jsp";
			
		}else if(com != null && com.trim().equals("index")) {
				viewPage = "/WEB-INF/View/index.jsp";	
			
		}else if(com != null && com.trim().equals("insert")) {
			BoardService service = new BinsertService();
			service.execute(request, response);
			viewPage = "/WEB-INF/View/list.do";
			
		}else if(com != null && com.trim().equals("updateForm")) {
			BoardService service = new BSelectOneService();
			service.execute(request, response);
			viewPage = "/WEB-INF/View/updateForm.jsp";
			
		}else if(com != null && com.trim().equals("update")) {
			BoardService service = new BUpdateService();
			service.execute(request, response);
			viewPage = "/WEB-INF/View/list.do";
			
		}else if(com != null && com.trim().equals("delete")) {
			BoardService service = new BDeleteService();
			service.execute(request, response);
			viewPage = "/WEB-INF/View/list.do";
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
