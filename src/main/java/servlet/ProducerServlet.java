package servlet;

import model.Car;
import service.CarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProducerServlet extends HttpServlet {
    private CarService carService = CarService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Collect info about new car
//        String brand = req.getParameter("brand");
//        String model = req.getParameter("model");
//        String licensePlate = req.getParameter("licensePlate");
//        long price = Long.parseLong(req.getParameter("price"));

        // Creating a new car
//        Car car = new Car(brand, model, licensePlate, price);

        carService.addCar(new Car("Lada", "Vesta", "S985AE178", 650000L));
        carService.addCar(new Car("Honda", "Civic", "S985AE78", 330000L));
        carService.addCar(new Car("Uaz", "Patriot", "S123", 250000L));
        carService.addCar(new Car("KIA", "Spectra", "D321", 33000L));
        carService.addCar(new Car("Lada", "Priora", "R789", 6500005L));
        carService.addCar(new Car("Honda", "Legend", "U174", 3300003L));
//        if (carService.addCar(car)) {
//            resp.setStatus(HttpServletResponse.SC_OK);
//        } else {
//            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
//        }
    }
}
