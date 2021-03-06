package ManagerFiles;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by server-pc on 31/03/14.
 */
public class HttpFileManager {
	ServerSocket server = null;
	Socket currentConnexion;
	HandlerFiles handler = new HandlerFiles();
	int port = 9876;
	
	public HttpFileManager() {
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
	                handler.execute(null, new ResponseFiles(currentConnexion, null));
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
