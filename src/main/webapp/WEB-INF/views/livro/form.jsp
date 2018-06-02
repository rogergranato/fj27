<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formul&aacute;rio de livro</title>
</head>
<body>
  <form action="${spring:mvcUrl("LC#salvarLivro").build()}">
    <div>
      <label>T&iacute;tulo</label>
      <input type="text" name="titulo"/>
    </div>
    
    <div>
      <label>Autor</label>
      <input type="text" name="autor"/>
    </div>
      
    <div>
      <label>N&uacute;mero de p&aacute;ginas</label>
      <input type="text" name="numPaginas"/>
    </div>
    
    <div>
      <input type="submit" value="Salvar"/>
    </div>
    
    <c:forEach items="${tiposLivro}" var="tipo" varStatus="status">
      
	    <div>
	      <label>${tipo}</label>
	      <input type="text" name="precos[${status.index}].valor"/>
          <input type="hidden" name="precos[${status.index}].tipo" value="${tipo}"/>

	    </div>
    
    </c:forEach>
        
  </form>

</body>
</html>