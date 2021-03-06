package http.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;

import http.Interfaces.IResponseHttpHandler;

public class ResponseHttpHandler implements IResponseHttpHandler {
    private Writer writer;
	private Socket socket;
	private RequestHttpHandler request;
		
	public ResponseHttpHandler(Socket s, RequestHttpHandler r) throws IOException {
        this.socket = s;
        this.request = r;
        
        this.writer = new OutputStreamWriter(this.socket.getOutputStream());
//        this.writer.write("HTTP/1.1 200 OK\r\n");
	}

	@Override
	public void flush() {
        try {
            this.writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@Override
	public Writer getWriter() {
        return this.writer;
	}

	@Override
	public OutputStream getOutputStream() {
		try {
			return this.socket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addHeader(String key, String value) {
		try {
			this.writer.write(key+":"+value+"\r\n");
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	@Override
	public void setContentType(String contentType) {
		try {
			this.writer.write("Content-Type:"+contentType+"\r\n");
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	@Override
	public void addCookie(String name, String value, int duration, int path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void puturl(String URL) throws IOException {
	}
}
