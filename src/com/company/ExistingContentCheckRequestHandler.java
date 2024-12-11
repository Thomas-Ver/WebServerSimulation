package com.company;

import java.util.List;
import java.util.ArrayList;

import com.company.AbstractRequestHandler;
import com.company.WebRequest;

public class ExistingContentCheckRequestHandler extends AbstractRequestHandler{
    
    @Override
    public void handleRequest(WebRequest request){
        List<String> ListPathAuthorize = new ArrayList<>();
        ListPathAuthorize.add("/home");
        ListPathAuthorize.add("/dashboard");
        // Si le chemin n'est pas connue on arrete la chaine et on print le message d'erreur
        if (!ListPathAuthorize.contains(request.getPath())){
            System.out.println("Status 404 : Page missing");
        }
        //sinon on fait appelle a la def de handleRequest dans la class Abstraite qui va alors appeller HandelRequest du successor que l'on a choisis s'il y en a un
        else{
            super.handleRequest(request);
        }
    }
}
