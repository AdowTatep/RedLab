<section class="row">
    <div class="cadastroForm col s12 m5 content-center ">
        <div class="card">            
            <form method="POST" action="home" class="">
                <div class="card-content">

                    <div class="content-center msgs">
                        <div class="topMsg">
                            <span class="wlcMessage">Cadastro de usuário</span>
                        </div>
                    </div>

                    <div class="row">
                        <div class="input-field col s12 m6">

                            <input  id="loginCampo" type="text" class="validate" name="login">

                            <label for="loginCampo">Login</label>

                        </div>
                            
                        <div class="input-field col s12 m6">

                            <input  id="senhaCampo" type="password" class="validate" name="senha">

                            <label for="senhaCampo">Senha</label>

                        </div>
                    </div>

                    <div class="row">
                        <div class="input-field col s12">

                            <input  id="nomeCampo" type="text" class="validate" name="nome">

                            <label for="nomeCampo">Nome</label>

                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="input-field col s12 m8">

                            <input  id="telefoneCampo" type="text" class="validate" name="telefone">

                            <label for="telefoneCampo">Telefone</label>

                        </div>
                        
                        <script>
                            $(document).ready(function() {
                                $('#sexoCampo').material_select();
                            });
                        </script>
                        
                        <div class="input-field col s12 m4">

                            <select id="sexoCampo">
                                <option value="" disabled selected>Sexo</option>
                                <option value="M" >Masculino</option>
                                <option value="F" >Feminino</option>
                            </select>
                            <label for="sexoCampo">Escolha um sexo</label>

                        </div>
                        
                    </div>
                    
                    <div class="row">
                        <div class="input-field col s12">

                            <input  id="cpfCampo" type="text" class="validate" name="cpf">

                            <label for="cpfCampo">CPF</label>

                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="input-field col s12">

                            <input  id="enderecoCampo" type="text" class="validate" name="endereco">

                            <label for="enderecoCampo">Endereço</label>

                        </div>
                    </div>

                </div>


                <div class="card-action">
                    <button class="btn waves-effect red darken-4" type="submit" name="page" value="cadastro">
                        Confirmar Cadastro
                        <i class="material-icons right">person_add</i>
                    </button>
                </div>
            </form>
        </div>
    </div>
</section>