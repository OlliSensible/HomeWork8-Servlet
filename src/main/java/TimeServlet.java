import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {
    private static final String DEFAULT_TIMEZONE = "UTC";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String timezone = req.getParameter("timezone");

        if (timezone == null) {
            timezone = DEFAULT_TIMEZONE;
        } else {
            timezone = timezone.replace(" ", "+");
        }

        String dateTime = LocalDateTime.now(ZoneId.of(timezone)).atZone(ZoneId.of(timezone))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));

        resp.setContentType("text/html; charset=utf-8");

        try (PrintWriter writer = resp.getWriter()) {
            writer.write(dateTime);
        }
    }
}
