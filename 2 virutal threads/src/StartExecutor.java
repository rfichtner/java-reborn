import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class StartExecutor {

	public static void doSomeIO(int index) {
		try {
			System.out.println(index + " entering " + Thread.currentThread());

			var numberOfLines = Files.lines(Path.of("StartThreads.java")).count();
			
			System.out.println(index + " exiting " + Thread.currentThread() + " lines: " + numberOfLines);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception{
		
//		var executorService = Executors.newFixedThreadPool(100);
		var executorService = Executors.newVirtualThreadPerTaskExecutor();

		for (var i = 0; i < 1_000; i++) {
			var index = i;
			executorService.submit(() -> doSomeIO(index));
		}

		executorService.shutdown();
		executorService.awaitTermination(10, TimeUnit.SECONDS);
	}

}
