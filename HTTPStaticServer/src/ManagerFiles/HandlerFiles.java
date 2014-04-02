package ManagerFiles;

import com.sun.org.apache.xpath.internal.SourceTree;
import http.Interfaces.IHttpHandler;
import http.Interfaces.IRequestHttpHandler;
import http.Interfaces.IResponseHttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;
import java.util.Date;

import org.omg.CORBA.Request;

/**
 * Created by server-pc on 31/03/14.
 */
public class HandlerFiles implements IHttpHandler{
	String css = "a.icon {text-decoration: none;} a.icon:hover {text-decoration: underline;}";	
    @Override
    public void execute(IRequestHttpHandler request, IResponseHttpHandler response) throws IOException {
    	String Path = new File("").getAbsolutePath() + File.separator + "www";
    	String html = "";
    	File file = new File(request.getRealPath(request.getUrl()));
    	if(file.isFile()) {
    		
    	}
    	else if(file.isDirectory()) {
        	html += "<html><head><meta charset=\"utf-8\"><style></style></head><body><h1>Index of "+request.getUrl()+"</h1><table><tr><th>Nom</th><th>Taille</th><th>Date de modification</th></tr>";
        	html += "<tr><td><a class=\"icon up\" href=\"http://"+request.getHostname()+":"+request.getPort()+request.getUrl().substring(0, request.getUrl().lastIndexOf("/"))+"\">Parent directory</a></td><td></td><td></td></tr>";
        	html += ListeFilesAndFolders(file, request, response);
        	html += "</table></body></html>";
    	}
    	response.getWriter().write(html);
    	response.flush();
    }

//    private String listFilesForFolder(String Path) {
//        String html="<html><h2>Server Root-->"+Path+"</h2><body><ul>";
//        File directory = new File(Path);
//        File[] fList = directory.listFiles();
//        for (File file : fList){
//            html+="<li><h3><a href="+Path+"\\"+file.getName()+"/>"+file.getName()+"</h3></li>";
//        }
//        html+="</ul></body></html>";
//        return html;
//    }
    
    private String ListeFilesAndFolders(File file, IRequestHttpHandler request, IResponseHttpHandler response) throws IOException {
        String html = "";
        File[] children = file.listFiles();
        if(children!=null)
	        for (File child : children)
	        {
	        	Date date = new Date(child.lastModified());
	        	String link = "<a class=\"icon dir\" href=\"http://"+request.getHostname()+":"+request.getPort()+request.getUrl()+child.getName()+"\">"+child.getName()+"</a>";
	        	if(child.isDirectory())
	        		html += "<tr><td>"+link+"</td><td></td><td>"+date+"</td></tr>";
	        	else
	        		html += "<tr><td>"+link+"</td><td>"+getReadableByte(child.length(), false)+"</td><td>"+date+"</td></tr>";
	        }
        return html;
    }
    
    private static String getReadableByte(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) 
        	return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
}