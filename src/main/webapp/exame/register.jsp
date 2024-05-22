<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@taglib uri="/struts-tags" prefix="s" %>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <title>Cadastrar Exame</title>
            <s:head />
            <style type="text/css">
                @import url(style.css);
            </style>
        </head>

        <body>
        	<s:form action="saveRealizarExame">
                <s:push value="exameRealizadoKey">
                <h2>Realizar Exame</h2>
					
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
            <s:form action="saveOrUpdateExame">
                <s:push value="exame">
                    <s:hidden name="id" />
                    <s:textfield name="nome" label="Nome Exame" />
                    <br>
                    <s:radio id="ativo" name="ativo" title="Ativo"   list="#{ '1' : 'Ativo'}"/>
					<s:radio id="ativo" name="ativo" title="Inativo" list="#{ '0' : 'Inativo'}"/>
                    <s:submit value="Gravar"/>
                </s:push>
            </s:form>
            
            <hr>
            <h3>Pesquisar Exame</h3>
            <s:form action="findExames">
                <s:push value="exame">
                    <s:textfield name="id" label="Cod. Exame"/>
                    <s:textfield name="nome" label="Nome Exame" />
                    <br>
                    <s:radio id="ativo" name="ativo" title="Ativo"   list="#{ '1' : 'Ativo'}"/>
					<s:radio id="ativo" name="ativo" title="Inativo" list="#{ '0' : 'Inativo'}"/>
					<s:radio id="ativo" name="ativo" title="Ambos"   list="#{ '2' : 'Ambos'}"/>
                    <s:submit value="Buscar"/>
                </s:push>
            </s:form>
            
            <hr>

            <s:if test="exameList.size() > 0">
                <div class="content">
                    <table class="exameTable" cellpadding="5px">
                        <tr class="even">
                            <th>Cod.</th>
                            <th>Nome</th>
                            <th>Editar</th>
                            <th>Deletar</th>
                        </tr>
                        <s:iterator value="exameList" status="exameStatus">
                            <tr class="even">
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
                                    <s:a href="%{editURL}">Edit</s:a>
                                </td>

                                <td>
                                    <s:url id="deleteURL" action="deleteExame">
                                        <s:param name="id" value="%{id}"></s:param>
                                    </s:url>
                                    <s:a href="%{deleteURL}">Delete</s:a>
                                </td>
                            </tr>
                        </s:iterator>
                    </table>
                </div>
            </s:if>
        </body>

        </html>