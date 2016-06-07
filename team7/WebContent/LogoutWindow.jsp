<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<form action=logout method=post
	onSubmit="CURRENT_URL.value=window.location.href">
	안녕하세요, ${sessionScope.LOGIN_ID}님 
	<input type=hidden name=CURRENT_URL>
	<input type=submit value='로그아웃'>
</form>