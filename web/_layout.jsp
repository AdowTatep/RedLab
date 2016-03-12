<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <% 
        //Pega o titulo passado
        String titulo = request.getParameter("titulo");
        
        //Pega a pagina para tratamento de arquivos especiais
        String pagina = request.getParameter("page");
        
        //Se tiver uma pagina no parametro, ele preenche o caminho com a pasta layout
        //E a pasta daquela pagina;
        String caminho = (pagina == null || pagina.isEmpty()) ? "layout/" : "layout/"+pagina ;
        
        System.out.println(caminho);
        
        //Se não passou nada é nulo
        //Se é nulo usa um padrão
        titulo =  (titulo != null || !titulo.isEmpty()) ?  request.getParameter("titulo") : "RedLab Laboratório";         
        System.out.println(titulo);
        
        
        //Se não tiver nada, é o primeiro load, então carrega a home,
        //ao contrário, carrega a página que está no parâmetro       
        
     %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= titulo %></title>
        
        <%
            //Não há tratamento de script minified de js da framework e css pois é tudo local de qualquer forma
            //Nem melhor posicionamento do jquery para carregamento mais rapido(scripts ficam no rodapé)
            //Justamente por isso. Os css são adicionados depois dos scripts por padrão.
            //O style não está em /css pois assim caso ele queira carregar uma imagem em img ele
            //reconhece o caminho tranquilo.
        %>        
        <script src="js/jquery-2.2.1.min.js"></script>
        <script src="js/materialize.js"></script>
        <link rel="stylesheet" href="css/materialize.css"/>
        <link rel="stylesheet" href="style.css"/>
        
    </head>
    <body>
        
        <jsp:include page="<%= caminho+"header.jsp" %>" />   
        
        <jsp:include page="<%= //Aqui a pagina é adicionada ao caminho mesmo ela ja tendo sido adicionada antes
                                              //No caso, cada pagina "nome.jsp" fica dentro de uma pasta com seu mesmo nome
                                              //Para organização, então, fica "padrao/pagina/pagina.jsp" por isso a reperição
                                                caminho+pagina+".jsp" %>" />            
            
        <jsp:include page="<%= caminho+"footer.jsp" %>" />   
    </body>
</html>
