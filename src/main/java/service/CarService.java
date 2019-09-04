package service;

import DAO.CarDao;
import model.Car;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;

public class CarService {
    private static CarService carService;
    private SessionFactory sessionFactory;

    private CarService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static CarService getInstance() {
        if (carService == null) {
            carService = new CarService(DBHelper.getSessionFactory());
        }
        return carService;
    }

    private static CarDao getCarDao() {
        return new CarDao(carService.sessionFactory.openSession());
    }

    public List<Car> getAllCars() {
        return getCarDao().getAllCarsFromDB();
    }

    public void addCar(Car car) {
        getCarDao().addCarToDB(car);
    }
}
