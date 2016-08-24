//import

class TestThread implements Runnable{
	private int id, bits;

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
		synchronized(this){
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