<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listagem de Livros</title>
</head>
<body>
  <center>
  
  <p>
	  <font color="red">${successo}</font>
  </p>
  <table border=1>
    <th width="35%">T&iacute;tulo</th>
    <th width="35%">Autor</th>
    <th width="30%">Pre&ccedil;os</th>
    
        <c:forEach items="${livros}" var="livro">
      
        <tr>
		    <td>${livro.titulo}</td>
		    <td>${livro.autor}</td>
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