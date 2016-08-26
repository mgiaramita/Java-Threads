/*
  Spawns 4 threads. Each one has 5 'bits' to add to the counter.
  Every time the counter is increased the value is printed to the console.
  When a thread runs out of bits its execution stops. The final count
  printed will be => (# Threads)*(5 bits per thread)
*/

class TestThread implements Runnable{
	private int id, bits;

	static Object lock = new Object();
	static int counter = 0;

	public TestThread(int id){
		this.id = id;
		bits = 5;
	}
	public void run(){
		System.out.println("Thread [" + id + "] created");
		
		while(bits > 0){
			incrementcounter();
			bits--;
		}
	}
	public void incrementcounter(){
		synchronized(lock){
			counter++;
			System.out.println(counter);
		}
	}
}

class ThreadTesting{
	private static final int NUM_THREADS = 4;

	public static void main(String args[]){
		Thread[] t_array = new Thread [NUM_THREADS];

		for(int i = 0; i < NUM_THREADS; i++){
			t_array[i] = new Thread(new TestThread(i));
			t_array[i].start();
		}
	}
}
