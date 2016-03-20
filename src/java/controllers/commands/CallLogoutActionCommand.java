package controllers.commands;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CallLogoutActionCommand implements CommandApp{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        RequestDispatcher rd = request.getRequestDispatcher("/control");
        
        String pagina = "home";        
        request.setAttribute("page", pagina);
        
        request.getSession().setAttribute("usuario", null);        
        rd.forward(request, response);
    }
    
}
