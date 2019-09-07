package service;

import DAO.CarDao;
import model.Car;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;


public class CarService {
    private static CarService carService;
    private SessionFactory sessionFactory;
    private DailyReportService dailyReportService = DailyReportService.getInstance();

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

    public boolean addCar(Car car) {
        if (getNumberOfOneBrandCars(car.getBrand()) < 10) {
            getCarDao().addCarToDB(car);
            return true;
        }

        return false;
    }

    public void buyCar(String brand, String model, String licensePlate) {
        Car car = getCarDao().getCar(licensePlate, model, brand);
        dailyReportService.commitDeal(car.getPrice());
        getCarDao().deleteCarFromDB(car);
    }

    private int getNumberOfOneBrandCars(String brand) {
        return getCarDao().getNumberOfOneBrandCarsInDB(brand);
    }

    public void deleteAllCars() {
        getCarDao().deleteAllCarsFromDB();
    }
}
