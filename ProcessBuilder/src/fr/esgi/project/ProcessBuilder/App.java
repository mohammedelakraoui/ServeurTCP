package fr.esgi.project.ProcessBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File dest = new File("C:\\Users\\Ernsso\\Documents\\ESGI\\5eme_annee\\ArchiDistrib\\Proxy.rar");
		File s1 = new File("C:\\Users\\Ernsso\\Documents\\ESGI\\5eme_annee\\ArchiDistrib\\proxy.java");
		File s2 = new File("C:\\Users\\Ernsso\\Documents\\ESGI\\5eme_annee\\ArchiDistrib\\config.js");
		
		List<File> src = new ArrayList<File>();
		src.add(s1);
		src.add(s2);
		Compress c = new Compress();
		c.compress(dest, src.toArray(new File[src.size()]));
	}

}
