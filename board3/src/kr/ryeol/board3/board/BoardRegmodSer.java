package kr.ryeol.board3.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ryeol.board3.common.SecurityUtils;
import kr.ryeol.board3.common.Utils;

@WebServlet("/board/regmod")
public class BoardRegmodSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(SecurityUtils.isLogout(request)) {
			response.sendRedirect("/login");
			return;
		}
		
		request.setAttribute("data", BoardService.detail(request));
		
		request.setAttribute("jsList", new String[]{"board"});
		Utils.forwardTemp("등록/수정", "temp/basic_temp", "board/bRegmod", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(BoardService.regMod(request));
	}

}
