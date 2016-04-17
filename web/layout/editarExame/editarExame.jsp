<%@page import="java.util.List"%>
<%@page import="model.Usuario"%>
<%@page import="model.Exame"%>


<section class="row">
    <div class="editar exameForm col s12 m5 content-center ">
        <div class="card">            

            <form id="cadastroExame" method="POST" action="control" class="">
                <div class="card-content">

                    <%
                        Exame exame = (Exame) request.getAttribute("exame");
                    %>
                    <div class="content-center msgs">
                        <div class="topMsg">
                            <span class="wlcMessage">Editar exame</span>

                        </div>

                    </div>
                    <p><label>* Campos Obrigatórios</label></p>
                    <div class="row">
                        <div class="input-field col s12">

                            <input  id="descrCampo" type="text" class="validate" name="descricao" value="<%= exame.getDescricao()%>">

                            <label id="descrLabel" for="descrCampo"><span class="cancelRed">*</span>Descrição(Nome)</label>

                        </div>                
                    </div>

                    <div class="row">
                        <div class="input-field col s12 m4">

                            <input  id="custoCampo" type="number" class="validate" name="custo" value="<%= exame.getCusto()%>">

                            <label id="custoLabel" for="custoCampo"><span class="cancelRed">*</span>Custo</label>

                        </div>

                        <script>
                            $(document).ready(function () {
                                $('#pessoaCampo').material_select();
                            });
                        </script>

                        <div class="input-field col s12 m8">

                            <select id="pessoaCampo" name="pessoa">
                                <option value="" disabled selected><%= exame.getLoginPessoa().getNome()%></option>

                                <%
                                    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuario");
                                    if (usuarios != null) {
                                        for (Usuario usu : usuarios) {
                                            if (!usu.getIsAdmin()) {
                                %>
                                <option value="<%= usu.getLogin()%>" ><%=usu.getPessoa().getNome()%> - <%=usu.getLogin()%></option>
                                <%
                                            }
                                        }
                                    }
                                %>

                            </select>
                            <label id="sexoLabel" for="pessoaCampo"><span class="cancelRed">*</span>Escolha uma pessoa</label>

                        </div>

                    </div>

                    <div class="row">                
                        <div class="col s12">
                            <div class="input-field col s12 m6">

                                <input type="checkbox" id="entregueCampo" name="entregue" />

                                <label id="entregueLabel" for="entregueCampo">Foi entregue?</label>

                            </div>                

                            <div class="input-field col s12 m6">

                                <input  id="jejumCampo" type="text" class="validate" name="jejum" maxlength="2" value="<%= exame.getTempoJejum()%>">

                                <label id="jejumLabel" for="jejumCampo">Tempo de jejum(hrs)</label>

                            </div>                
                        </div>             
                    </div>

                    <div class="row">                
                        <div class="col s12">

                            <div class="input-field col s12 m6">

                                <input  id="dataEntregaCampo" type="date" class="datepicker" name="dataEntrega" value="Data de entrega" value="<%= exame.getDataEntrega()%>">

                            </div>                

                            <div class="input-field col s12 m6">

                                <input  id="horaEntregaCampo" type="text" class="validate" name="horaEntrega" maxlength="2" value="<%= exame.getHoraEntrega()%>">

                                <label id="horaEntregaLabel" for="horaEntregaCampo">Hora de entrega(Hrs)</label>

                            </div>                
                        </div>             
                    </div>

                </div>

                <div class="card-action">

                    <button class="btn waves-effect red darken-4" type="submit" id="btn-cadastrar" name="page" value="ConfirmarEditarExame">
                        Confirmar
                        <i class="material-icons right">playlist_add</i>
                    </button>
                    
                </div>

            </form>
            <!--
    <script src="js/validaCadastroExame.js"></script>
            -->
        </div>
    </div>
</section>

