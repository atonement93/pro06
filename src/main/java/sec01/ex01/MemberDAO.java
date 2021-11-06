package sec01.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberDAO {
	
	public List<MemberVO> listMembers() {
		
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		String user = "user02";
		String password = "user02";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		
		try {
			Class.forName(driver);
			
			Connection con = DriverManager.getConnection(url, user, password);
			
			String query = "SELECT * FROM T_MEMBER";
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
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
			stmt.close();
			con.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
		

		
	}
}

