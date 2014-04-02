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

/**
 * Created by server-pc on 31/03/14.
 */
public class HandlerFiles implements IHttpHandler{

    @Override
    public void execute(IRequestHttpHandler request, IResponseHttpHandler response) throws IOException {
    	String Path = new File("").getAbsolutePath() + File.separator + "www";
    	response.getWriter().write("<html><h2>Server Root-->"+Path+"</h2><body><ul>");
    	//response.getWriter().write("<a href='"+Path+"'>root</a>");
    	File folder = new File(Path);
    	ListeFilesAndFolders(folder,response,Path);
    	response.getWriter().write("</ul></body></html>");
    	response.flush();
    }

    public String listFilesForFolder(String Path) {
        String html="<html><h2>Server Root-->"+Path+"</h2><body><ul>";
        File directory = new File(Path);
        File[] fList = directory.listFiles();
        for (File file : fList){
            html+="<li><h3><a href="+Path+"\\"+file.getName()+"/>"+file.getName()+"</h3></li>";
        }
        html+="</ul></body></html>";
        return html;
    }
    public void ListeFilesAndFolders(File file,IResponseHttpHandler response,String root) throws IOException {
        response.getWriter().write("<li><h3><a href='"+root+"\\"+file.getName()+"'/>"+file.getName()+"</h3></li>");
        File[] children = file.listFiles();
        if(children==null)
        {
            return;
        }
        for (File child : children)
        {
            ListeFilesAndFolders(child, response,root);
        }
    }
}