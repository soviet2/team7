<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>게시판</title>

<script language="javascript">
	function Click(s) {

		var form = document.fform;
		if (!s) {
			alert("로그인 후 이용하세요");
			return;
		} else {

			form.submit();

		}
	}
</script>

</head>
<body>
	<table id="board" border=1>
		<tr>
			<td width=40>번호</td>
			<td width=300>제목</td>
			<td width=80>작성자</td>
			<td width=90>작성일자</td>
			<td width=70>조회수</td>
		</tr>

		<c:forEach var="cnt" begin="0" end="${BBS_LIST.listSize-1}">
			<tr>
				<td>${BBS_LIST.number[cnt] }</td>
				<td><a
					href="Home.jsp?BODY_PATH=BBSItemView.jsp?
									NUMBER=${BBS_LIST.number[cnt] }">${BBS_LIST.title[cnt] }</a></td>
				<td>${BBS_LIST.userName[cnt] }</td>
				<td>${BBS_LIST.wDate[cnt] }</td>
				<td>${BBS_LIST.hit[cnt] }</td>
			</tr>
		</c:forEach>
	</table>

	<c:if test="${!BBS_LIST.lastPage }">
		<a href='Home.jsp?BODY_PATH=bbs-list?LAST_NUMBER=${BBS_LIST.number[BBS_LIST.listSize-1]}'>다음
			페이지</a>
	</c:if>
	<!-- Onclick="location='Home.jsp?BODY_PATH=BBSInput.jsp'" -->

	<form name=fform action=BBSInput.jsp method=post>
		<input type=button value='글쓰기' Onclick="javascript:Click('${ID}'); location='Home.jsp?BODY_PATH=BBSInput.jsp'">
	</form>

</body>
</html>