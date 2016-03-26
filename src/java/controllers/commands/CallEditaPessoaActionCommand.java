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
import model.Pessoa;
import model.Usuario;
import model.dao.UsuarioJpaController;

/**
 *
 * @author adowt
 */
public class CallEditaPessoaActionCommand implements CommandApp {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("/control?login=&senha=");
        
        //Cria usuario
        Usuario editarUsuario = new Usuario(request);
        Pessoa editarPessoa = new Pessoa(request, editarUsuario);
        
        editarUsuario.setPessoa(editarPessoa);
        
        //Edita o usuário
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RedLabPU");
        new UsuarioJpaController(emf).edit(editarUsuario);        
        
        String pagina = "searchPessoa";
        String caminho = new Helpers().geraCaminho(pagina);
        
        request.setAttribute("page", pagina);
        request.setAttribute("path", caminho);
        
        rd.forward(request, response);
    }
    
}
