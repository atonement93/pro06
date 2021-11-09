package sec02.ex02;

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
import javax.naming.NamingException;
import javax.sql.DataSource;

import sec02.ex01.MemberVO;

public class MemberDAO {
	
public List<sec02.ex02.MemberVO> listMembers() {
		
		List<sec02.ex02.MemberVO> list = new ArrayList<sec02.ex02.MemberVO>();
		
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
			
			//PrepareStatement ¿¬½À
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
				
				sec02.ex02.MemberVO vo = new sec02.ex02.MemberVO();
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
	
	public void addMember(sec02.ex02.MemberVO memberVO) {

		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			DataSource dataFactory = (DataSource) envContext.lookup("jdbc/oracle");

			Connection con = dataFactory.getConnection();
			
			String id = memberVO.getId();
			String pw = memberVO.getPw();
			String name = memberVO.getName();
			String email = memberVO.getEmail();
			Date joinDate = memberVO.getJoinDate();
			
			String query = "INSERT INTO T_MEMBER";
			query +=" (id,pw,name,email)";
			query +=" values(?,?,?,?)";
			System.out.println("prepareStatement : "+query);
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			ResultSet rs = pstmt.executeQuery();

			
			rs.close();
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void delMember(String id) {

		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			DataSource dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
				
			Connection con = dataFactory.getConnection();
			
			String query = "DELETE FROM T_MEMBER" + " WHERE ID=?";
			System.out.println("prepareStatement:" + query);
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
}


