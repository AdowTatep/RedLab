
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
        
        
        RequestDispatcher rd  = request.getRequestDispatcher("/control?page=searchPessoa");
        Usuario usuDeletar = new Usuario(request);
        Pessoa pesDeletar = new Pessoa(request, usuDeletar);
        
        usuDeletar.setPessoa(pesDeletar);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RedLabPU");
        new UsuarioJpaController(emf).deletaUsuario(usuDeletar);
        
        rd.forward(request, response);
        
    }
    
}
