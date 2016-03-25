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
import model.dao.PessoaJpaController;
import model.dao.UsuarioJpaController;

/**
 *
 * @author adowt
 */
public class CallPageAdminCommand implements CommandApp {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("_layout.jsp");
        
        //Pega o usuario para trabalhar
        Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
        
        //Pega qual página quer ser acessada, e gera o caminho customizado
        Helpers help = new Helpers();
        
        String pagina = "admin";
        String caminho = help.geraCaminho(pagina);
                
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RedLabPU");
        List<Exame> exames = new ExameJpaController(emf).findExameEntities();        
        List<Usuario> usuarios = new UsuarioJpaController(emf).findUsuarioEntities();
        
        for(Usuario usu : usuarios){
            usu.setPessoa(new PessoaJpaController(emf).findPessoaFromUsuario(usu));
        }
        
        request.setAttribute("users", usuarios);
        request.setAttribute("page", pagina);
        request.setAttribute("path", caminho);
        request.setAttribute("exames", exames);        
        
        rd.forward(request, response);        
    }
    
}
