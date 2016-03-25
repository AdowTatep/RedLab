<section class="container row">

    <div class="row">
        <section class="welcomeUser">
            <h4>Faça uma busca:</h4>
        </section>
        <jsp:include page='<%= "formBusca.jsp"%>' />
    </div>

    <div class="divider"></div>

    <div clas="row">

        <section class="welcomeUser">
            <h4>Resultados de pessoa:</h4>
        </section>

        <jsp:include page='<%= "usuarioTabela.jsp"%>' />
    </div>

</section>





