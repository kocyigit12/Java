package game;

//this class measures the time as seconds

public class T {

    private static long start;
    private static long end;
    private static boolean started;


    private static boolean isStopped() {
        return !started;
    }


    private static boolean isStarted() {
        return started;
    }

    /**
     * Starts stopwatch
     */
    public static void start() {
        if (isStopped()) {
            startTimer();
        } }

    private static void startTimer() {
        start = System.nanoTime();
        started = true;
    }

    /**
     * Stops the stopwatch
     */
    public static void stop() {
        if (isStarted()) {
            stopTimer();
        } }

    private static void stopTimer() {
        end = System.nanoTime();
        started = false;
    }

    /**
     * time between Timer.start() and Timer.stop() is estimated as nanoseconds
     */
    public static long getElapsedTime() {
        if (isStopped()) {
            return end - start;
        } else {
            throw new RuntimeException("Exception: Timer could not be stopped !!!\n");
        }

    }



    /**
     * time between Timer.start() and Timer.stop() is estimated as seconds
     */
    public static double getElapsedSeconds() {
        double seconds = (double) getElapsedTime() / 1000000000.0;
        return seconds;
    }
}


