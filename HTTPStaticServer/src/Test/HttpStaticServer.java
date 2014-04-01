package Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: Server-pc
 * Date: 31/03/14
 * Time: 14:07
 * To change this template use File | Settings | File Templates.
 */
public class HttpStaticServer {
    ServerSocket server = null;
    Socket currentConnexion;
    MyHandlerTest handler = new MyHandlerTest();
    int port = 1234;
    public static void main (String args[]) throws IOException {
        new HttpStaticServer().run();
    }


    public HttpStaticServer() {
        try {
            server = new ServerSocket(port);
        } catch (IOException ex) {
            System.err.println("Impossible de créer un socket serveur sur ce port : " + ex);

            try { // trying an anonymous one.
                server = new ServerSocket(0);
            } catch (IOException ex2) { // Impossible to connect!
                System.err.println("Impossible de créer un socket serveur : " + ex2);
            }
        }

    }

    public void run() {
        if (null == server)
            return;


        try {
            System.out.println("En attente de connexion sur le port : " + server.getLocalPort());
            while (true) {
                currentConnexion = server.accept();
                System.out.println("Nouvelle connexion : " + currentConnexion);
                try {
                    handler.execute(null, new SimpleResponse(currentConnexion.getOutputStream()));
                } catch (IOException ex) { // end of connection.
                    System.err.println("Fin de connexion : " + ex);
                }
                currentConnexion.close();
            }
        } catch (Exception ex) {
            // Error of connection
            System.err.println("Une erreur est survenue : " + ex);
            ex.printStackTrace();
        }
    }

}