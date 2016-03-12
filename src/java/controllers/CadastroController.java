/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pessoa;
import model.Usuario;
import model.dao.PessoaJpaController;
import model.dao.UsuarioJpaController;

/**
 *
 * @author adowt
 */
@WebServlet(name = "CadastroController", urlPatterns = {"/cadastro"})
public class CadastroController extends HttpServlet {

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
        try {
            response.setContentType("text/html;charset=UTF-8");
            
            //Pega os parametros passados
            Usuario usuario = new Usuario();
            usuario.setLogin(request.getParameter("login"));
            usuario.setSenha(request.getParameter("senha"));
            usuario.setIsAdmin(Boolean.FALSE);
            
            Pessoa pessoa = new Pessoa(usuario); //Passa usuario para criar pessoa com login
            pessoa.setNome(request.getParameter("nome"));
            pessoa.setTelefone(request.getParameter("telefone"));
            String sex=request.getParameter("sexo");
            pessoa.setSexo(request.getParameter("sexo").charAt(0));
            pessoa.setCpf(request.getParameter("cpf"));
            pessoa.setEndereco(request.getParameter("endereco"));
            
            //Cria uma conexao
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("RedLabPU");
            //Tenta inserir no banco
            new UsuarioJpaController(emf).create(usuario);
            new PessoaJpaController(emf).create(pessoa);
            
            //Retorna para o login(não passou parametro então é login)
            RequestDispatcher rd = request.getRequestDispatcher("_layout.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CadastroController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
