package com.company;

/**
 *  We have a small web server that receive requests with the method getRequest();
 *  The first parameter is the path of the request, and the second is an object that represents the
 *  user that is currently logged in.
 *
 *  The only valid paths for our web app are "/dashboard" and "/home".
 *  "/dashboard" is only accessible to admin users.
 *  "/home" is accessible to any user. 
 *  The web server writes directly its response to the console using System.out.println.
 *
 *  When a non authorized user wants to access the content, the webserver must respond with the message :
 *  "Status 403 : user is not authorized to access this content"
 *
 *  When we try to access content that do not exist, the server must respons with the message :
 *  "Status 404 : Page missing".
 *
 *  Otherwise the server respond with the asked content which is :
 *  "/dashboard" => "Status 200 : Dashboard content here"
 *  "/home" => "Status 200 : Home content here"
 *
 *  We also want to have a file system log of all request that were made.
 */

public class Main {

    public static void main(String[] args) {
        FileLogger fileLogger = new FileLogger("logs.txt");

        User regularUser = new User(false);
        User adminUser = new User(true);

        // on crée nos instances Handler
        AbstractRequestHandler hExistingPage = new ExistingContentCheckRequestHandler();
        AbstractRequestHandler hPolicy = new PolicyCheckRequestHandler();
        AbstractRequestHandler hDisplay = new RenderContentHandler();

        // on définit les successors
        hExistingPage.setSucceessor(hPolicy);
        hPolicy.setSucceessor(hDisplay);
        
        //le WebServer doit prendre le premier Handler pour se créer et ensuite traiter les WebRequest
        WebServer webServer = new WebServer(hExistingPage);
        
        //on ajoute fileLogger comme observer dans notre listes des Observers
        webServer.attach(fileLogger);


        /**
         * Expected output :
         * Status 403 : user is not authorized to access this content
         */
        webServer.getRequest(new WebRequest("/dashboard", regularUser));

        /**
         * Expected output :
         * Status 404 : Page missing
         */
        webServer.getRequest(new WebRequest("/dashboard/nonExistingPage", adminUser));

        /**
         * Expected output :
         * Status 200 : Dashboard content here
         */
        webServer.getRequest(new WebRequest("/dashboard", adminUser));

        /**
         * Expected output :
         * Status 200 : Home content here
         */
        webServer.getRequest(new WebRequest("/home", regularUser));
    }
}
