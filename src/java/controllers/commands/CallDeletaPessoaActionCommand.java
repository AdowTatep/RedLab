
package controllers.commands;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pessoa;
import model.Usuario;
import model.dao.UsuarioJpaController;


public class CallDeletaPessoaActionCommand implements CommandApp{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        //Pega o login que está na busca para mostrar a mesma página de busca anterior
        String login = (request.getParameter("login") == null) ? "" : request.getParameter("login") ;
        
        
        RequestDispatcher rd  = request.getRequestDispatcher("/control?login="+login+"&senha=null");
        Usuario usuDeletar = new Usuario(request);
        Pessoa pesDeletar = new Pessoa(request, usuDeletar);
        
        usuDeletar.setPessoa(pesDeletar);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RedLabPU");
        new UsuarioJpaController(emf).deletaUsuario(usuDeletar);
        
        request.setAttribute("page", "searchPessoa");
        
        rd.forward(request, response);
        
    }
    
}
