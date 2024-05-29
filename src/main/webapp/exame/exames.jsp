<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@taglib prefix="s" uri="/struts-tags" %>
    <%@taglib prefix="sb" uri="/struts-bootstrap-tags" %>
       <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <link href="../style.css" rel="stylesheet" media="screen">
            <title>Exames</title>
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
				
	            <s:form action="findExames">
	            	<h4>Pesquisar Exame</h4>
	                <s:push value="exame">
	                    <s:textfield name="id" label="Cod. Exame"/>
	                    <s:textfield name="nome" label="Nome Exame" />
	                    <s:radio id="ativo" name="ativo" title="Ativo"   list="#{ '1' : 'Ativo'}"/>
						<s:radio id="ativo" name="ativo" title="Inativo" list="#{ '0' : 'Inativo'}"/>
						<s:radio id="ativo" name="ativo" title="Ambos"   list="#{ '2' : 'Ambos'}"/>
	                    <s:submit value="Buscar"/>
	                </s:push>
	            </s:form>
	            
	            <hr>
	
	            <s:if test="exameList.size() > 0">
	            	<table class="table table-striped">
	                   	<thead>
	                        <tr>
	                            <th>Cod.</th>
	                            <th>Nome</th>
	                            <th>Editar</th>
	                            <th>Deletar</th>
	                        </tr>
                        </thead>
	                        <s:iterator value="exameList" status="exameStatus">
	                        	<tbody>
		                            <tr>
		                                <td>
		                                    <s:property value="id" />
		                                </td>
		                                <td>
		                                    <s:property value="nome" />
		                                </td>
		                                <td>
		                                    <s:url id="editURL" action="update">
		                                        <s:param name="id" value="%{id}"></s:param>
		                                    </s:url>
		                                    <s:a href="%{editURL}">Editar </s:a>
		                                </td>
		                                <td>
		                                    <s:url id="deleteURL" action="deleteExame">
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