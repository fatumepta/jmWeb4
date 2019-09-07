package service;

import DAO.DailyReportDao;
import model.DailyReport;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;

public class DailyReportService {
    private static DailyReportService dailyReportService;
    private SessionFactory sessionFactory;
    private long soldTodayUnits = 0L;
    private long profitToday = 0L;

    public void commitDeal(long price) {
        ++soldTodayUnits;
        profitToday += price;
    }

    private void closeTheDay() {    // reset daily sales counters
        soldTodayUnits = 0L;
        profitToday = 0L;
    }

    private DailyReportService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static DailyReportService getInstance() {
        if (dailyReportService == null) {
            dailyReportService = new DailyReportService(DBHelper.getSessionFactory());
        }
        return dailyReportService;
    }

    private static DailyReportDao getDailyReportDao() {
        return new DailyReportDao(dailyReportService.sessionFactory.openSession());
    }

    public List<DailyReport> getAllDailyReports() {
        return getDailyReportDao().getAllDailyReport();
    }


    public DailyReport getLastReport() {
        return getDailyReportDao().getLastReportFromDB();
    }

    public void generateDaylyReport() {
        DailyReport today = new DailyReport(profitToday, soldTodayUnits);
        getDailyReportDao().addDailyReportToDB(today);
        closeTheDay();
    }

    public void deleteAllReports() {
        getDailyReportDao().deleteAllReportsFromDB();
    }
}
