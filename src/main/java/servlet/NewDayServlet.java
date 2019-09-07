package servlet;

import service.DailyReportService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewDayServlet extends HttpServlet {
    private DailyReportService dailyReportService = DailyReportService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        dailyReportService.generateDailyReport();
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
