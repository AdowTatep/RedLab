<%@page import="model.Exame"%>
<%@page import="model.Exame"%>
<%

    Exame searchExame = (Exame) request.getAttribute("searchExame");

%>

<div class="row col s12 m8 card">
    <form class="card-content">

        <div class="row">
            <div class="input-field col s12">

                <input  id="loginCampo" type="text" class="validate" name="descricaoExame" >
                <label id="loginLabel" for="loginCampo" value="<%= (searchExame != null) ? searchExame.getDescricao() : "" %>"><span class="cancelRed">*</span>Descrição</label>

            </div>
        </div>

        <input type="hidden" name="page" value="searchExame"></input>
        <button class="btn waves-effect red darken-4" type="submit" id="btn-cadastrar" >
            Buscar
            <i class="material-icons right">search</i>
        </button>
        <a href="/RedLab" class="btn waves-effect red darken-4" type="button" id="btn-voltar" />
        Voltar
        <i class="material-icons right">undo</i>
        </a>

    </form>
</div>