package fr.esgi.project.Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class ThreadPool {
	PriorityQueue<Runnable> queue = new PriorityQueue<>();
	List<Grabber> workers;
	Object lock = new Object();
	String startUrl;
	
	public ThreadPool(String url){
		workers = new ArrayList<Grabber>();
		this.startUrl = url;
		for (Grabber g : workers){
			g = new Grabber(this, this.startUrl);
			g.start();
		}
	}
	
	public void addJob(Runnable job) {
		synchronized (lock) {
			queue.add(job);
			lock.notify();
		}
	} 
	Runnable getJob() throws InterruptedException {
		synchronized (lock) {
			if (0 == queue.size())
				lock.wait();
			return queue.poll();
		}
	}

}
