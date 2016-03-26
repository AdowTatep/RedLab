<%@page import="model.Usuario"%>
<%
    
    Usuario user = (Usuario)request.getSession().getAttribute("usuario");

%>

<section class="container row">
    <section class="welcomeUser">
        <h4>Bem vindo, <%= user.getPessoa().getNome() %>.</h4>
        <p>Confira abaixo seus exames.</p>
    </section>
        
    <jsp:include page='<%= "tabelaExamesUsuario.jsp" %>' />
    
</section>