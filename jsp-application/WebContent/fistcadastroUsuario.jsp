<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="resource/css/estilo.css">

</head>
<body>	
	<div class="limiter" style="background-image: url('resource/images/bg-01.jpg');">
		<div class="form-style-6">
			<h1>Cadastro de Usuário</h1>
			<form action='UsuarioServlet' method="POST">
				<input type="text" name='id' readonly="readonly"
					value="${ usuario.getId() }" placeholder="Código" /> <input
					type="text" name='usuario' value="${ usuario.getUsuario() }"
					placeholder="Nome" /> <input type='password' name='senha'
					value="${ usuario.getSenha() }" placeholder="Senha" /> <input
					type="submit" value="Cadastrar" />
			</form>
		</div>

		<h2>Lista de Usuários</h2>
		<table>
			<tr>
				<th>Id</th>
				<th>Usuário</th>
				<th>Senha</th>
			</tr>
			<c:forEach items="${ usuarios }" var="usuario">
				<tr>
					<td><c:out value="${ usuario.getId() }" /></td>
					<td><c:out value="${ usuario.getUsuario() }" /></td>
					<td><c:out value="${ usuario.getSenha() }" /></td>
					<td><a
						href="UsuarioServlet?acao=deletar&usuario=${ usuario.usuario }">Excluir</a>
					</td>
					<td><a href="UsuarioServlet?acao=editar&id=${ usuario.id }">Editar</a>
					</td>
				</tr>
			</c:forEach>
		</table>

	</div>
</body>
</html>