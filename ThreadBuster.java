public class ThreadBuster {

	public static void main(String[] args) {
		boolean stop = false;
		while (!stop) {
		Thread t = new Thread(() -> makeObject());
		t.start();
		}

	}

	public static void makeObject() {
		boolean stop = false;
		while (!stop) {
		
		}
	}

}