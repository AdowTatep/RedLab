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
            <th data-field="nome">Cliente</th>
            <th data-field="cpf">Descrição</th>
            <th data-field="endereco">Entregue</th>
            <th data-field="temPlano">Custo</th>
        </tr>
        </thead>

        <tbody>
            <%
                if (exames != null) {
                    for (Exame exame : exames) {
                        %>
                            <tr>
                                <td><%= exame.getId() %></td>
                                <td><%= exame.getLoginPessoa().getNome() %></td>
                                <td><%= exame.getDescricao() %></td>
                                <td><%= (exame.getIsEntregue()) ? "<i class='material-icons checkGreen'>check_circle</i>" : "<i class='material-icons cancelRed'>cancel</i>" %></td>
                                <td>R$<%= exame.getCusto() %></td>
                                <td>
                                    <form method="post" action="control">
                                        <input  id="loginCampo" type="hidden" name="descricao" maxlength="20" value="<%= exame.getDescricao() %>">
                                        <input  id="sexoCampo" type="hidden" name="usuario" maxlength="1" value="<%= exame.getLoginPessoa().getLogin() %>">
                                        <input  id="enderecoCampo" type="hidden" name="custo"  value="<%= exame.getCusto() %>">                                        
                                                                               
                                        <button class="btn-floating btn-flat waves-effect waves-circle blue darken-2" type="submit" name="page"  value="editarExame" >
                                            <i class="material-icons">edit</i>
                                        </button>
                                        <button class="btn-floating btn-flat waves-effect waves-circle red darken-4" type="submit" name="page"  value="deletarExame" >
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