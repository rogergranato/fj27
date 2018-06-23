<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="cdc" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<cdc:page title="Formul&aacute;rio de livro">
  <!-- form action="${spring:mvcUrl("LC#salvarLivro").build()}" method="POST"-->
  <form:form action="${spring:mvcUrl('LC#salvarLivro').build()}" commandName="livro" method="POST" enctype="multipart/form-data" servletRelativeAction="/livros">
    
    <!-- security:csrfInput/-->
    
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

</cdc:page>