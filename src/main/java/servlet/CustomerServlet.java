package servlet;

import com.google.gson.Gson;
import service.CarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CustomerServlet extends HttpServlet {
    private CarService carService = CarService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        resp.setStatus(HttpServletResponse.SC_OK);
        out.println("<b><h2>CustomerServlet</h2></b>");
        carService.getAllCars().forEach(car -> out.printf("<br>[ID: %d BRAND: %s MODEL: %s LIC.PLATE: %s PRICE: %d]</br>",
                car.getId(), car.getModel(), car.getBrand(), car.getLicensePlate(), car.getPrice()));

//        Gson gson = new Gson();
//        String json = gson.toJson(CarService.getInstance().getAllCars());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
    }
}
