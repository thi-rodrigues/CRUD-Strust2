<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@taglib prefix="s" uri="/struts-tags" %>
    <%@taglib prefix="sb" uri="/struts-bootstrap-tags" %>
       <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <link href="../style.css" rel="stylesheet" media="screen">
            <title>Relatório de Exames</title>
            <sb:head />
            <style type="text/css">
                @import url(style.css);
            </style>
        </head>

        <body>
        	<script src="https://code.jquery.com/jquery.js"></script>
    		<script src="js/bootstrap.min.js"></script>
    		
    		
	        <div class="container">
				<ul class="breadcrumb">
				  <li><s:a href="listExame.action">Exames Realizados</s:a> <span class="divider"></span></li>
				  <li><s:a href="telaCadastrar.action">Cadastrar Exames</s:a> <span class="divider"></span></li>
				  <li><s:a href="telaExames.action">Exames</s:a> <span class="divider"></span></li>
				  <li><s:a href="telaRealizarExames.action">Realizar Exames</s:a> <span class="divider"></span></li>
				  <li><s:a href="telaRelatorioExames.action">Download Exames</s:a> <span class="divider"></span></li>
				</ul>
				
				<h4>Relatório de Exames</h4>
	        	<s:form action="downloadExameRealizado" class="form-horizontal" >
	                <s:push value="exameRealizadoKey">
		                <s:textfield name="dtInicial" type="date" class="input-small" label="Data Inicial" required="true" />
		                <s:textfield name="dtFinal" type="date" class="input-small" label="Data Final" required="true" />
	                    <s:submit value="Download"/>
	                </s:push>
	            </s:form>
	            <br>
	            <hr>
	            <br>
	        </div>
        </body>
	</html>