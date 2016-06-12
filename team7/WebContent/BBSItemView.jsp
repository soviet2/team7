<!-- 게시글의 내용을 출력하는 페이지 -->
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:useBean id="bbsItem" class="web.BBSItem" />
<jsp:setProperty name="bbsItem" property="number"
	value="${param.NUMBER }" />
<%
	bbsItem.readDB();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script language="javascript">
	function Check3(a, s) {
		var form = document.ffform;

		if (a != s) {
			alert("권한이 없습니다");
			return;
		}

		form.submit();
	}

	function Check4(a, s) {
		var form = document.formGreen;

		if (!a) {
			alert("로그인 후 가능합니다.");
			return;
		}
		if (a == s) {
			alert("작성자 본인입니다.");
			return;
		}

		form.submit();
	}
</script>
</head>
<body>
	<table>
		<tr>
			<td>[번호] ${param.NUMBER}</td>
		</tr>
		<tr>
			<td>[제목] ${bbsItem.title}</td>
		</tr>
		<tr>
			<td>[작성자] ${bbsItem.userName} [작성일] ${bbsItem.wDate}</td>
		</tr>
		<tr>
			<td>${bbsItem.content}</td>
		</tr>
	</table>
	<form name=ffform action=bbs-delete method=post>
		<input type=hidden value="${param.NUMBER }" name=num> <input
			type=button value='목록'
			Onclick="location='Home.jsp?BODY_PATH=bbs-list'"> <input
			type=button value='삭제'
			Onclick="javascript:Check3('<%= session.getAttribute("LOGIN_ID") %>', '${bbsItem.userName}');">
	</form>
	<br>
	<form name=formGreen action=sendgreen method=post>
		<input type=button value='그린라이트'
			Onclick="javascript:Check4('<%= session.getAttribute("LOGIN_ID") %>', '${bbsItem.userName}')">
		<input type=hidden value="${bbsItem.userName}" name=setCode>
	</form>
</body>
</html>