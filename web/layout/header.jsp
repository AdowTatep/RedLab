<header class="">
    <nav class="red darken-4 text-white nav-wrapper">
        <section class="container">
            <a href="/RedLab" class="brand-logo">RedLab</a>
            <%
                if(request.getSession().getAttribute("usuario")!=null){
                 %>
                <ul id="nav-mobile" class="right hide-on-med-and-down">
                    <li><a href="control?page=logout">Deslogar<i class="material-icons right">highlight_off</i></a></li>
               </ul>
                 <%
                }
            %>
        </section>
    </nav>
</header>