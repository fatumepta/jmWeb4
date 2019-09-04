package DAO;

import model.Car;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CarDao {

    private Session session;

    public CarDao(Session session) {
        this.session = session;
    }

    public List<Car> getAllCarsFromDB() {
        Transaction transaction = session.beginTransaction();
        List<Car> allCars = session.createQuery("FROM Car").list();
        transaction.commit();
        session.close();

        return allCars;
    }

    // Выставить ограничения на добавление автомобилей (не более 10 шт. одного бренда в день)
    // Подумать, где выставить ограничения - в сервисе или в ДАО?
    // Добавить метод запрашивающий количество автомобилей определенного бренда в из БД
    public void addCarToDB(Car car) {
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(car);
        transaction.commit();
        session.close();

    }

}
