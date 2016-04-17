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
import model.Pessoa;
import model.Usuario;
import model.dao.ExameJpaController;
import model.dao.PessoaJpaController;
import model.dao.UsuarioJpaController;

/**
 *
 * @author Diego
 */
public class CallPageEditarExameCommand implements CommandApp{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("_layout.jsp");
        
        String descricaoExame = request.getParameter("descricao");
        String loginUser = request.getParameter("usuario");
        String custo = request.getParameter("custo");
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RedLabPU");
        Pessoa p = new PessoaJpaController(emf).findPessoaFromUsuarioByLogin(loginUser);
        
        Exame exame = new ExameJpaController(emf).findExameByDescCustoUser(descricaoExame, p, custo);
        
        List<Usuario> usuario = new UsuarioJpaController(emf).findUsuarioEntities();
        
        String pagina = "editarExame";
        String caminho = new Helpers().geraCaminho(pagina);
        
        request.setAttribute("usuario", usuario);
        request.setAttribute("exame", exame);
        request.setAttribute("page", pagina);
        request.setAttribute("path", caminho);
        
        rd.forward(request, response);
    }
}
