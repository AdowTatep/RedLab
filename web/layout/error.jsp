<%@page import="controllers.Mensagem"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<Mensagem> mensagens = (ArrayList<Mensagem>) request.getAttribute("msgs");
%>

<div class="container">
    <h1>Me desculpe <i class="material-icons medium">sentiment_dissatisfied</i></h1>
    <h3>    Houve um erro</h3>
    <p>Detalhes: <br>
        <br>

        <%
            if (mensagens != null) {
                for (Mensagem msg : mensagens) {
                    if (msg.getContexto().equals("geral")) {
                        %>
                            <%= msg.getMensagem()%>
                        <%
                    }
                }
            }
        %>

    </p>
</div>