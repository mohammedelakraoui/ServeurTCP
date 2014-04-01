package Test;

import java.io.IOException;
import http.Interfaces.*;

public class MyHandlerTest implements IHttpHandler {


    @Override
    public void execute(IRequestHttpHandler request, IResponseHttpHandler response) throws IOException {
        response.getWriter().write("Hello World\r\n");
		response.getWriter().write(request.getMethod()+"\r\n");
		response.getWriter().write(request.getHttpVersion()+"\r\n");
		response.getWriter().write(request.getHostname()+"\r\n");
        response.flush();
    }


}
