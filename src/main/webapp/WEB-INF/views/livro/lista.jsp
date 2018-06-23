<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri = "http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listagem de Livros</title>
</head>
<body>

  <security:authorize access="hasRole('ROLE_ADMIN')">
       <a href="/casadocodigo/livros/form">Cadastrar novo livro</a>     
  </security:authorize>
  
  <security:authorize access="isAuthenticated()">
     <security:authentication property="principal" var="usuariologado"/>
       Ol&aacute; ${usuariologado.usuario} (${usuariologado.email})
     <a href="/casadocodigo/logout">Sair</a>
  </security:authorize>

  <center>
  
  <p>
	  <font color="red">${sucesso}</font>
  </p>
  <table border=1>
    <th width="25%">T&iacute;tulo</th>
    <th width="25%">Autor</th>
    <th width="40%">Descri&ccedil;&atilde;o: </th>
    <!-- th width="5%">Lan&ccedil;amento: </th> -->
    <th width="5%">Pre&ccedil;os</th>
    
        <c:forEach items="${livros}" var="livro">
      
        <tr>
           <c:url value="/livros/detalhe/${livro.id}" var="pathDoLivro"></c:url>
		    <td><a href="${pathDoLivro}">${livro.titulo}</a></td>
		    <td>${livro.autor}</td>
		    <td>${livro.descricao}</td>
		    <td>
		       <c:forEach items="${livro.precos}" var="preco">
		          ${preco.tipo}: ${preco.valor}<br/>
			   </c:forEach>	       
		    </td>     
		</tr>
    
    </c:forEach>

  </table>
</center>
</body>
</html>