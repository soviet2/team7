<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Pick Me Up</title>
<link rel="styLesheet" type="text/css" href="css/homeLayout.css"></link>
</head>
<body>
	<div id="wrap">
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
		<div id="header">
			<div id="main">
				<a href="Home.jsp">Pick Me Up</a>
			</div>
			<div id="nav">
				<ul>
					<li><a href="Home.jsp?BODY_PATH=Board.jsp">게시판</a></li>
					<li><a href="Home.jsp?BODY_PATH=Search.jsp">검색</a></li>
					<li><a href="Home.jsp?BODY_PATH=Matching.jsp">그린바구니</a>
						<ul>
							<li><a href="Home.jsp?BODY_PATH=Matching.jsp">매칭현황</a></li>
							<li><a href="Home.jsp?BODY_PATH=Send.jsp">보낸라이트</a></li>
							<li><a href="Home.jsp?BODY_PATH=Receive.jsp">받은라이트</a></li>
						</ul></li>
					<li><a href="Home.jsp?BODY_PATH=Message.jsp">쪽지함</a></li>
				</ul>
			</div>
		</div>
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
	</div>

	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	<script src="jquery.fittext.js"></script>
	<script type="text/javascript">
		$("#wrap").fitText(8, { minFontSize: '12px', maxFontSize: '16px' });
	</script>
</body>
</html>