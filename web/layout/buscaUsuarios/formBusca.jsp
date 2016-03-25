<%@page import="model.Usuario"%>
<%

    Usuario searchUser = (Usuario)request.getAttribute("searchUser");

%>

<div class="row col s12 m8">
    <form>
        <button style="background-color: initial !important;" class="modal-action waves-effect btn-flat" type="submit" id="btn-cadastrar" name="page" value="addPessoa">
                Buscar
                <i class="material-icons right">person_add</i>
            </button>
    </form>
</div>