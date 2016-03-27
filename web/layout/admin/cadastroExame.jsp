<%@page import="java.util.List"%>
<%@page import="model.Usuario"%>
<div id="modalAddExame" class="modal modal-fixed-footer">    
    <form id="cadastrarFormulario" method="POST" action="control" class="">
        <div class="modal-content">

            <div class="content-center msgs">
                <div class="topMsg">
                    <span class="wlcMessage">Cadastro de exame</span>

                </div>

            </div>
            <p><label>* Campos Obrigatórios</label></p>
            <div class="row">
                <div class="input-field col s12">

                    <input  id="descrCampo" type="text" class="validate" name="descricao">

                    <label id="descrLabel" for="descrCampo"><span class="cancelRed">*</span>Descrição(Nome)</label>

                </div>                
            </div>

            <div class="row">
                <div class="input-field col s12 m4">

                    <input  id="custoCampo" type="number" class="validate" name="custo">

                    <label id="custoLabel" for="custoCampo"><span class="cancelRed">*</span>Custo</label>

                </div>

                <script>
                    $(document).ready(function () {
                        $('#pessoaCampo').material_select();
                    });
                </script>

                <div class="input-field col s12 m8">

                    <select id="pessoaCampo" name="pessoa">
                        <option value="" disabled selected>Pessoa</option>

                        <%
                            List<Usuario> usuarios = (List<Usuario>) request.getAttribute("users");
                            if (usuarios != null) {
                                for (Usuario usu : usuarios) {
                                    if (!usu.getIsAdmin()) {
                        %>
                        <option value="<%= usu.getLogin()%>" ><%= usu.getPessoa().getNome() + " - " + usu.getLogin()%></option>
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

                        <input  id="jejumCampo" type="text" class="validate" name="jejum" maxlength="2">

                        <label id="jejumLabel" for="jejumCampo"><span class="cancelRed">*</span>Tempo de jejum(hrs)</label>

                    </div>                
                </div>             
            </div>
                        
            <div class="row">                
                <div class="col s12">
                    
                    <div class="input-field col s12 m6">
                        
                        <input  id="dataEntregaCampo" type="date" class="datepicker" name="dataEntrega" value="Data de entrega">

                    </div>                

                    <div class="input-field col s12 m6">

                        <input  id="horaEntregaCampo" type="text" class="validate" name="horaEntrega" maxlength="2">

                        <label id="horaEntregaLabel" for="horaEntregaCampo"><span class="cancelRed">*</span>Hora de entrega(Hrs)</label>

                    </div>                
                </div>             
            </div>

        </div>


        <div class="modal-footer">
            
            <button style="background-color: initial !important;" class="modal-action waves-effect btn-flat" type="submit" id="btn-cadastrar" name="page" value="addExame">
                Confirmar Cadastro
                <i class="material-icons right">playlist_add</i>
            </button>
        </div>

    </form>

    <script src="js/validaCadastroExame.js"></script>
</div>

<script>
                    $(document).ready(function () {

                       $('.modal-trigger').leanModal({
                            complete: function() { $("div.lean-overlay").remove(); } 
                          }
                        );

                    });
</script>
