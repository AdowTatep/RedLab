package controllers;

public class Helpers {
       public String geraPagina(String pageParam){
        //Se não tiver nada, quer dizer que ele quer a página inicial, se tiver, ele coloca o nome da página
        //Para buscar no caminho e então carregar esta página diferente
        return (pageParam==null || pageParam.equals("")) ? "home" : pageParam ;
    }
    
    public String geraCaminho(String pageParam){
        //Se tiver uma pagina no parametro, ele preenche o caminho com a pasta layout
        //+ a pasta que no padrao criado tem o mesmo nome da pagina
        //se for nulo, ele quer usar o caminho default contendo só layout
        return (pageParam==null || pageParam.equals("") || pageParam.equals("home")) ? "layout/" : "layout/"+pageParam+"/" ;
    }
}
