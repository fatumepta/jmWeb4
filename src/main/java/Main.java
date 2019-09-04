import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.hibernate.SessionFactory;
import servlet.CustomerServlet;
import servlet.DailyReportServlet;
import servlet.NewDayServlet;
import servlet.ProducerServlet;
import util.DBHelper;

public class Main {

    public static void main(String[] args) throws Exception {
        SessionFactory sessionFactory = DBHelper.getSessionFactory();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new CustomerServlet()), "/customer");
        context.addServlet(new ServletHolder(new ProducerServlet()), "/producer");
        context.addServlet(new ServletHolder(new NewDayServlet()), "/newday");
        context.addServlet(new ServletHolder(new DailyReportServlet()), "/report/*");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        server.join();
    }
}
