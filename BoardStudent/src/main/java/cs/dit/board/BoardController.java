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

// 서블릿 개념을 확실하게 이해해야 함
// 서블릿 매핑하는 이유 : 특정 servlet을 요청할 때, 전체 경로를 url에 써주면, 너무 복잡하고 보안에도 취약하기 때문에, 간단하게 경로를 표현해주는 것
// description : 서블릿 설명, urlPatterns : 해당 서블릿을 호출할 url패턴
@WebServlet(description = "게시판컨트롤러", urlPatterns = { "*.do" })
public class BoardController extends HttpServlet {
	// 객체 직렬화에 사용. 서로 직렬에 알맞는 클래스를 찾는데 도와주는 신분증 같은 것. (직렬화 개념 이해 필요)
	private static final long serialVersionUID = 1L;

    public BoardController() {
        // TODO Auto-generated constructor stub
    }

	// 클라이언트로부터 요청이 들어올 때마다 스레드가 생성됨. httpservletrequest,response 는 객체가 넘어올 수 있도록 도와줌. 이 두 객체는 
    // doGet은 url 값으로 정보 전송되어 보안에 취약, Post는 header를 이용해 정보 전송 보안 굿. 각 메소드 방식에 따라 둘중에 요청
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
