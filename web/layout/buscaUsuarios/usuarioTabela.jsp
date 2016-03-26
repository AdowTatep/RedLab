<%@page import="java.util.List"%>
<%@page import="model.Usuario"%>

<div class="col s12">
    <%
        List<Usuario> usuarios = (List<Usuario>) request.getAttribute("users");
    %>
    <table class="highlight bordered">
        <thead>
        <tr>
            <th data-field="login">Login</th>
            <th data-field="nome">Nome</th>
            <th data-field="telefone">Telefone</th>
            <th data-field="cpf">CPF</th>
            <th data-field="sexo">Sexo</th>
            <th data-field="endereco">Endereço</th>
            <th data-field="temPlano">Tem plano</th>
        </tr>
        </thead>

        <tbody>
            <%
                if (usuarios != null) {
                    for (Usuario usu : usuarios) {
                        if (!usu.getIsAdmin()) {
                        %>
                            <tr>
                                <td><%= usu.getLogin() %></td>
                                <td><%= usu.getPessoa().getNome() %></td>
                                <td><%= usu.getPessoa().getTelefone() %></td>
                                <td><%= usu.getPessoa().getCpf() %></td>
                                <td><%= usu.getPessoa().getSexo() %></td>
                                <td><%= usu.getPessoa().getEndereco() %></td>
                                <td><%= (usu.getPessoa().getTemPlanoSaude()) ? "<i class='material-icons checkGreen'>check_circle</i>" : "<i class='material-icons cancelRed'>cancel</i>"%></td>
                                <td>
                                    <form method="post" action="control">
                                        <input  id="loginCampo" type="hidden" name="login" maxlength="20" value="<%= usu.getLogin() %>">
                                        <input  id="nomeCampo" type="hidden" name="nome" maxlength="150" value="<%= usu.getPessoa().getNome() %>">
                                        <input  id="telefoneCampo" type="hidden" name="telefone" maxlength="20" value="<%= usu.getPessoa().getTelefone() %>">
                                        <input  id="sexoCampo" type="hidden" name="sexo" maxlength="1" value="<%= usu.getPessoa().getSexo() %>">
                                        <input  id="enderecoCampo" type="hidden" name="endereco"  value="<%= usu.getPessoa().getEndereco() %>">
                                        
                                                                                
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
                }
            %>
        </tbody>
    </table>
</div>