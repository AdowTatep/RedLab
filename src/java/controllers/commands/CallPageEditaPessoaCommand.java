/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.commands;

import controllers.Helpers;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pessoa;
import model.Usuario;

/**
 *
 * @author adowt
 */
public class CallPageEditaPessoaCommand implements CommandApp {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("_layout.jsp");
        
        //Pega o usuario e seta a pessoa e passa para a página
        Usuario usuarioEditar = new Usuario(request);
        Pessoa pessoaEditar = new Pessoa(request, usuarioEditar);
        usuarioEditar.setPessoa(pessoaEditar);
        
        String pagina = "editarPessoa";
        String caminho = new Helpers().geraCaminho(pagina);
        
        
        request.setAttribute("usuario", usuarioEditar);
        request.setAttribute("page", pagina);
        request.setAttribute("path", caminho);
        
        rd.forward(request, response);
        
    }
    
}
