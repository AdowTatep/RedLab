<jsp:include page='<%= "cadastroPessoa.jsp" %>' />      

<div class="fixed-action-btn" style="bottom: 45px; right: 24px;">
    <a class="btn-floating btn-large red darken-4" >
        <i class="large material-icons">add</i>
    </a>
    <ul>
        <li><a class="btn-floating blue-grey tooltipped" data-position="left" data-delay="50" data-tooltip="Exames"> <i class="material-icons">assignment</i></a></li>
        <li><a class="btn-floating purple tooltipped" data-position="left" data-delay="50" data-tooltip="Pessoas"> <i class="material-icons">assignment_ind</i></a></li>
        <li><a class="btn-floating green tooltipped" data-position="left" data-delay="50" data-tooltip="Criar tipo de exame"> <i class="material-icons">playlist_add</i></a></li>
        <li><a class="btn-floating blue tooltipped modal-trigger" href="#modalAddPessoa" data-position="left" data-delay="50" data-tooltip="Criar uma pessoa"><i class="material-icons">person_add</i></a></li>
    </ul>
</div>