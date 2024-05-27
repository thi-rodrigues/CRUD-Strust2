<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@taglib uri="/struts-tags" prefix="s" %>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <link href="../style.css" rel="stylesheet" media="screen">
            <title>Cadastrar Exame</title>
            <sb:head />
        </head>

        <body>
			<div class="content">
	            <s:form action="saveOrUpdateExame" enctype="multipart/form-data" theme="bootstrap" cssClass="form-horizontal">
	                <s:push value="exame">
	                    <s:hidden name="id" />
	                    <s:textfield name="name" label="Exame Name" placeholder="Nome" required="true" />
	                    <s:radio name="gender" label="Gender" list="{'Ativo','Inativo'}" />
	                    <s:select name="country" list="{'India','USA','UK'}" headerKey="" headerValue="Select"
	                        label="Select a country" />
	                    <s:textarea name="aboutYou" label="About You" />
	                    <s:submit />
	                </s:push>
	            </s:form>

            	<s:if test="exameList.size() > 0">
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
				</s:if>
			</div>
        </body>

        </html>