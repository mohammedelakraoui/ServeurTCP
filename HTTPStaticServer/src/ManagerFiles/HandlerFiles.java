package ManagerFiles;

import http.Interfaces.IHttpHandler;
import http.Interfaces.IRequestHttpHandler;
import http.Interfaces.IResponseHttpHandler;

import java.io.IOException;

/**
 * Created by server-pc on 31/03/14.
 */
public class HandlerFiles implements IHttpHandler{

    @Override
    public void execute(IRequestHttpHandler request, IResponseHttpHandler response) throws IOException {
        String HTML="<html><head><script type='text/javascript'> function URL(){window.location.href='file:///C:/';}</script>" +
              "</head> <body onload=URL()> </body> </html>";
        String a = "HTTP/1.1 404 Not Found\r\n" +
                "Content-Length: 22\r\n" +
                "Content-Type: text/html\r\n\r\n" +
                "<h1>404 Not Found</h1>";
        response.getWriter().write(HTML);

        response.flush();
    }

}
