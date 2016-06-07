<%@ page language="java" contentType="text/html; charset=euc-kr"
	pageEncoding="euc-kr"%>
<form action=logout method=post
	onSubmit="CURRENT_URL.value=window.location.href">
	æ»≥Á«œººø‰, ${sessionScope.LOGIN_ID}¥‘ 
	<input type=hidden name=CURRENT_URL>
	<input type=submit value='∑Œ±◊æ∆øÙ'>
</form>