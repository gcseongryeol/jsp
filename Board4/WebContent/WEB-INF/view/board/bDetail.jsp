<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<a href="/board/list.korea?typ=${data.typ}">돌아가기</a>
	
	<c:if test="${data.i_user == loginUser.i_user}">
		<a href="regmod?typ=${data.typ}&i_board=${data.i_board}">
			<button>수정</button>
		</a>
	</c:if>
	<div>
		<div>번호 : ${data.seq}</div>
		<div>조회수 : ${data.hits}</div>
		<div>
			이름 : 
			<c:if test="${data.profile_img == null}">
				<div class="circular--landscape circular--size40">
					<img id="profileImg" src="/res/img/basic_profile.jpg">
				</div>
			</c:if>
			<c:if test="${data.profile_img != null}">
				<div class="circular--landscape circular--size40">
					<img id="profileImg" src="/res/img/${data.i_user}/${data.profile_img}">
				</div>
			</c:if>
			<span class="profile-td-nm">${data.writer_nm}</span>
		</div>
		<div>제목 : ${data.title}</div>
		<div>작성일 : ${data.r_dt}</div>
		<div>
			${data.ctnt}
		</div>
	</div>
	<div style="margin-top 20px;">
		<div>
			<form action="cmt/reg" method="post">
				댓글 : <input type="text" name="ctnt">
				<input type="submit" value="댓글쓰기">
			</form>
		</div>
		<div>
			<table>
				<tr>
					<th>내용</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>비고</th>
				</tr>
			</table>
		</div>
	</div>
	<c:if test="${loginUser != null}">
	<div id="favoriteContainer" is_favorite="${data.is_favorite}" onclick="toggleFavorite(${data.i_board});">
		<c:choose>
			<c:when test="${data.is_favorite == 1}">
				<i class="fas fa-heart"></i>
			</c:when>
			<c:otherwise>
				<i class="far fa-heart"></i>
			</c:otherwise>
		</c:choose>
	</div>
	</c:if>
</div>
<script>
	<c:if test="${msg != null}">
		alert('${msg}');
	</c:if>
</script>