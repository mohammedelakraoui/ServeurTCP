package fr.esgi.project.Thread;

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread thread = new MyThread();
		thread.start();
		
		Runnable r = new Grabber("http://www.oracle.com/index.html");
		Thread t = new Thread(r); // cr�ation
		t.start(); // start
	}

}
