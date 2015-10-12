<html>
<head>
<title>Todo App</title>
<sec:authentication property="principal.account" var="account" />
</head>
<body>
	<div id="header">
		<form:form action="${pageContext.request.contextPath}/logout">
			<h1>${f:h(account.name)}'sTodoList</h1>
			<button>Logout</button>
		</form:form>
	</div>
	<div id="create">
		<form:form action="${pageContext.request.contextPath}/todo/create"
			method="post" modelAttribute="todoForm">
			<form:input path="title" />
			<form:button>create todo</form:button>
			<t:messagesPanel />
			<form:errors path="title" cssStyle="color:red" />
		</form:form>
	</div>
	<hr>
	<div id="todoList">
		<ul>
			<c:forEach items="${todoList}" var="todo">
				<li><c:choose>
						<c:when test="${todo.finished}">
							<span style="text-decoration: line-through">${f:h(todo.title)}</span>
						</c:when>
						<c:otherwise>
							<form:form
								action="${pageContext.request.contextPath}/todo/finish"
								method="POST" modelAttribute="todoForm"
								cssStyle="display: inline-block;">
								<span>${f:h(todo.title)}</span>
								<form:hidden path="id" value="${f:h(todo.id)}" />
								<form:button>finish!</form:button>
							</form:form>
						</c:otherwise>
					</c:choose> <form:form
						action="${pageContext.request.contextPath}/todo/delete/${f:h(todo.id)}"
						method="POST" modelAttribute="todoForm"
						cssStyle="display: inline-block;">
						<form:button>delete</form:button>
					</form:form></li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>