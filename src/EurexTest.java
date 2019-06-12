import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class EurexTest {

    public static void main(String[] args) throws FileNotFoundException {

        final String url = "https://www.eurexchange.com/exchange-en/products/idx/stx/blc/EURO-STOXX-50-Index-Options-46548";
        final PrintWriter writer = new PrintWriter(new File("C:\\Users\\Wojt\\Desktop\\out.html"));
        double sum = 0;

        ArrayList<Float> settles = new ArrayList<>();

//        ArrayList<String> headings = new ArrayList<>();
//        final String tableSelector = "table.dataTable thead tr th span";

        try {
            Document document = Jsoup.connect(url).get();

            for (Element headingRow : document.select("table.dataTable thead tr")) {
                String strikeHeading = headingRow.select("th:nth-of-type(1)").text();
                String dateHeading = headingRow.select("th:nth-of-type(12)").text();
                String settleHeading = headingRow.select("th:nth-of-type(14)").text();

                System.out.println(strikeHeading + "  " + dateHeading + "  " + settleHeading);

            }


            for (Element row : document.select("table.dataTable tbody tr")) {
                if (row.select("td:nth-of-type(1)").text().equals("")) {
                    continue;
                } else {
                    String strike = row.select("td:nth-of-type(1) span").text();
                    String date = row.select("td:nth-of-type(12) span").text();
                    String settle = row.select("td:nth-of-type(14) span").text();

                    final String tempSettle = settle.replace(",", "");
                    final Float floatSettle = Float.parseFloat(tempSettle);

                    settles.add(floatSettle);
//                    sum = (sum += doubleSettle);

                    System.out.println(strike + "  " + date + "  " + settle);

                }

            }
            System.out.println("sum is : " + sum);
            System.out.println("average is : " + (sum / settles.size()));


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
