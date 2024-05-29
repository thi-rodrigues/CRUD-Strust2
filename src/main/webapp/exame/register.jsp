<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@taglib prefix="s" uri="/struts-tags" %>
    <%@taglib prefix="sb" uri="/struts-bootstrap-tags" %>
       <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <link href="../style.css" rel="stylesheet" media="screen">
            <title>Cadastrar Exame</title>
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
				
	            <!--  EXAME REALIZADO  -->
	            <s:if test="examesRealizados.size() > 0">
	            	<h3> EXAMES REALIZADOS </h3>
	            	<table class="table table-striped">
	                   	<thead>
	                   		<tr>
	                         <th>Cod. Exame</th>
	                         <th>Nome Exame</th>
	                         <th>Ativo</th>
	                         <th>Detalhe Exame</th>
	                         <th>Detalhe Eexame1</th>
	                         <th>Cód. Funcionário</th>
	                         <th>Nome Funcionário</th>
	                         <th>Realização</th>
	                         <th>Ação</th>
	                     </tr>
	                   	</thead>
	                        <s:iterator value="examesRealizados" status="examesRealizadosStatus">
		                        <tbody>
		                            <tr>
		                                <td><s:property value="exame.id" /></td>
		                                <td><s:property value="exame.nome" /></td>
		                                <td><s:property value="exame.ativo" /></td>
		                                <td><s:property value="exame.dsDetalheExame" /></td>
		                                <td><s:property value="exame.dsDetalheExame1" /></td>
		                                <td><s:property value="funcionario.id" /></td>
		                                <td><s:property value="funcionario.nome" /></td>
		                                <td><s:property value="dtRealizacao"  /></td>
		                                <td>
		                                	<s:url id="deleteURL" action="deleteExameRealizado">
		                                        <s:param name="exameId" value="%{exame.id}"></s:param>
		                                        <s:param name="funcionarioId" value="%{funcionario.id}"></s:param>
		                                    </s:url>
		                                    <s:a href="%{deleteURL}">Deletar</s:a>
		                                </td>
		                            </tr>
		                        </tbody>
	                        </s:iterator>
	                    </table>
	            </s:if>
	        </div>
        </body>
	</html>