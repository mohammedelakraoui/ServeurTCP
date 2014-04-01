package Test;

import http.Interfaces.IResponseHttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Created with IntelliJ IDEA.
 * User: Server-pc
 * Date: 31/03/14
 * Time: 14:21
 * To change this template use File | Settings | File Templates.
 */
public class SimpleResponse  implements  IResponseHttpHandler{
        Writer writer;

        public SimpleResponse(OutputStream out){
            writer = new OutputStreamWriter(out);
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
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void addHeader(String key, String value) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void setContentType(String contentType) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void addCookie(String name, String value, int duration, int path) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

    @Override
    public void puturl(String URL) throws IOException {

    }
}
