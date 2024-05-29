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
	            <h4>Relatório de Exames</h4>
	        	<s:form action="downloadExameRealizado" class="form-horizontal" >
	                <s:push value="exameRealizadoKey">
	                <s:textfield name="dtInicial" type="date" class="input-small" label="Data Inicial" required="true" />
	                <s:textfield name="dtFinal" type="date" class="input-small" label="Data Final" required="true" />
	                <br>
                    <s:submit value="Download"/>
	                </s:push>
	            </s:form>
	            <br>
	            <hr>
	            <br>
	        	<s:form action="saveRealizarExame" class="form-horizontal">
	                <s:push value="exameRealizadoKey">
	                	<h4>Realizar Exame</h4>
						
						<s:select label="Exame" headerKey="0" list="exameList" listValue="%{nome}" 
							listKey="id" name="exameId" />
							
						<s:select label="Funcionário" headerKey="0" list="funcionarioList" listValue="%{nome}" 
							listKey="id" name="funcionarioId" />
							
	                   <s:submit value="Gravar"/>
	                </s:push>
	            </s:form>
	            <br>
	            <hr>
	            <br>
	            <s:form action="saveOrUpdateExame" class="form-horizontal">
	            	<h4>Novo Exame</h4>
	                <s:push value="exame">
	                    <s:hidden name="id" />
	                    <s:textfield name="nome" label="Nome Exame" required="true"/>
	                    <s:radio id="ativo" name="ativo" title="Ativo"   list="#{ '1' : 'Ativo'}" required="true" />
						<s:radio id="ativo" name="ativo" title="Inativo" list="#{ '0' : 'Inativo'}" required="true" />
	                    <s:submit value="Gravar"/>
	                </s:push>
	            </s:form>
	            
	            <hr>
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
		                                    <s:url id="editURL" action="editExame">
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
	            
	            <br>
	            <br>
	            <br>
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