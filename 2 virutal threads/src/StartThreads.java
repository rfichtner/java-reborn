import java.nio.file.Files;
import java.nio.file.Path;

public class StartThreads {

	public static void doSomeIO(int index) {
		try {
			System.out.println(index + " entering " + Thread.currentThread());

			var numberOfLines = Files.lines(Path.of("StartThreads.java")).count();

			Thread.sleep(5_000);

			System.out.println(index + " exiting " + Thread.currentThread() + " lines: " + numberOfLines);
//			throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println("press enter to start");
		System.in.read();

		for (var i = 0; i < 1_000; i++) {
			var index = i;
			new Thread(() -> doSomeIO(index)).start();
//			Thread.startVirtualThread(() -> doSomeIO(index));
			Thread.sleep(10);
		}

		try {
			Thread.sleep(3_000);
		} catch (Exception e) {
		}
		System.out.println("done " + Thread.currentThread());

	}

}
