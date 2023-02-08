package domain;

import java.util.ArrayList;
import java.util.List;

public class Cars {

    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = cars;
    }

    public void move(NumberGenerator strategy) {
        for (Car car : cars) {
            car.move(strategy.generate());
        }
    }

    public List<Car> getCars() {
        return new ArrayList<>(cars);
    }
}