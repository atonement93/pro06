package sec02.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	
	public List<MemberVO> listMembers() {
		
		List<MemberVO> list = new ArrayList<MemberVO>();
		
//		String user = "user02";
//		String password = "user02";
//		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
//		String driver = "oracle.jdbc.driver.OracleDriver";
		
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			DataSource dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
			
			//Class.forName(driver);
			
			//Connection con = DriverManager.getConnection(url, user, password);
			
			Connection con = dataFactory.getConnection();
			
			String query = "SELECT * FROM T_MEMBER";
			
			//PrepareStatement ????
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			//Statement stmt = con.createStatement();
			
			//ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPw(pw);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				
				list.add(vo);

			}
			
			rs.close();
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
		

		
	}
}


