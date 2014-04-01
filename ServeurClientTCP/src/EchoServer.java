/**
 * Created by server-pc on 31/03/14.
 */

import java.io.*;
import java.net.ServerSocket;
import java.net.*;

public class EchoServer
{
    public static void main (String args[]) {
/** default port */

        int port = 1234;
        ServerSocket server = null;
        Socket currentConnexion;
        InputStream socketInputStream;
        OutputStream socketOuputStream;
        try {
            server = new ServerSocket(port);
        }
        catch (IOException ex)
        {
            // For any reason, impossible to create the socket on the required port.
            System.err.println("Impossible de créer un socket serveur sur ce port : "+ex);

            try { // trying an anonymous one.
                server = new ServerSocket(0);
            }
            catch (IOException ex2) { // Impossible to connect!
                System.err.println("Impossible de créer un socket serveur : "+ex2);
            }
        }

        if (null != server ) {
            try {
                System.out.println("En attente de connexion sur le port : "+server.getLocalPort());
                while (true) {
                    currentConnexion = server.accept();
                    System.out.println("Nouvelle connexion : "+currentConnexion);
                    socketInputStream = currentConnexion.getInputStream();
                    socketOuputStream = currentConnexion.getOutputStream();

                    try {
                        int b = 0;
                        while (-1 != (b= socketInputStream.read()))
                            socketOuputStream.write(b);
                        System.out.println("Fin de connexion");
                    }
                    catch (IOException ex) { // end of connection.
                        System.err.println("Fin de connexion : "+ex);
                    }
                    currentConnexion.close();
                }
            }
            catch (Exception ex) {
                // Error of connection
                System.err.println("Une erreur est survenue : "+ex);
                ex.printStackTrace();
            }
        }



    }
}
