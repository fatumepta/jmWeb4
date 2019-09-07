package servlet;

import model.Car;
import service.CarService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProducerServlet extends HttpServlet {
    private CarService carService = CarService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        // Collect info about new car
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        String licensePlate = req.getParameter("licensePlate");
        long price = Long.parseLong(req.getParameter("price"));
        // Creating a new car
        Car car = new Car(brand, model, licensePlate, price);
        if (carService.addCar(car)) {
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
