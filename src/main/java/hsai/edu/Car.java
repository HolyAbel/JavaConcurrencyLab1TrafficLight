package main.java.hsai.edu;

public class Car extends Thread {
    private String direction;
    private TrafficLight trafficLight;

    public Car(String direction, TrafficLight trafficLight) {
        this.direction = direction;
        this.trafficLight = trafficLight;
    }

    public String getDirection() {
        return direction;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Машина \"" + direction + "\" подъезжает.");
            trafficLight.addCar(this);
            try {
                synchronized (this) {
                    this.wait();
                }
                System.out.println("Машина \"" + direction + "\" проехала.");
                trafficLight.removeCar(this);
                sleep((int) (10000 * Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
