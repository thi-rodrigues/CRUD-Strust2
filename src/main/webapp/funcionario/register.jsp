<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@taglib prefix="s" uri="/struts-tags" %>
    <%@taglib prefix="sb" uri="/struts-bootstrap-tags" %>
	<html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <link href="../style.css" rel="stylesheet" media="screen">
            <title>Cadastrar Funcionario</title>
            <sb:head />
        </head>

        <body>
        	<script src="https://code.jquery.com/jquery.js"></script>
    		<script src="js/bootstrap.min.js"></script>
    		
        	<div class="container" style="margin-top: 50px;">
	            <s:form action="saveOrUpdateFuncionario" enctype="multipart/form-data" theme="bootstrap" cssClass="form-horizontal">
	                <s:push value="funcionario">
	                	<s:hidden name="id" />
	                    <s:textfield name="nome" label="Nome Funcionario" placeholder="Nome" required="true"/>
	                    <s:submit value="Gravar"/>
	                </s:push>
	            </s:form>
        	<hr>
			<br>
	            <s:if test="funcionarioList.size() > 0">
	            	<table class="table table-striped">
	                   	<thead>
	                        <tr">
	                            <th>Cod.</th>
	                            <th>Nome</th>
	                            <th style="text-align: center;">A��o</th>
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