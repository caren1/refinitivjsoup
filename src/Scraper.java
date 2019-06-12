import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Scraper {
    public static void main(String[] args) throws Exception {

        final PrintWriter writer = new PrintWriter(new File("C:\\Users\\Wojt\\out.html"));
        final String url = " https://www.ote-cr.cz/en/statistics/electricity-imbalances-1";

        try {

            final Document doc = Jsoup.connect(url).get();

            for (Element row : doc.select("table.report_table tbody tr")) {
                if (row.select("td:nth-of-type(1)").text().equals("")) {
                    continue;
                } else {
                    final String hour = row.select("td:nth-of-type(1)").text();

                    final String simabalance = row.select("td:nth-of-type(2)").text();
                    final String tempbalance = simabalance.replace(",", ".");
                    final Double dsimabalance = Double.parseDouble(tempbalance);

                    final String aimbalance = row.select("td:nth-of-type(3)").text();

                    System.out.println(hour + " " + dsimabalance + " " + aimbalance);
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}




