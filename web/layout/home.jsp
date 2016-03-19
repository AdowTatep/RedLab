<section class="row">
    <div class="loginForm col s12 m5 content-center ">
        <div class="card">            
            <form method="POST" action="control" class="">
                <div class="card-content">

                    <div class="content-center msgs">
                        <div class="topMsg">
                            <span class="wlcMessage">Faça seu login</span>
                        </div>
                    </div>



                        <div class="row">
                            <div class="input-field col s12">

                                <input  id="loginCampo" type="text" class="validate" name="login" maxlength="20">

                                <label for="loginCampo">Login</label>

                            </div>
                        </div>

                        <div class="row">
                            <div class="input-field col s12">

                                <input  id="senhaCampo" type="password" class="validate" name="senha" maxlength="50">

                                <label for="senhaCampo">Senha</label>

                            </div>
                        </div>


                </div>


                <div class="card-action">
                    <button class="btn waves-effect red darken-4" type="submit" name="page" value="cadastro">
                        Cadastrar
                        <i class="material-icons right">person_add</i>
                    </button>


                    <button class="btn waves-effect red darken-4" type="submit" name="page" value="login">
                        Fazer Login
                        <i class="material-icons right">send</i>
                    </button>
                </div>
            </form>
        </div>
    </div>
</section>