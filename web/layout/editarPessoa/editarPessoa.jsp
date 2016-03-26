<%@page import="controllers.Mensagem"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Usuario"%>
<%
    Usuario editarPessoa = (Usuario) request.getAttribute("usuario");
    ArrayList<Mensagem> mensagens = (ArrayList<Mensagem>) request.getAttribute("msgs");
%>

<section class="row">
    <div class="cadastroForm col s12 m5 content-center ">
        <div class="card">            

            <form id="cadastrarFormulario" method="POST" action="control" class="">
                <div class="card-content">
                    <div class="content-center msgs">
                        <div class="topMsg">
                            <span class="wlcMessage">Editar Cliente</span>
                        </div>                        

                        <%
                            if (mensagens != null) {
                        %>
                        <div class="errorsMsg">
                             <%
                                 for (Mensagem msg : mensagens) {
                                     if (msg.getContexto().equals("cadastro")) {
                             %>
                             <p><%= msg.getMensagem()%></p>
                            <%
                                    }
                                }
                            %>
                        </div>
                        <%
                            }
                        %>

                    </div>
                    <p><label>* Campos Obrigat�rios</label></p>
                    <div class="row">

                        <div class="input-field col s12 m6">
                            <div>
                                <%
                                    if (mensagens != null) {
                                        for (Mensagem msg : mensagens) {
                                            if (msg.getContexto().equals("login")) {
                                %>
                                <p><%= msg.getMensagem()%></p>
                                <%
                                            }
                                        }
                                    }
                                %>
                            </div>
                            <input  id="loginCampo" type="text" class="validate" name="login" maxlength="20" value="<%= editarPessoa.getLogin()%>">

                            <label id="loginLabel" for="loginCampo"><span class="cancelRed">*</span>Login</label>

                        </div>         

                        <div class="input-field col s12 m6">
                            
                            <input type="checkbox" id="adminCampo" name="isAdmin" />

                            <label id="adminLabel" for="adminCampo">� admin?</label>

                        </div>
                    </div>

                    <div class="row">
                        <div class="input-field col s12">
                            <div>
                                <%
                                    if (mensagens != null) {
                                        for (Mensagem msg : mensagens) {
                                            if (msg.getContexto().equals("nome")) {
                                %>
                                <p><%= msg.getMensagem()%></p>
                                <%
                                            }
                                        }
                                    }
                                %>
                            </div>
                            <input  id="nomeCampo" type="text" class="validate" name="nome" maxlength="150" value="<%= editarPessoa.getPessoa().getNome() %>">

                            <label id="nomeLabel" for="nomeCampo"><span class="cancelRed">*</span>Nome</label>

                        </div>
                    </div>

                    <div class="row">
                        <div class="input-field col s12 m8">
                            <div>
                                <%
                                    if (mensagens != null) {
                                        for (Mensagem msg : mensagens) {
                                            if (msg.getContexto().equals("telefone")) {
                                %>
                                <p><%= msg.getMensagem()%></p>
                                <%
                                            }
                                        }
                                    }
                                %>
                            </div>
                            <input  id="telefoneCampo" type="text" class="validate" name="telefone" maxlength="20" value="<%= editarPessoa.getPessoa().getTelefone()%>">

                            <label id="telefoneLabel" for="telefoneCampo"><span class="cancelRed">*</span>Telefone</label>

                        </div>

                        <script>
                            $(document).ready(function () {
                                $('#sexoCampo').material_select();
                            });
                        </script>

                        <div class="input-field col s12 m4">
                            <div>
                                <%
                                    if (mensagens != null) {
                                        for (Mensagem msg : mensagens) {
                                            if (msg.getContexto().equals("sexo")) {
                                %>
                                <p><%= msg.getMensagem()%></p>
                                <%
                                            }
                                        }
                                    }
                                %>
                            </div>
                            <select id="sexoCampo" name="sexo">
                                <option value="" disabled <%= (!editarPessoa.getPessoa().getSexo().equals("M") && !editarPessoa.getPessoa().getSexo().equals("F")) ? "selected" : ""%> >Sexo</option>
                                <option value="M" <%= (editarPessoa.getPessoa().getSexo().equals("M")) ? "selected" : ""%>>Masculino</option>
                                <option value="F" <%= (editarPessoa.getPessoa().getSexo().equals("F")) ? "selected" : ""%>>Feminino</option>
                            </select>
                            <label id="sexoLabel" for="sexoCampo"><span class="cancelRed">*</span>Escolha um sexo</label>

                        </div>

                    </div>

                    <div class="row">
                        <div class="input-field col s12">
                            <div>
                                <%
                                    if (mensagens != null) {
                                        for (Mensagem msg : mensagens) {
                                            if (msg.getContexto().equals("cpf")) {
                                %>
                                <p><%= msg.getMensagem()%></p>
                                <%
                                            }
                                        }
                                    }
                                %>
                            </div>
                            <input  id="cpfCampo" type="text" class="validate" name="cpf" maxlength="11" value="<%= editarPessoa.getPessoa().getCpf()%>">

                            <label id="cpfLabel" for="cpfCampo"><span class="cancelRed">*</span>CPF</label>

                        </div>
                    </div>

                    <div class="row">
                        <div class="input-field col s12">
                            <div>
                                <%
                                    if (mensagens != null) {
                                        for (Mensagem msg : mensagens) {
                                            if (msg.getContexto().equals("endere�o")) {
                                %>
                                <p><%= msg.getMensagem()%></p>
                                <%
                                            }
                                        }
                                    }
                                %>
                            </div>
                            <input  id="enderecoCampo" type="text" class="validate" name="endereco" maxlength="255" value="<%= editarPessoa.getPessoa().getEndereco()%>">

                            <label id="enderecoLabel" for="enderecoCampo">Endere�o</label>

                        </div>
                    </div>

                </div>

                <div class="card-action">

                    <input type="hidden" name="page" value="confirmarEditarPessoa"></input>
                    
                    <button class="btn waves-effect red darken-4" type="submit" id="btn-cadastrar" >
                        Confirmar Altera��o
                        <i class="material-icons right">person_add</i>
                    </button>

                    <a href="control?page=searchPessoa" class="btn waves-effect red darken-4" type="button" id="btn-voltar" >
                        Voltar
                        <i class="material-icons right">undo</i>
                    </a>
                </div>
            </form>
        </div>
    </div>
</section>