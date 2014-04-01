package Test;

import java.io.IOException;
import http.Interfaces.*;

public class MyHandlerTest implements IHttpHandler {


    @Override
    public void execute(IRequestHttpHandler request, IResponseHttpHandler response) throws IOException {
        response.getWriter().write("Hello World");
        response.flush();
    }


}
