<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@taglib prefix="s" uri="/struts-tags" %>
    <%@taglib prefix="sb" uri="/struts-bootstrap-tags" %>
       <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <link href="../style.css" rel="stylesheet" media="screen">
            <title>Realizar Exame</title>
            <sb:head />
            <style type="text/css">
                @import url(style.css);
                body {color: #000000;}
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
				  <span class="divider">|</span></li>
				  <li><s:a href="telaCadastrarFuncionario.action">Cadastrar Funcionário</s:a> <span class="divider"></span></li>
				  <li><s:a href="listFuncionario.action">Funcionários</s:a> <span class="divider"></span></li>
				</ul>
				
				<s:form action="saveRealizarExame" class="form-horizontal">
	                <s:push value="exameRealizadoKey">
	                	<h4>Realizar Exame</h4>
						
						<s:select label="Exame" headerKey="0" list="exameList" listValue="%{nome}" style="color: white"
							listKey="id" name="exameId" />
							
						<s:select label="Funcionário" headerKey="0" list="funcionarioList" listValue="%{nome}" style="color: white" 
							listKey="id" name="funcionarioId" />
							
	                   <s:submit value="Gravar"/>
	                </s:push>
	            </s:form>
				
	            <br>
	            <hr>
	            <br>
	        </div>
        </body>
	</html>