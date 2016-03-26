<jsp:include page='<%= "cadastroPessoa.jsp" %>' />
<jsp:include page='<%= "cadastroExame.jsp" %>' />


<div class="fixed-action-btn horizontal" style="top: 80px; right: 30px;">
    <a class="btn-floating btn-large green darken-1" >
        <i class="large material-icons">add</i>
    </a>
    <ul>
        <li><a class="btn-floating blue-grey tooltipped" href="control?page=searchExames" data-position="bottom" data-delay="50" data-tooltip="Exames"> <i class="material-icons">assignment</i></a></li>
        <li><a class="btn-floating purple tooltipped" href="control?page=searchPessoa" data-position="bottom" data-delay="50" data-tooltip="Pessoas"> <i class="material-icons">assignment_ind</i></a></li>
        <li><a class="btn-floating red tooltipped modal-trigger" href="#modalAddExame" data-position="bottom" data-delay="50" data-tooltip="Criar tipo de exame"> <i class="material-icons">playlist_add</i></a></li>
        <li><a class="btn-floating blue tooltipped modal-trigger" href="#modalAddPessoa" data-position="bottom" data-delay="50" data-tooltip="Criar uma pessoa"><i class="material-icons">person_add</i></a></li>
    </ul>
</div>