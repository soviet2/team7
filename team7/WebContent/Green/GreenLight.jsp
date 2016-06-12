<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>그린 바구니</title>
<style>
div.board {
	border: 1px solid black;
}
</style>
</head>
<body>
	<h3>그린라이트 바구니</h3>

	<div class=board>
		<c:import url="/getgreenshow" />
	</div>
	<div class=board>
		<c:import url="/setgreenshow" />
	</div>
	<div class=board>
		<c:import url="/maching" />
	</div>

</body>
</html>