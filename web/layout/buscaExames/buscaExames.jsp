<section class="container row">

    <div class="row">
        <section class="welcomeUser">
            <h4>Faça uma busca:</h4>
        </section>
        <jsp:include page='<%= "formBuscaExames.jsp"%>' />
    </div>

    <div class="divider"></div>

    <div clas="row">

        <section class="welcomeUser">
            <h4>Resultados de Exames:</h4>
        </section>

        <jsp:include page='<%= "exameTabela.jsp"%>' />
    </div>
</section>