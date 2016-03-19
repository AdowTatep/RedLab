/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.commands;

import controllers.Helpers;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Exame;
import model.Usuario;
import model.dao.ExameJpaController;

/**
 *
 * @author adowt
 */
public class CallPageAdminCommand implements CommandApp {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("_layout.jsp");
        
        //Pega o usuario para trabalhar
        Usuario usuario = (Usuario)request.getAttribute("usuario");
        
        //Pega qual página quer ser acessada, e gera o caminho customizado
        Helpers help = new Helpers();
        
        String pagina = "admin";
        String caminho = help.geraCaminho(pagina);
        
        //Pega o titulo passado
        //Se não passou nada é nulo
        //Se é nulo usa um padrão
        String titulo = "Admin - "+usuario.getLogin();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RedLabPU");
        List<Exame> exames = new ExameJpaController(emf).findExameEntities();        
        
        request.setAttribute("usuario", usuario);
        request.setAttribute("page", pagina);
        request.setAttribute("titulo", titulo);
        request.setAttribute("path", caminho);
        request.setAttribute("exames", exames);        
        
        rd.forward(request, response);        
    }
    
}
