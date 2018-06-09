<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formul&aacute;rio de livro</title>
</head>
<body>
  <!-- form action="${spring:mvcUrl("LC#salvarLivro").build()}" method="POST"-->
  <form:form action="${spring:mvcUrl('LC#salvarLivro').build()}" commandName="livro" method="POST" enctype="multipart/form-data">
    <div>
      <label>T&iacute;tulo</label>
      <form:input path="titulo"/>
      <form:errors path="titulo"/>
    </div>
    
    <div>
      <label>Autor: </label>
      <!-- input type="text" name="autor"/-->
      <form:input path="autor"/>
      <form:errors path="autor"/>
    </div>
      
    <div>
      <label>N&uacute;mero de p&aacute;ginas: </label>
      <!-- input type="text" name="numPaginas"/-->
      <form:input path="numPaginas"/>
      <form:errors path="numPaginas"/>
    </div>
    
       
    <div>
      <label>Descri&ccedil;&atilde;o: </label>
      <!-- input type="text" name="descricao"/-->
      <form:input path="descricao"/>
      <form:errors path="descricao"/>
    </div>
       
    <div>
      <label>Data de lan&ccedil;amento: </label>
      <input type="date" name="dataLancamento"/>
    </div>
    
    <div>
      <label>Sum&aacute;rio: </label>
      <input type="file" name="sumario"/>
    </div>
    
    <c:forEach items="${tiposLivro}" var="tipo" varStatus="status">
      
	    <div>
	      <label>${tipo}: </label>
	      <!-- input type="text" name="precos[${status.index}].valor"/>
          <input type="hidden" name="precos[${status.index}].tipo" value="${tipo}"/-->
	      <form:input path="precos[${status.index}].valor"/>
          <input type="hidden" name="precos[${status.index}].tipo" value="${tipo}"/>
	    </div>
    
    </c:forEach>
        
    <div>
      <input type="submit" value="Salvar"/>
    </div>
  </form:form>

</body>
</html>