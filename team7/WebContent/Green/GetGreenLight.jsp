<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>���� �׸� �ٱ��� </title>
</head>
<body>
	<h4>���� �׸�����Ʈ</h4>

	<table border=1>
		<tr>
			<th width="200">����</th>
			<th width="200">���̵�</th>
			<th width="200">����</th>
			<th width="200">����</th>
			<!-- <th>����</th> -->
			<!-- <th>����</th> -->
			<th width="200">��ư</th>
		</tr>
		<c:forEach items="${GETGREEN_LIST }" var="current"  varStatus="cnt"> 
		<tr>
			<td>${cnt.count }</td>
			<td>${current.id }</td>
			<td>${current.age}</td>
			<td>${current.gender}</td>
			<!-- <td>����</td>
			<td>����</td> -->
			<td>
				<form action="sendGreen" method=get>
					<input type=hidden name=setCode value="${current.id }">
					<input type=submit value="�׸�����Ʈ">
				</form>
				<form action="canselGreen" method=get>
					<input type=hidden name=setCode value="${current.id }">
					<input type=submit name=check value="����">
				</form>
			</td>
		</tr>	
		
	    </c:forEach>
		
	
	</table>
	
	
	
	
</body>
</html>