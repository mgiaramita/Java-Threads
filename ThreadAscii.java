class AlphabetThread implements Runnable{
	//Bounds for ascii characters to be printed
	private static final int MIN_ASCII_VALUE = 65;
	private static final int MAX_ASCII_VALUE = 90;

	private int id;

	static Object lock = new Object();
	static int charCounter = MIN_ASCII_VALUE;

	public AlphabetThread(int id){
		this.id = id;
	}

	public void run(){
		while(charCounter <= MAX_ASCII_VALUE){
			printNextChar();
		}
	}

	public void printNextChar(){
		synchronized(lock){
			//Secondary check needs to be done. At end of execution threads currenly
			//waiting for the lock will eventually get their chance to execute.
			//This can cause characters outside of the MIN/MAX range to be printed.
			if(charCounter <= MAX_ASCII_VALUE){
				System.out.println("[" + id + "]: " + (char)charCounter);
				charCounter++;
			}
		}
	}
}

class ThreadAscii{
	private static final int NUM_THREADS = 4;

	public static void main(String args[]){
		Thread[] t_array = new Thread [NUM_THREADS];

		for(int id = 0; id < NUM_THREADS; id++){
			t_array[id] = new Thread(new AlphabetThread(id));
			t_array[id].start();
		}
	}
}
