package sec02.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/member3")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		MemberDAO dao = new MemberDAO();
		List<MemberVO> list = dao.listMembers();
		
		out.print("<html><body>");
		out.print("<table border=1><tr align='center' bgcolor='lightgreen'>");
		out.print("<td>아이디</td><td>비밀번호</td><td>이름</td><td>이메일</td><td>가입일</td></tr>");
		
		for(int i=0;i<list.size();i++) {
			MemberVO memberVO = list.get(i);
			String id=memberVO.getId();
			String pw=memberVO.getPw();
			String name=memberVO.getName();
			String email=memberVO.getEmail();
			Date joinDate=memberVO.getJoinDate();
			out.print("<tr><td>"+id+"</td><td>"+pw+"</td><td>"+name+"</td><td>"+email+"</td><td>"+joinDate+"</td></tr>");
		}
		out.print("</table></body><html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		MemberDAO dao = new MemberDAO();
		PrintWriter out = response.getWriter();
		
		String command = request.getParameter("command");
		
		if (command != null && command.equals("addMember")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			MemberVO vo = new MemberVO();
			vo.setId(id);
			vo.setPw(pw);
			vo.setName(name);
			vo.setEmail(email);
			dao.addMember(vo);
		}
			else if(command != null && command.equals("delMember")){
			String id = request.getParameter("id");
			dao.delMember(id);
		}
		List<MemberVO> list = dao.listMembers();
		out.print("<html><body>");
		out.print("<table border=1><tr align='center' bgcolor='lightgreen'>");
		out.print("<td>아이디</td><td>비밀번호</td><td>이름</td><td>이메일</td><td>가입일</td><td>삭제</td></tr>");
		
		for(int i=0;i<list.size();i++) {
			MemberVO memberVO = (MemberVO) list.get(i);
			String id = memberVO.getId();
			String pw = memberVO.getPw();
			String name = memberVO.getName();
			String email = memberVO.getEmail();
			Date joinDate = memberVO.getJoinDate();
			out.print("<tr><td>" + id + "</td><td>" + pw + "</td><td>" + name + "</td><td>" + email + "</td><td>" + joinDate + "</td><td>"
			+ "<a href='/pro06/member3?command=delMember&id=" + id + "'>삭제</a></td></tr>");
		}
		out.print("</table></body></html>");
		out.print("<a href='/pro06/memberForm.html'>새 회원가입하기</a>");
	}

}
