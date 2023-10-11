/*================================================
 * user : sorae
 * date : 2023. 10. 10.
 * file_name : CommentsDao.java
 * comments :
================================================*/
package cs.dit.comments;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
 * @author sorae
 *
 */
public class CommentsDao {
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/jh");
		Connection con = ds.getConnection();
		
		return con;
	}
	//Comments
	public JSONArray listComments(int bcode) {
		String sql = "select ccode, content, regdate from comments where bcode=? order by ccode desc";
		JSONArray list = new JSONArray();
		
		try (
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);)
		{
			pstmt.setInt(1, bcode);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				JSONObject json = new JSONObject();
				json.put("ccode", rs.getInt("ccode"));
				json.put("content", rs.getString("content"));
				String regdate = (rs.getDate("regdate")).toString();
				json.put("regdate", regdate);
				
				list.add(json);
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return list;
	}
	
	public void insertComments(CommentsDto dto) {
		String sql = "{call proc_comments(?, ?)}";
		
		try (
			Connection con = getConnection();
			CallableStatement cstmt = con.prepareCall(sql);
		)
		{	cstmt.setInt(1, dto.getBcode());
			cstmt.setString(2, dto.getContent());
			
			cstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
