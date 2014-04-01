package Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;

import http.Interfaces.ICookie;
import http.Interfaces.IRequestHttpHandler;

public class RequestHttpHandler implements IRequestHttpHandler {
	private Socket socket;
	private String rawRequest = "";
	private HashMap<String, String> httpHeaders;

	private String method;
	private String uri;
	private String httpVersion;
	
	public RequestHttpHandler(Socket s) {
		this.socket = s;
        try {
//            System.out.println(this.socket.getInetAddress().getCanonicalHostName());
//			System.out.println(this.socket.getReceiveBufferSize());
	        BufferedReader br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
	        int c;
	        while(((c = br.read()) != -1) && !this.rawRequest.endsWith("\r\n\r"))
	        	this.rawRequest += (char)c;
	        this.rawRequest = this.rawRequest.substring(0, this.rawRequest.length()-4);
//	        this.rawRequest = new String(buffer);
//	        System.out.println(buffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(null != this.rawRequest)
        {
        	this.httpHeaders = new HashMap<>();
    		String[] headers = this.rawRequest.split("\r\n");
    		String[] tmp = headers[0].split(" ");
    		this.method = tmp[0];
    		this.uri = tmp[1];
    		this.httpVersion = tmp[2];
    		
        	for(int i = 1; i < headers.length; i++)
        	{
        		int index = headers[i].indexOf(":");
    			this.httpHeaders.put(headers[i].substring(0, index), headers[i].substring(index+1).trim());
        	}
        }
	}

	@Override
	public String[] getParameterNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getParameter(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ICookie getCookies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMethod() {
		return this.method;
	}

	@Override
	public String getHttpVersion() {
		return this.httpVersion;
	}

	@Override
	public String[] getHeaderNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHeader(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRealPath(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHostname() {
		return this.httpHeaders.get("Host");
	}

	@Override
	public String getRemoteAddress() {
		// TODO Auto-generated method stub
		return null;
	}
}