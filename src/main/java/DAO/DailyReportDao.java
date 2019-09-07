package DAO;

import model.DailyReport;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DailyReportDao {
    private Session session;

    public DailyReportDao(Session session) {
        this.session = session;
    }

    public List<DailyReport> getAllDailyReport() {
        Transaction transaction = session.beginTransaction();
        List<DailyReport> dailyReports = session.createQuery("FROM DailyReport").list();
        transaction.commit();

        session.close();
        return dailyReports;
    }

    public void deleteAllReportsFromDB() {
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM DailyReport").executeUpdate();
        transaction.commit();

        session.close();
    }

    public DailyReport getLastReportFromDB() {
        Transaction transaction = session.beginTransaction();
        DailyReport lastReport = (DailyReport) session.createQuery(
                "FROM DailyReport ORDER BY id DESC").setMaxResults(1).list().get(0);

        session.close();
        return lastReport;
    }

    public void addDailyReportToDB(DailyReport report) {
        Transaction transaction = session.beginTransaction();
        session.save(report);
        transaction.commit();

        session.close();
    }
}
