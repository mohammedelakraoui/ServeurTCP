package ManagerFiles;

import http.Interfaces.IRequestHttpHandler;
import http.Interfaces.IResponseHttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

/**
 * Created by server-pc on 31/03/14.
 */
public class ResponseFiles implements IResponseHttpHandler {
    Writer writer;
    String Host="www.google.com";

    public ResponseFiles(Socket s, IRequestHttpHandler r) throws IOException {
        writer = new OutputStreamWriter(s.getOutputStream());
    }

    public void flush() {
        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Writer getWriter() {
        return writer;
    }

    public OutputStream getOutputStream() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void addHeader(String key, String value) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setContentType(String contentType) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void addCookie(String name, String value, int duration, int path) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void puturl(String URL) throws IOException {
        URL url = new URL(URL);
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setRequestMethod("PUT");
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write("Resource content");
        out.close();
        httpCon.getInputStream();
    }
}