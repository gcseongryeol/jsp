package kr.ryeol.board3.board;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import kr.ryeol.board3.common.SecurityUtils;
import kr.ryeol.board3.common.Utils;
import kr.ryeol.board3.db.BoardDAO;
import kr.ryeol.board3.db.SQLInterUpdate;
import kr.ryeol.board3.model.BoardPARAM;
import kr.ryeol.board3.model.BoardSEL;

public class BoardService {
	
	public static void selBoardList(HttpServletRequest request) {
		int typ = Utils.getIntParam(request, "typ");
		BoardPARAM p = new BoardPARAM();
		p.setTyp(typ);
		
		request.setAttribute("list", BoardDAO.selBoardList(p));
	}
	
	public static String regMod(HttpServletRequest request) {
		int i_board = Utils.getIntParam(request, "i_board");
		int typ = Utils.getIntParam(request, "typ");
		String title = request.getParameter("title");
		String ctnt = request.getParameter("ctnt");
		int i_user = SecurityUtils.getLoingUserPk(request);
		
		if(i_board == 0) { //등록
			String sql = " INSERT INTO t_board "
					+ " (typ, seq, title, ctnt, i_user) "
					+ " SELECT "
					+ " ?, ifnull(max(seq), 0) + 1, ?, ?, ? "
					+ " FROM t_board "
					+ " WHERE typ = ? ";
			
			BoardDAO.executeUpdate(sql, new SQLInterUpdate() {

				@Override
				public void proc(PreparedStatement ps) throws SQLException {
					ps.setInt(1, typ);
					ps.setNString(2, title);
					ps.setNString(3, ctnt);
					ps.setInt(4, i_user);
					ps.setInt(5, typ);
					
				}
				
			});
			
			return "list?typ=" +typ; // 리스트 페이지로 이동
			
		} else { // 수정 
			String sql = " UPDATE t_board "
					+ " SET title = ?, ctnt = ? "
					+ " WHERE i_board = ? "
					+ " AND i_user = ? ";
			
			BoardDAO.executeUpdate(sql, new SQLInterUpdate() {
				
				@Override
				public void proc(PreparedStatement ps) throws SQLException {
					ps.setNString(1, title);
					ps.setNString(2, ctnt);
					ps.setInt(3, i_board);
					ps.setInt(4, SecurityUtils.getLoingUserPk(request));
					
				}
			});
			
			return "detail?i_board=" + i_board; // 디테일 페이지로 이동
		}
	}
	
	public static BoardSEL detail(HttpServletRequest request) {
		int i_board = Utils.getIntParam(request, "i_board");
		if(i_board == 0) {
			return null;
		}
		
		BoardPARAM p = new BoardPARAM();
		p.setI_board(i_board);
		
		return BoardDAO.selBoard(p);
	}
	
	

}
