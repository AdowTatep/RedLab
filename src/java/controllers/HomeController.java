/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
        
        RequestDispatcher rd = request.getRequestDispatcher("_layout.jsp");
        
        //Pega qual página quer ser acessada, e o caminho customizado
        String pagina = geraPagina(request.getParameter("page"));
        String caminho = geraCaminho(pagina);
        
        //Pega o titulo passado
        //Se não passou nada é nulo
        //Se é nulo usa um padrão
        String titulo = request.getParameter("titulo");
        titulo =  (titulo != null) ?  request.getParameter("titulo") : "RedLab Laboratório" ;        
        
        if( pagina != null && pagina.equals("login"))
            rd=request.getRequestDispatcher("/sistema");
            
        //Coloca o título padrão
        request.setAttribute("titulo", titulo);
        request.setAttribute("page", pagina);
        request.setAttribute("path", caminho);
        
        //No final, redireciona
        rd.forward(request, response);        
    }
    
    private String geraPagina(String pageParam){
        //Se não tiver nada, quer dizer que ele quer a página inicial, se tiver, ele coloca o nome da página
        //Para buscar no caminho e então carregar esta página diferente
        return (pageParam==null || pageParam.equals("")) ? "home" : pageParam ;
    }
    
    private String geraCaminho(String pageParam){
        //Se tiver uma pagina no parametro, ele preenche o caminho com a pasta layout
        //+ a pasta que no padrao criado tem o mesmo nome da pagina
        //se for nulo, ele quer usar o caminho default contendo só layout
        return (pageParam==null || pageParam.equals("") || pageParam.equals("home")) ? "layout/" : "layout/"+pageParam+"/" ;
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
