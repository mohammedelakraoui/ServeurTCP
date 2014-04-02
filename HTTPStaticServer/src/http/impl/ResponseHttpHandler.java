package http.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

import http.Interfaces.IResponseHttpHandler;

public class ResponseHttpHandler implements IResponseHttpHandler {
    private Writer writer;
	private Socket socket;
	private RequestHttpHandler request;
	
	public ResponseHttpHandler(Socket s, RequestHttpHandler r) throws IOException {
        this.socket = s;
        this.request = r;
        writer = new OutputStreamWriter(this.socket.getOutputStream());
	}

	@Override
	public void flush() {
        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@Override
	public Writer getWriter() {
        return writer;
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
		// TODO Auto-generated method stub

	}

	@Override
	public void setContentType(String contentType) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addCookie(String name, String value, int duration, int path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void puturl(String URL) throws IOException {
	}
}
