import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by server-pc on 31/03/14.
 */

    public class EchoClient {
        public static void main (String args[]) throws IOException {
            String response;
            BufferedReader stdInputStream;
            Socket client;
            PrintStream socketInputStream;
            BufferedReader socketOutputStream;
            try {
                client = new Socket("localhost", 1234); // socket sur echo
                stdInputStream = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Tapez votre nom : ");
                response = stdInputStream.readLine();
                System.out.println("Bonjour "+response);
                System.err.println("Connecté sur : "+client);
                socketInputStream = new PrintStream(client.getOutputStream());
                socketOutputStream = new BufferedReader(new
                        InputStreamReader(client.getInputStream()));
                socketInputStream.println("Bonjour "+response);
                String serveurResponse = socketOutputStream.readLine();
                System.out.println("Reponse du serveur : "+serveurResponse);
                client.close();
            }catch (IOException ex)
            {}
        }
}
