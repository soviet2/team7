<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<form action=logout method=post
	onSubmit="CURRENT_URL.value=window.location.href">
	�ȳ��ϼ���, ${sessionScope.LOGIN_ID}�� 
	<input type=hidden name=CURRENT_URL>
	<input type=submit value='�α׾ƿ�'>
</form>