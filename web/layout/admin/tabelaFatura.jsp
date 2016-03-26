<%@page import="java.util.List"%>
<%@page import="model.Exame"%>
<div class="col s12">
        <%
            List<Exame> exames = (List<Exame>)request.getAttribute("exames");
            
            double totalFaturado = 0.0;
        %>
        <table class="highlight bordered">
            <thead>
            <h5>Total a receber:</h5>
                <tr>
                    <th data-field="id">ID</th>
                    <th data-field="cliente">Cliente</th>
                    <th data-field="descricao">Descrição</th>
                    <th data-field="custo">Lucro</th>
                </tr>
            </thead>

            <tbody>
                <%
                    for (Exame exame : exames) {
                        //Só cobra exames que foram entregues
                        if( exame.getIsEntregue() ){
                            totalFaturado += exame.getCusto();
                            %>
                            <tr>
                                <td><%= exame.getId() %></td>
                                <td><%= exame.getLoginPessoa().getNome() %></td>
                                <td><%= exame.getDescricao() %></td>
                                <td> <span class="green-text">+ R$<%= exame.getCusto() %></span></td>
                            </tr>
                            <%                            
                            }
                    }
                %>
            </tbody>
        </table>
            <br>
            <div class="card">
                <div class="card-content">
                    <h5>Total: R$ <%= totalFaturado %></h5>
                </div>
            </div>
    </div>