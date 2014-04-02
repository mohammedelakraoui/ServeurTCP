import com.fasterxml.jackson.databind.ObjectMapper;
import http.conf.Config;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleProxyServer {
    public static void main(String[] args) throws IOException {
        try {
            final  String path = new File("").getAbsolutePath() + File.separator+"config.js";
            File configFile=new File(path);
            ObjectMapper objectMapper = new ObjectMapper();
            Config config = objectMapper.readValue(configFile, Config.class);
            String host ="un-site.org";
            int remoteport = config.port;
            int localport = config.proxyport;
            //System.out.println(host);
            // Print a start-up message
           System.out.println("Starting proxy ... Real server=" + remoteport + " on Proxy= " + localport);
            // And start running the server
            runServer(host, remoteport, localport); // never returns
        } catch (Exception e) {
            System.err.println(e);
        }
    }
// Get host
    private static  String GetHost(InputStream input) throws IOException {
        String Host="";
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String ligne="";
                int i=1;
                 while ((ligne=reader.readLine())!=null)
                     {
                         if(i==2){
                             Host=ligne.split(":")[1].trim();
                         break;
                         }
                         i++;
                    }
       // reader.close();
        return Host;
    }

    /**
     * runs a single-threaded proxy server on
     * the specified local port. It never returns.
     */
    public static void runServer(String host,int remoteport, int localport)
            throws IOException {
        // Create a ServerSocket to listen for connections with
        ServerSocket ss = new ServerSocket(localport);
      //  String host="un-site.org";
        final byte[] request = new byte[1024];
        byte[] reply = new byte[4096];

        while (true) {
            Socket client = null, server = null;
            try {
                // Wait for a connection on the local port
                client = ss.accept();
                InputStream ForMyHost=client.getInputStream();
                final InputStream streamFromClient =client.getInputStream();
                final OutputStream streamToClient = client.getOutputStream();
               // host=GetHost(ForMyHost);
             //   System.out.println(host);
                // host="un-site.org";
                // Make a connection to the real server.
                // If we cannot connect to the server, send an error to the
                // client, disconnect, and continue waiting for connections.


                try {

                    server = new Socket(host, remoteport);
                } catch (IOException e) {
                    PrintWriter out = new PrintWriter(streamToClient);
                    out.print("Proxy server cannot connect to " + host + ":"
                            + remoteport + ":\n" + e + "\n");
                    out.flush();
                    client.close();
                    continue;
                }

                // Get server streams.
                final InputStream streamFromServer = server.getInputStream();
                final OutputStream streamToServer = server.getOutputStream();

                // a thread to read the client's requests and pass them
                // to the server. A separate thread for asynchronous.
                Thread t = new Thread() {
                    public void run() {
                        int bytesRead;
                        try {
                            while ((bytesRead = streamFromClient.read(request)) != -1) {
                                streamToServer.write(request, 0, bytesRead);
                                streamToServer.flush();
                            }
                        } catch (IOException e) {
                            System.out.println(e.toString());
                        }

                        // the client closed the connection to us, so close our
                        // connection to the server.
                        try {
                            streamToServer.close();
                        } catch (IOException e) {
                        }
                    }
                };

                // Start the client-to-server request thread running
                t.start();

                // Read the server's responses
                // and pass them back to the client.
                int bytesRead;
                try {
                    while ((bytesRead = streamFromServer.read(reply)) != -1) {
                        streamToClient.write(reply, 0, bytesRead);
                        streamToClient.flush();
                    }
                } catch (IOException e) {
                }

                // The server closed its connection to us, so we close our
                // connection to our client.
                streamToClient.close();
            } catch (IOException e) {
                System.err.println(e);
            } finally {

                try {
                    if (server != null)
                        server.close();
                    if (client != null)
                        client.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
