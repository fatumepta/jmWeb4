package servlet;

import com.google.gson.Gson;
import service.CarService;
import service.DailyReportService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DailyReportServlet extends HttpServlet {
    private DailyReportService dailyReportService = DailyReportService.getInstance();
    private CarService carService = CarService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Gson gson = new Gson();

        String json = null;
        if (req.getPathInfo().contains("all")) {
            json = gson.toJson(dailyReportService.getAllDailyReports());
        } else if (req.getPathInfo().contains("last")) {
            json = gson.toJson(dailyReportService.getLastReport());
        }

        resp.getWriter().println(json);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        dailyReportService.deleteAllReports();
        carService.deleteAllCars();
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
