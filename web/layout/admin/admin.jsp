<%@page import="model.Usuario"%>
<%
    
    Usuario user = (Usuario)request.getSession().getAttribute("usuario");

%>

<section class="container row">
    <section class="welcomeUser">
        <h4>Bem vindo, <%= user.getPessoa().getNome() %>.</h4>
        <p>Essas s�o as informa��es mais recentes do sistema. Clique no <strong>bot�o inferior esquerdo para uma nova a��o</strong>, ou altere as pesquisas da tabela</p>
    </section>
    <jsp:include page='<%= "tabelaTodosExames.jsp" %>' />
    
    
    <jsp:include page='<%= "menuFuncoes.jsp" %>' />
    
</section>