package http.Interfaces;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.net.*;
/**
 * Created by server-pc on 31/03/14.
 */
public interface IResponseHttpHandler {
    void flush();
    Writer getWriter();
    OutputStream getOutputStream();
    void addHeader(String key, String value);
    void setContentType(String contentType);
    void addCookie(String name, String value, int duration, int path);
    void puturl(String URL) throws IOException;
}
