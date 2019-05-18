package ch10.sec08;

public class ThreadIS {

	public static void main(String[] args) throws InterruptedException {

		int randomValue = (int) Math.floor(Math.random()*15);
		Runnable interruptibleTask = () -> {
			System.out.print("\nInterruptible: ");
			try {
				for (int i = 1; i <= 1000; i++) {
					System.out.print(i + " ");
					if (i > randomValue)
					throw new IllegalStateException();	
					Thread.sleep(20) ;
					
				}
			}

			catch (InterruptedException ex) {
				System.out.println("Interrupted!");
			}
		};
		Thread thread = new Thread(interruptibleTask);
		thread.setUncaughtExceptionHandler((t, ex) -> System.out.println("Yikes!"));
		thread.start();
		Thread.sleep(100);
		thread.interrupt();

	}

}
