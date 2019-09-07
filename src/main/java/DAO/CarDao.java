package DAO;

import model.Car;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CarDao {
    private Session session;

    public CarDao(Session session) {
        this.session = session;
    }

    public List<Car> getAllCarsFromDB() {
        List<Car> cars = (List<Car>) session.createCriteria(Car.class).list();

        session.close();
        return cars;
    }

    public Car getCar(String licensePlate, String model, String brand) {
        Car car = (Car) session.createCriteria(Car.class)
                .add(Restrictions.eq("licensePlate", licensePlate))
                .add(Restrictions.eq("model", model))
                .add(Restrictions.eq("brand", brand)).uniqueResult();

        session.close();
        return car;
    }

    public void addCarToDB(Car car) {
        Transaction transaction = session.beginTransaction();
        session.save(car);
        transaction.commit();

        session.close();
    }

    public void deleteCarFromDB(Car car) {
        Transaction transaction = session.beginTransaction();
        session.delete(car);
        transaction.commit();

        session.close();
    }

    public int getNumberOfOneBrandCarsInDB(String brand) {
        int number = session.createCriteria(Car.class).add(Restrictions.eq("brand", brand)).list().size();

        session.close();
        return number;
    }

    public void deleteAllCarsFromDB() {
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM Car").executeUpdate();
        transaction.commit();

        session.close();
    }
}
