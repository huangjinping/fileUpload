<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传信息</title>
</head>
<body>

	<a
		href="${pageContext.request.contextPath}/NewFile.jsp">关闭服务器</a>
	<form enctype="multipart/form-data"
		action="${pageContext.request.contextPath}/serverletmode/UploadServerletddd" method="post">
		<input type="text" name="title" /><br /> 
		<input type="file"
			name="picFile" />
		<button>提交数据</button>
	</form>

   <%-- <form action="${pageContext.request.contextPath}/serverletmode/UploadServerletddd">
   <input type="text" name="title"/>
   <button>提交</button>
   </form> --%>

</body>
</html>