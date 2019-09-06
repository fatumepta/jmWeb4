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
        return (List<Car>) session.createCriteria(Car.class).list();
    }

    public Car getCar(String licensePlate, String model, String brand) {
        return (Car) session.createCriteria(Car.class)
                .add(Restrictions.eq("licensePlate", licensePlate))
                .add(Restrictions.eq("model", model))
                .add(Restrictions.eq("brand", brand)).uniqueResult();
    }

    public void addCarToDB(Car car) {
        Transaction transaction = session.beginTransaction();
        session.save(car);
        transaction.commit();
    }

    public void deleteCarFromDB(Car car) {
        Transaction transaction = session.beginTransaction();
        session.delete(car);
        transaction.commit();
    }

    public int getNumberOfOneBrandCarsInDB(String brand) {
        return session.createCriteria(Car.class).add(Restrictions.eq("brand", brand)).list().size();
    }
}
