<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form commandName="loginVO" method="post" action="/HelloMVC/doLogin" enctype="multipart/form-data" >
		id : <input type="text" name="id" />
		<form:errors path="id" cssClass="errors"/><br/>
		pw : <input type="text" name="password" />
		<form:errors path="password" cssClass="errors"/><br/>
		memberNumber : <input type="text" name="memberNumber" />
		<form:errors path="memberNumber" cssClass="errors"/><br/>
		autoLogin : <input type="checkbox" name="enableAutoLogin" value="true" /><br/>
		hobby : <input type="text" name="hobby" /><br/>
		hobby : <input type="text" name="hobby" /><br/>
		hobby : <input type="text" name="hobby" /><br/>
		fileUpload : <input type="file" name="uploadFile" /><br/>
		
		<input type="submit" value="제출"/>
	</form:form>
</body>
</html>