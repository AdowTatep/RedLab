<%@page import="model.Usuario"%>
<%
    
    Usuario user = (Usuario)request.getSession().getAttribute("usuario");

%>

<section class="container row">
    <div class="row">
        <section class="welcomeUser">
            <h4>Bem vindo, <%= user.getPessoa().getNome() %>.</h4>
            <p>Essas s�o as informa��es mais recentes do sistema. Clique no <strong>bot�o inferior esquerdo para uma nova a��o</strong>, ou altere as pesquisas da tabela</p>
        </section>
            
            <div class="row">
                <div class="col s12 m6">
                    <jsp:include page='<%= "tabelaTodosExames.jsp" %>' />
                </div>
                
                <div class="col s12 m6">
                    <jsp:include page='<%= "tabelaFatura.jsp" %>' />
                </div>
        
            </div>
    </div>
    
</section>
    <jsp:include page='<%= "menuFuncoes.jsp" %>' />