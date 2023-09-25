package main.java.hsai.edu;

import java.util.List;

public class App {
    public static void main(String[] args) throws InterruptedException {
        TrafficLight tl = new TrafficLight();

        List<String> carDirs = List.of("NS", "ES", "SN", "WE");
        for (String dir: carDirs) {
            new Thread(new Car(dir, tl)).start();
        }

        while (true) {
            if (tl.getWaitingCars().size() > 1) {
                tl.unlockDirections();
                tl.useUnlockedDirections();
            }
            Thread.sleep(2000);
        }
    }
}
