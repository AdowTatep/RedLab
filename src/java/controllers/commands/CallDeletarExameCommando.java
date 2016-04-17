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
public class CallDeletarExameCommando implements CommandApp {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("/control");
        
        //Cria uma conexao
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RedLabPU");
        
        String login = request.getParameter("usuario");
        String descricao = request.getParameter("descricao");
        String custo = request.getParameter("custo");
        Pessoa pessoa = new PessoaJpaController(emf).findPessoaFromUsuarioByLogin(login);
        Exame exame = new ExameJpaController(emf). findExameByDescCustoUser(descricao, pessoa, custo);
        
        new ExameJpaController(emf).destroy(exame.getId());

        String pagina = "searchExames";
        String caminho = new Helpers().geraCaminho(pagina);
        
        request.setAttribute("page", pagina);
        request.setAttribute("path", caminho);
        
        rd.forward(request, response);
    }
    
}
