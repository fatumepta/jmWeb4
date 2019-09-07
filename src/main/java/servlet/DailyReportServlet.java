package servlet;

import model.DailyReport;
import service.DailyReportService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DailyReportServlet extends HttpServlet {
    DailyReportService dailyReportService = DailyReportService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if (req.getPathInfo().contains("all")) {
//            dailyReportService.getAllDailyReports();
//        } else if (req.getPathInfo().contains("last")) {
//            dailyReportService.getLastReport();
//        }

        PrintWriter out = resp.getWriter();
        if (req.getPathInfo().contains("all")) {
            dailyReportService.getAllDailyReports()
                    .forEach(report ->
                            out.printf("<br>id: %d units: %d profit: %d</br>", report.getId(), report.getSoldCars(), report.getEarnings()));
        } else if (req.getPathInfo().contains("last")) {
            DailyReport report = dailyReportService.getLastReport();
            out.printf("<br>id: %d units: %d profit: %d</br>", report.getId(), report.getSoldCars(), report.getEarnings());
        }

        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dailyReportService.deleteAllReports();
        resp.getWriter().println("<b><h2>All reports deleted</h2></b>");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
