/*================================================
 * user : sorae
 * date : 2023. 10. 11.
 * file_name : LController.java
 * comments :
================================================*/
package ditcs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class LController {
	private static final long serialversionUID = 1L;
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Context initCtx = null;
		Context envCtx = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray list = null;
		
		try {
			request.setCharacterEncoding("utf-8");
			
			initCtx = new InitialContext();
			envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/jh");
			
			con = ds.getConnection();
			String sql = "select * from login";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			response.setContentType("text/html; charset=utf-8");
			
			list = new JSONArray();
			
			while(rs.next()) {
				JSONObject json = new JSONObject();
				json.put("id", rs.getString("id"));
				json.put("name", rs.getString("name"));
				json.put("pwd", rs.getString("pwd"));
				list.add(json);
			}
			
			response.getWriter().print(list);
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(rs != null)
				try {rs.close();} catch (SQLException e) {e.printStackTrace(); }
			if(pstmt != null)
				try {pstmt.close();} catch (SQLException e) {e.printStackTrace(); }
			if(con != null)
				try {con.close();} catch (SQLException e) {e.printStackTrace(); }
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
}
