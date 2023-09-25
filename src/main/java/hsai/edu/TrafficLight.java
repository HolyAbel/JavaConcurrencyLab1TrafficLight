package main.java.hsai.edu;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TrafficLight {
    private Set<String> unlockedDirections = Collections.synchronizedSet(new CopyOnWriteArraySet<>());
    private List<Car> waitingCars = new CopyOnWriteArrayList<>();;
    private Lock lock = new ReentrantLock(true);

    Map<String, Set<String>> conflictingDirections = Map.of(
            "NS", Set.of("EW", "WE"),
            "ES", Set.of("WE", "SN"),
            "SN", Set.of("WE", "ES"),
            "WE", Set.of("NS", "SN", "ES")
    );

    public List<Car> getWaitingCars() {
        return waitingCars;
    }

    public void addCar(Car c) {
        waitingCars.add(c);
    }

    public void removeCar(Car c) {
        waitingCars.remove(c);
    }

    public void unlockDirections() {
        Iterator<Car> iterator = waitingCars.iterator();
        Car firstCar = iterator.next();
        unlockedDirections.add(firstCar.getDirection());
        String cars = "Машины на светофоре: " + firstCar.getDirection();
        while(iterator.hasNext()){
            Car car = iterator.next();
            cars += ", " + car.getDirection();
            boolean hasNoConflict = true;
            for (String direction: unlockedDirections) {
                if (conflictingDirections.get(car.getDirection()).contains(direction)) {
                    hasNoConflict = false;
                }
            }
            if (hasNoConflict) {
                unlockedDirections.add(car.getDirection());
            }
        }
        System.out.println(cars);
    }

    public void useUnlockedDirections() {
        System.out.println("Доступные направления: " + unlockedDirections);
        synchronized (lock) {
            Iterator<Car> it = waitingCars.iterator();
            while (it.hasNext()) {
                Car car = it.next();
                if (unlockedDirections.contains(car.getDirection())) {
                    synchronized (car) {
                        car.notify();
                    }
                }
            }
            unlockedDirections.clear();
        }
    }
}
