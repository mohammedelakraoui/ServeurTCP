package http.impl;

import ManagerFiles.ResponseFiles;
import com.fasterxml.jackson.databind.ObjectMapper;
import http.Interfaces.IHttpHandler;
import http.conf.Config;
import http.conf.Handler;
import http.conf.Host;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Server-pc
 * Date: 31/03/14
 * Time: 14:07
 * To change this template use File | Settings | File Templates.
 */
public class HttpStaticServer {
	private final static String CONFIG_PATH = "../config.js";
	private static HttpStaticServer instance = null;
	
	File configFile;

	HttpRequestBuilder requestBuilder;
	ServerSocket server = null;
    Socket currentConnexion;

	public Map<String, Host> hosts = new HashMap<String,Host>();
	Map<String, IHttpHandler> handlersPool = new HashMap<String,IHttpHandler>();
    
	public static HttpStaticServer getInstance() throws Exception {
		if(null == instance)
			instance = new HttpStaticServer();
		return instance;
	}
	
	private HttpStaticServer() throws Exception {
		configFile = new File(CONFIG_PATH);
		if(!configFile.exists())
			throw new FileNotFoundException();
		
		ObjectMapper objectMapper = new ObjectMapper();
		Config config = objectMapper.readValue(configFile, Config.class);

		for(Host h : config.hosts)
			hosts.put(h.name, h);
    	
		requestBuilder = new HttpRequestBuilder(this);
		try {
            server = new ServerSocket(config.port);
        }
		catch (IOException ex) {
            System.err.println("Impossible de cr�er un socket serveur sur ce port : " + ex);
            try { // trying an anonymous one.
                server = new ServerSocket(0);
            }
            catch (IOException ex2) { // Impossible to connect!
                System.err.println("Impossible de cr�er un socket serveur : " + ex2);
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
                	RequestHttpHandler request = requestBuilder.build(currentConnexion);
                	ResponseHttpHandler response = new ResponseHttpHandler(currentConnexion, request);
    				
                	if(null != request.host)
                    for (Handler handlerConfig : request.host.handlers){
    					Pattern p = Pattern.compile(handlerConfig.pattern);
    					if (p.matcher(request.getUrl()).find()){
    						IHttpHandler handler = handlersPool.get(handlerConfig.clazz);
    						if (null == handler){
    							handler = (IHttpHandler) Class.forName(handlerConfig.clazz).newInstance();
    							handlersPool.put(handlerConfig.clazz, handler);
    						}
    						System.out.println(handler.toString());
    						handler.execute(request, response);
    						break;
    					}
    				}
                } catch (Exception ex) { // end of connection.
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
