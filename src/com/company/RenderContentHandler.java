package com.company;

import java.net.http.WebSocket;

import com.company.AbstractRequestHandler;
import com.company.WebRequest;

public class RenderContentHandler extends AbstractRequestHandler{

    @Override
    public void handleRequest(WebRequest request){
        if (request.getPath().equals("/dashboard")){
            System.out.println("Status 200 : Dashboard content here");
        }
        else{
            System.out.println("Status 200 : Home content here");

        }
    }
}
