package com.company;

public abstract class AbstractRequestHandler {
    protected AbstractRequestHandler successor;

    public AbstractRequestHandler setSucceessor(AbstractRequestHandler successor){
        this.successor = successor;
        return this.successor;
    }


    public void handleRequest(WebRequest request) {
        if (this.successor != null) {
            this.successor.handleRequest(request);
        }
    }

}
