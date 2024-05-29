<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@taglib prefix="s" uri="/struts-tags" %>
    <%@taglib prefix="sb" uri="/struts-bootstrap-tags" %>
	<html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <link href="../style.css" rel="stylesheet" media="screen">
            <title>Funcionários</title>
            <sb:head />
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
				
	            <s:if test="funcionarioList.size() > 0">
	            	<table class="table table-striped">
	                   	<thead>
	                        <tr>
	                            <th>Cod.</th>
	                            <th>Nome</th>
	                            <th style="text-align: center;">Ação</th>
	                        </tr>
						</thead>
	                        <s:iterator value="funcionarioList" status="funcionarioStatus">
	                        	<tbody>
		                            <tr class="odd">
		                                <td>
		                                    <s:property value="id" />
		                                </td>
		                                <td>
		                                    <s:property value="nome" />
		                                </td>
		                                <td style="text-align: center;">
		                                    <s:url id="editURL" action="editFuncionario">
		                                        <s:param name="id" value="%{id}"></s:param>
		                                    </s:url>
		                                    <s:a href="%{editURL}" style="padding-right:30px;">Editar</s:a>
		
		                                    <s:url id="deleteURL" action="deleteFuncionario">
		                                        <s:param name="id" value="%{id}"></s:param>
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