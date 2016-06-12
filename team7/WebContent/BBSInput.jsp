<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<script language="javascript">
	function Check() {
		var form = document.writeform;

		if (!form.title.value) {
			alert("제목을 입력해주세요");
			form.title.focus();
			return;
		}

		if (!form.content) {
			alert("내용을 입력해주세요");
			form.content.focus();
			return;
		}

		form.submit();
	}
</script>


</head>
<body>
	<h4>게시글 작성</h4>
	<form name=writeform action=bbs-post method=post>
		제목: <input type=text name=title><br>
		<textarea cols=80 rows=5 name=content></textarea>
		<br> <input type=button value='저장' Onclick="javascript:Check();">
		<input type=button value='취소' Onclick="javascript:history.back(-1)">

	</form>
</body>
</html>