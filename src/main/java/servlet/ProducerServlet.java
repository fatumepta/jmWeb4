package servlet;

import model.Car;
import service.CarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProducerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("ProducerServlet");
        resp.setStatus(HttpServletResponse.SC_OK);
        // GET method shouldn't be here!
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Change it! ONLY FOR TEST!
        CarService carService = CarService.getInstance();
        carService.addCar(new Car("Lada", "Vesta", "S985AE178", 650000L));
        carService.addCar(new Car("Lada", "Vesta", "S985AE178", 650000L));
        carService.addCar(new Car("Honda", "Civic", "S985AE78", 330000L));
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
