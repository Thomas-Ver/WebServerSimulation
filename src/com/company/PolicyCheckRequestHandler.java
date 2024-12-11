package com.company;

import com.company.AbstractRequestHandler;
import com.company.WebRequest;

public class PolicyCheckRequestHandler extends AbstractRequestHandler{


    @Override
    public void handleRequest(WebRequest request){

        if (!request.getLoggedUser().isAdmin() && request.getPath().equals("/dashboard")){
            System.out.println("Status 403 : user is not authorized to access this content");
        }
        else{
            super.handleRequest(request);
        }
    }
}