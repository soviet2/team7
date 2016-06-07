<%@page contentType="text/html; charset=euc-kr"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr" />
<title>Pick Me Up</title>
<link rel="styLesheet" type="text/css" href="css/homeLayout.css"></link>
</head>
<body>
	<div id="wrap">
		<div id="header">
			<a href="Home.jsp">Pick Me Up</a>
		</div>
		<div id="login">
			<c:choose>
				<c:when test="${sessionScope.LOGIN_ID==null}">
					<jsp:include page="LoginWindow.html" />
				</c:when>
				<c:otherwise>
					<jsp:include page="LogoutWindow.jsp" />
				</c:otherwise>
			</c:choose>
		</div>
		<div id="nav">
			<ul>
				<li><a href="Home.jsp?BODY_PATH=Board.jsp">게시판</a></li>
				<li><a href="Home.jsp?BODY_PATH=Search.jsp">검색</a></li>
				<li><a href="Home.jsp?BODY_PATH=GreenLight.jsp">마이페이지</a>
					<ul>
						<li><a href="Home.jsp?BODY_PATH=GreenLight.jsp">그린바구니</a></li>
						<li><a href="Home.jsp?BODY_PATH=Message.jsp">쪽지함</a></li>
					</ul></li>
			</ul>
		</div>
		<div id="sidebar">sidebar</div>
		<div id="contents">
			<c:choose>
				<c:when test="${param.BODY_PATH == null}">
					<c:import url="Board.jsp" />
				</c:when>
				<c:otherwise>
					<c:import url="${param.BODY_PATH}" />
				</c:otherwise>
			</c:choose>

		</div>
		<div id="footer">footer</div>
	</div>
</body>
</html>