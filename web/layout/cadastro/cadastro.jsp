<section class="row">
    <div class="cadastroForm col s12 m5 content-center ">
        <div class="card">            
            
            <form id="cadastrarFormulario" method="POST" action="cadastrar" class="">
                <div class="card-content">
                    <div class="content-center msgs">
                        <div class="topMsg">
                            <span class="wlcMessage">Cadastro de usuário</span>
                            
                        </div>
                           
                    </div>
                    <p><label>* Campos Obrigatórios</label></p>
                    <div class="row">
                        
                        <div class="input-field col s12 m6">

                            <input  id="loginCampo" type="text" class="validate" name="login" maxlength="20">

                            <label id="loginLabel" for="loginCampo"><span class="cancelRed">*</span>Login</label>

                        </div>
                            
                        <div class="input-field col s12 m6">

                            <input  id="senhaCampo" type="password" class="validate" name="senha" maxlength="50">

                            <label id="senhaLabel" for="senhaCampo"><span class="cancelRed">*</span>Senha</label>

                        </div>
                    </div>

                    <div class="row">
                        <div class="input-field col s12">

                            <input  id="nomeCampo" type="text" class="validate" name="nome" maxlength="150">

                            <label id="nomeLabel" for="nomeCampo"><span class="cancelRed">*</span>Nome</label>

                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="input-field col s12 m8">

                            <input  id="telefoneCampo" type="text" class="validate" name="telefone" maxlength="20">

                            <label id="telefoneLabel" for="telefoneCampo"><span class="cancelRed">*</span>Telefone</label>

                        </div>
                        
                        <script>
                            $(document).ready(function() {
                                $('#sexoCampo').material_select();
                            });
                        </script>
                        
                        <div class="input-field col s12 m4">

                            <select id="sexoCampo" name="sexo">
                                <option value="" disabled selected>Sexo</option>
                                <option value="M" >Masculino</option>
                                <option value="F" >Feminino</option>
                            </select>
                            <label id="sexoLabel" for="sexoCampo"><span class="cancelRed">*</span>Escolha um sexo</label>

                        </div>
                        
                    </div>
                    
                    <div class="row">
                        <div class="input-field col s12">

                            <input  id="cpfCampo" type="text" class="validate" name="cpf" maxlength="11">

                            <label id="cpfLabel" for="cpfCampo"><span class="cancelRed">*</span>CPF</label>

                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="input-field col s12">

                            <input  id="enderecoCampo" type="text" class="validate" name="endereco" maxlength="255">

                            <label id="enderecoLabel" for="enderecoCampo">Endereço</label>

                        </div>
                    </div>

                </div>


                <div class="card-action">
                    <button class="btn waves-effect red darken-4" type="submit" id="btn-cadastrar" name="page" value="cadastro">
                        Confirmar Cadastro
                        <i class="material-icons right">person_add</i>
                    </button>
                </div>
            </form>
            <script src="js/validaCadastro.js"></script>
        </div>
    </div>
</section>