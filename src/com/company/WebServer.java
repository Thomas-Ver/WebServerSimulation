package com.company;

import java.util.ArrayList;
import java.util.List;

import com.company.AbstractRequestHandler;

public class WebServer implements WebRequestObservable{
    public AbstractRequestHandler firstHandler;
    private List<WebRequestObserver> ListObserver = new ArrayList<WebRequestObserver>();


    public WebServer( AbstractRequestHandler Handler){
        this.firstHandler = Handler;
    }

    public String getRequest(WebRequest request) {
        // TODO
        // On envoie le message dans le premier Handler
        firstHandler.handleRequest(request);

        //on notifie les observers
        notifyObservers(request);
        return "";
    }
    
    @Override
    public void attach(WebRequestObserver observer){
        ListObserver.add(observer);
    }

    @Override
    public void detach(WebRequestObserver observer){
        ListObserver.remove(observer);
    }

    // on notifie les observers que il y a une WebRequest (ainsi que son contenue)
    @Override
    public void notifyObservers(WebRequest webRequest){
    for(WebRequestObserver observer : this.ListObserver){
        observer.update(webRequest);
    }
    }

}