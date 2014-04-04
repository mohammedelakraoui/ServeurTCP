import org.esgi.thread.FixedThreadPool;


public class Application {
	FixedThreadPool pool  = new FixedThreadPool(5);

	public Application() {
		
	}
	
	public static void main(String[] args) {
		Application application = new Application();
		application.pool.addJob(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("JOB1 - "+ Thread.currentThread().getId());
			}
		});
		application.pool.addJob(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("JOB2 - "+ Thread.currentThread().getId());
			}
		});
		application.pool.addJob(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("JOB3 - "+ Thread.currentThread().getId());
			}
		});
		application.pool.addJob(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("JOB4 - "+ Thread.currentThread().getId());
			}
		});
		application.pool.addJob(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("JOB5 - "+ Thread.currentThread().getId());
			}
		});
		application.pool.addJob(new Runnable() {
			@Override
			public void run() {
				System.out.println("JOB6 - "+ Thread.currentThread().getId());
				try {
					Thread.sleep(600);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
