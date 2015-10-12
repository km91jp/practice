<html>
<head>
<title>todo login</title>
</head>
<body>
	<div id="title">
		<h1>Login</h1>
	</div>
	<div id="loginForm">

		<c:if test="${param.error}">
			<t:messagesPanel messagesType="error"
				messagesAttributeName="SPRING_SECURITY_LAST_EXCEPTION" />
		</c:if>

		<form:form action="${pageContext.request.contextPath}/authenticate">
			<input type="text" id="j_username" name="j_username" value='demo'>(demo)</td>
			<input type="password" id="j_password" name="j_password" value="demo" />(demo)</td>
			<input name="submit" type="submit" value="Login" />
		</form:form>
	</div>
</body>
</html>