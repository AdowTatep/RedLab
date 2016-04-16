<%@page import="model.Exame"%>
<%@page import="java.util.List"%>
<%@page import="model.Usuario"%>

<div class="col s12">
    <%
        List<Exame> exames = (List<Exame>) request.getAttribute("listExames");
    %>
    <table class="highlight bordered">
        <thead>
        <tr>
            <th data-field="login">Id</th>
            <th data-field="nome">Descrição</th>
            <th data-field="telefone">Tempo Jejum</th>
            <th data-field="cpf">Data Entrega</th>
            <th data-field="sexo">Hora Entrega</th>
            <th data-field="endereco">Entregue</th>
            <th data-field="temPlano">Usuario</th>
            <th>Preço</th>
        </tr>
        </thead>

        <tbody>
            <%
                if (exames != null) {
                    for (Exame exam : exames) {
                        %>
                            <tr>
                                <td><%= exam.getId() %></td>
                                <td><%= exam.getDescricao() %></td>
                                <td><%= exam.getTempoJejum() %></td>
                                <td><%= exam.getHoraEntrega() %></td>
                                <td><%= (exam.getIsEntregue() ? "<i class='material-icons checkGreen'>check_circle</i>" : "<i class='material-icons cancelRed'>cancel</i>"%></td>
                                <td><%= exam.getLoginPessoa().getNome() %></td>
                                <td><%= exam.getCusto() %></td>
                                <td>
                                    <form method="post" action="control">
                                        <input  id="loginCampo" type="hidden" name="descricao" maxlength="20" value="<%= exam.getDescricao() %>">
                                        <input  id="nomeCampo" type="hidden" name="tempojejum" maxlength="150" value="<%= exam.getTempoJejum() %>">
                                        <input  id="telefoneCampo" type="hidden" name="horaentrega" maxlength="20" value="<%= exam.getHoraEntrega() %>">
                                        <input  id="sexoCampo" type="hidden" name="usuario" maxlength="1" value="<%= exam.getLoginPessoa() %>">
                                        <input  id="enderecoCampo" type="hidden" name="custo"  value="<%= exam.getCusto() %>">
                                        <input type="hidden" name="entregue" value="<%= exam.getIsEntregue() %>"                                        
                                                                                
                                        <button class="btn-floating btn-flat waves-effect waves-circle blue darken-2" type="submit" name="page"  value="editarPessoa" >
                                            <i class="material-icons">edit</i>
                                        </button>
                                        <button class="btn-floating btn-flat waves-effect waves-circle red darken-4" type="submit" name="page"  value="deletarPessoa" >
                                            <i class="material-icons">delete_forever</i>
                                        </button>
                                    </form>                                        
                                </td>
                            </tr>
                        <%
                        
                    }
                }
            %>
        </tbody>
    </table>
</div>