<%@page import="java.util.List"%>
<%@page import="model.Exame"%>
<div class="col s12">
        <%
            List<Exame> exames = (List<Exame>)request.getAttribute("exames");
            
            
        %>
        <table class="highlight bordered">
            <thead>
                <div class="divider"></div>
                <tr>
                    <th data-field="id">ID</th>
                    <th data-field="descricao">Descrição</th>
                    <th data-field="tempoJejum">Tempo de Jejum</th>
                    <th data-field="isEntregue">Entregue</th>
                    <th data-field="dataEntrega">Data de Entrega</th>
                    <th data-field="custo">Custo</th>
                </tr>
            </thead>

            <tbody>
                <%
                    for (Exame exame : exames) {
                        %>
                        <tr>
                            <td><%= exame.getId() %></td>
                            <td><%= exame.getDescricao() %></td>
                            <td><%= (exame.getTempoJejum()== null) ? "Sem Jejum" : exame.getTempoJejum() %></td>
                            <td><%= (exame.getIsEntregue()) ? "<i class='material-icons checkGreen'>check_circle</i>" : "<i class='material-icons cancelRed'>cancel</i>" %></td>
                            <td><%= exame.getDataEntrega() %></td>
                            <td>R$<%= exame.getCusto() %></td>
                        </tr>
                        <%                            
                    }
                %>
            </tbody>
        </table>
    </div>