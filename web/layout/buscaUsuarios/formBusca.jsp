<%@page import="model.Usuario"%>
<%

    Usuario searchUser = (Usuario) request.getAttribute("searchUser");

%>

<div class="row col s12 m8 card">
    <form class="card-content">

        <div class="row">
            <div class="input-field col s12">

                <input  id="loginCampo" type="text" class="validate" name="login" >
                <label id="loginLabel" for="loginCampo"><span class="cancelRed">*</span>Login</label>

            </div>
        </div>

        <input type="hidden" name="page" value="searchPessoa"></input>
        <button class="btn waves-effect red darken-4" type="submit" id="btn-cadastrar" >
            Buscar
            <i class="material-icons right">search</i>
        </button>
        <a href="control?page=searchExames" class="btn waves-effect red darken-4" type="button" id="btn-voltar" >
        Desfazer
        <i class="material-icons right">delete</i>
        </a>
        <a href="/RedLab" class="btn waves-effect red darken-4" type="button" id="btn-voltar" >
        Voltar
        <i class="material-icons right">undo</i>
        </a>

    </form>
</div>