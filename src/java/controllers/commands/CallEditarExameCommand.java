/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.commands;

import controllers.Helpers;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Exame;
import model.Pessoa;
import model.dao.ExameJpaController;
import model.dao.PessoaJpaController;

/**
 *
 * @author Diego
 */
public class CallEditarExameCommand implements CommandApp {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("/control");
        
        //Cria uma conexao
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RedLabPU");
        
        String loginAntigo = request.getParameter("pessoaAntiga");
        String descricaoAntiga = request.getParameter("descricaoAntiga");
        String custoAntigo = request.getParameter("custoAntigo");
        Pessoa pessoaAntiga = new PessoaJpaController(emf).findPessoaFromUsuarioByLogin(loginAntigo);
        Exame exame = new ExameJpaController(emf). findExameByDescCustoUser(descricaoAntiga, pessoaAntiga, custoAntigo);
        
        String loginPessoa = request.getParameter("pessoa");
        String descricaoExame = request.getParameter("descricao");
        String custo = request.getParameter("custo");
        String entregue = request.getParameter("entregue") == null ? "" : request.getParameter("entregue");
        String jejum = request.getParameter("jejum");
        String dataEntrega = request.getParameter("dataEntrega");
        String horaEntrega = request.getParameter("horaEntrega");
        
        exame.setCusto(Double.parseDouble(custo));
        exame.setDataEntrega(dataEntrega);
        exame.setDescricao(descricaoExame);
        exame.setIsEntregue(entregue.equals("on")?true:false);
        exame.setHoraEntrega(horaEntrega);
        exame.setTempoJejum(jejum);
        
        Pessoa pessoaNova = new PessoaJpaController(emf).findPessoaFromUsuarioByLogin(loginPessoa);
        exame.setLoginPessoa(pessoaNova);
        
        new ExameJpaController(emf).edit(exame);

        String pagina = "searchExames";
        String caminho = new Helpers().geraCaminho(pagina);
        
        request.setAttribute("page", pagina);
        request.setAttribute("path", caminho);
        
        rd.forward(request, response);
    }
}
