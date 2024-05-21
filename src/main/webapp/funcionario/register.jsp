<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@taglib uri="/struts-tags" prefix="s" %>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <title>Cadastrar Funcionario</title>
            <s:head />
            <style type="text/css">
                @import url(style.css);
            </style>
        </head>

        <body>
            <s:form action="saveOrUpdateFuncionario">
                <s:push value="funcionario">
                	<s:hidden name="id" />
                    <s:textfield name="nome" label="Nome Funcionario " />
                    <s:submit value="Gravar"/>
                </s:push>
            </s:form>
		<hr>
		<br>
            <s:if test="funcionarioList.size() > 0">
                <div class="content">
                    <table class="funcionarioTable" cellpadding="5px">
                        <tr class="even">
                            <th>Cod.</th>
                            <th>Nome</th>
                            <th>Editar</th>
                            <th>Deletar</th>
                        </tr>
                        <s:iterator value="funcionarioList" status="funcionarioStatus">
                            <tr class="odd">
                                <td>
                                    <s:property value="id" />
                                </td>
                                <td>
                                    <s:property value="nome" />
                                </td>
                                <td>
                                    <s:url id="editURL" action="editFuncionario">
                                        <s:param name="id" value="%{id}"></s:param>
                                    </s:url>
                                    <s:a href="%{editURL}">Editar</s:a>
                                </td>

                                <td>
                                    <s:url id="deleteURL" action="deleteFuncionario">
                                        <s:param name="id" value="%{id}"></s:param>
                                    </s:url>
                                    <s:a href="%{deleteURL}">Deletar</s:a>
                                </td>
                            </tr>
                        </s:iterator>
                    </table>
                </div>
            </s:if>
        </body>

        </html>