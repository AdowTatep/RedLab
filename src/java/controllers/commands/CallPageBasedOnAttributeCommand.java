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

/**
 *
 * @author adowt
 */
public class CallPageBasedOnAttributeCommand implements CommandApp{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //Gera o caminho a ser carregado o template de acordo com a página
        Helpers help = new Helpers();
        String pagina = (request.getAttribute("page")==null)?request.getParameter("page"):(String)request.getAttribute("page");
        String caminho = help.geraCaminho(pagina);
        
        RequestDispatcher rd = request.getRequestDispatcher("_layout.jsp");
        request.setAttribute("page", pagina);
        request.setAttribute("path", caminho);
        
        rd.forward(request, response);
    }
    
}
