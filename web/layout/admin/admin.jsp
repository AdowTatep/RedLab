<%@page import="model.Usuario"%>
<%
    
    Usuario user = (Usuario)request.getSession().getAttribute("usuario");

%>

<section class="container row">
    <section class="welcomeUser">
        <h4>Bem vindo, <%= user.getPessoa().getNome() %>.</h4>
        <p>Essas são as informações mais recentes do sistema. Clique no <strong>botão inferior esquerdo para uma nova ação</strong>, ou altere as pesquisas da tabela</p>
    </section>
    <jsp:include page='<%= "tabelaTodosExames.jsp" %>' />
    
    
    <jsp:include page='<%= "menuFuncoes.jsp" %>' />
    
</section>