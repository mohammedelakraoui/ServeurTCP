package http;

import http.impl.HttpStaticServer;
import ManagerFiles.HttpFileManager;

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        try {
			HttpStaticServer.getInstance().run();
//	        new HttpFileManager().run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}