/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HomeController", urlPatterns = {"/home"})
public class HomeController extends HttpServlet {
   
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        //Cria a lista de mensagens
        ArrayList<Mensagem> mensagens = ((ArrayList<Mensagem>)request.getAttribute("erros")==null) ? new ArrayList<>() : (ArrayList<Mensagem>)request.getAttribute("msgs") ;
        
        RequestDispatcher rd = request.getRequestDispatcher("_layout.jsp");
        
        //Pega qual página quer ser acessada, e gera o caminho customizado
        Helpers help = new Helpers();
        
        //Testa se a página veio como atributo, se sim, NÃO é o primeiro load
        //Então preenche com a página passada como atributo. Do contrário
        String pagina = help.geraPagina(((String)request.getAttribute("page") == null)? request.getParameter("page") : (String)request.getAttribute("page"));
        String caminho = help.geraCaminho(pagina);
        
        //Pega o titulo passado
        //Se não passou nada é nulo
        //Se é nulo usa um padrão
        String titulo = request.getParameter("titulo");
        titulo =  (titulo != null) ?  request.getParameter("titulo") : "RedLab Laboratório" ;        
        
        //Verifica se quer fazer o login
        //Se o login não for realizado, pagina é home então retorna a inicial
        //Se for realizado pagina é admin ou usario
        if (pagina.equals("login"))
            rd=request.getRequestDispatcher("/login");
            
        //Coloca o título padrão
        request.setAttribute("titulo", titulo);
        request.setAttribute("page", pagina);
        request.setAttribute("path", caminho);
        request.setAttribute("msgs", mensagens);
                
        //No final, redireciona
        rd.forward(request, response);        
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
