import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Task2 {
    private static void transfer(final InputStream inputStream, final PrintStream printStream, final String errorMessagePrefix) {
        try {
            inputStream.transferTo(printStream);
        } catch (IOException e) {
            System.err.printf("%s: %s%n", errorMessagePrefix, e.getMessage());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Scanner cin = new Scanner(System.in);
        System.out.println("Please enter the path to the Python executable (Python interpreter):");
        final String pythonInterpreterPath = cin.nextLine();
        cin.close();
        final Process pythonProcess;
        try {
            pythonProcess = new ProcessBuilder().command(pythonInterpreterPath, "-m", "timeit", "-r", "10").start();
        } catch (IOException e) {
            System.err.println("Couldn't start Python interpreter: " + e.getMessage());
            return;
        }
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int secondsPassed = 1;

            @Override
            public void run() {
                System.out.printf("Seconds passed: %d%n", secondsPassed++);
            }
        }, 1000, 1000);

        final int exitCode = pythonProcess.waitFor();
        timer.cancel();

        if (exitCode != 0) {
            System.err.println("Python interpreter returned non-zero exit code: " + exitCode);
        }
        transfer(pythonProcess.getInputStream(), System.out, "Couldn't read stdout of the Python interpreter");
        transfer(pythonProcess.getErrorStream(), System.err, "Couldn't read stderr of the Python interpreter");
    }
}
