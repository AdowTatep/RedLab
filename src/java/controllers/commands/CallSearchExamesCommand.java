/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.commands;

import controllers.Helpers;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Exame;
import model.dao.ExameJpaController;

/**
 *
 * @author Diego
 */
public class CallSearchExamesCommand implements CommandApp {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String descricaoExame = request.getParameter("descricaoExame");
        
        RequestDispatcher rd = request.getRequestDispatcher("_layout.jsp");
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RedLabPU");        
        
        List<Exame> listExames = new ArrayList<Exame>();
        
        if(descricaoExame == null || descricaoExame.equals("")){
            listExames = new ExameJpaController(emf).findExameEntities();
        }else{
            listExames = new ExameJpaController(emf).findExameByDescricao(descricaoExame);
        }
        
        String pagina = "buscaExames";
        //Gera o caminho correto do mvc baseado na pagina
        String caminho = new Helpers().geraCaminho(pagina);
        
        //Passa a tela de usuarios
        request.setAttribute("page", pagina);
        request.setAttribute("path", caminho);
        request.setAttribute("listExames", listExames);
        rd.forward(request, response);
    }
}
