package fr.esgi.project.Thread;

import java.util.ArrayList;
import java.util.List;

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ThreadPool pool = new ThreadPool();
//		Thread thread = new MyThread();
//		thread.start();
		
		Runnable r = new Grabber("http://www.oracle.com/index.html");
		Thread t = new Thread(r); // cr�ation
		t.start(); // start
		
		pool.add(r);
	}

}
