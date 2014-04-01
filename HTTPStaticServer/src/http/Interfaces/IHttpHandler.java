package http.Interfaces;

import java.io.IOException;

/**
 * Created by server-pc on 31/03/14.
 */
public interface IHttpHandler {
    void execute(IRequestHttpHandler request, IResponseHttpHandler response) throws IOException;
}