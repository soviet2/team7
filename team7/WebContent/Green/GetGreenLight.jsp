<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>받은 그린 바구니 </title>
</head>
<body>
	<h4>받은 그린라이트</h4>

	<table border=1>
		<tr>
			<th width="200">순번</th>
			<th width="200">아이디</th>
			<th width="200">나이</th>
			<th width="200">성별</th>
			<!-- <th>사진</th> -->
			<!-- <th>상태</th> -->
			<th width="200">버튼</th>
		</tr>
		<c:forEach items="${GETGREEN_LIST }" var="current"  varStatus="cnt"> 
		<tr>
			<td>${cnt.count }</td>
			<td>${current.id }</td>
			<td>${current.age}</td>
			<td>${current.gender}</td>
			<!-- <td>사진</td>
			<td>상태</td> -->
			<td>
				<form action="sendGreen" method=get>
					<input type=hidden name=setCode value="${current.id }">
					<input type=submit value="그린라이트">
				</form>
				<form action="canselGreen" method=get>
					<input type=hidden name=setCode value="${current.id }">
					<input type=submit name=check value="거절">
				</form>
			</td>
		</tr>	
		
	    </c:forEach>
		
	
	</table>
	
	
	
	
</body>
</html>