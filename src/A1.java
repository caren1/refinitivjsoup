import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class A1 {
    public static void main(String[] args) throws IOException, ParseException {

        final File input = new File("C:\\Users\\Wojt\\Desktop\\Refinitiv\\task_A1.html");
        Document doc = Jsoup.parse(input, "UTF-8");
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-mm-yyyy");
        String lines[] = new String[3];
//        final SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");

        for (Element row : doc.select("table tbody tr.primera.odd")) {
            if (row.select("th:nth-of-type(1)").text().equals("")) {
                continue;
            } else {
                String cell1 = row.select("th:nth-of-type(1)").text();
                String cell2 = row.select("th:nth-of-type(2)").text();
                String cell3 = row.select("th:nth-of-type(3)").text();
                String cell4 = row.select("th:nth-of-type(4)").text();
                String cell5 = row.select("th:nth-of-type(5)").text();
                String cell6 = row.select("th:nth-of-type(6)").text();
                String cell7 = row.select("th:nth-of-type(7)").text();
                String cell8 = row.select("th:nth-of-type(8)").text();

                if (cell1.equals("2017/10/10")) {

                    SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy/MM/dd");
                    Date date = oldFormat.parse(cell1);

                    SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String output = newFormat.format(date);

                    cell2 = cell2.toUpperCase();
                    cell3 = cell3.toUpperCase().replace(",", "");
                    cell4 = cell4.toUpperCase();
                    cell5 = cell5.toUpperCase();
                    cell6 = cell6.toUpperCase().replace("+", "Y");
                    cell7 = cell7.toUpperCase().replaceAll("[()0-9]", "");
                    cell8 = cell8.toUpperCase().replaceAll("[()+%0-9 ]", ".").substring(0, cell8.length() - 4);

                    String row1 = (output + "   " + cell2 + "   " + cell3 + "   " + cell4 + "   " + cell5 + "   " + cell6 + "   " + cell7 + "  " + cell8);
                    lines[0] = row1;

                } else if (cell1.equals("10102017T0000")) {

                    cell1 = cell1.substring(0, cell1.length() - 5);

                    SimpleDateFormat oldFormat = new SimpleDateFormat("ddMMyyyy");
                    Date date = oldFormat.parse(cell1);

                    SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String output = newFormat.format(date);


                    cell2 = cell2.toUpperCase();
                    cell3 = cell3.toUpperCase().replace(",", "");
                    cell4 = cell4.toUpperCase();
                    cell5 = cell5.toUpperCase().replace("%", " ");
                    cell6 = cell6.toUpperCase().substring(0, cell6.length() - 3);
//                            .replace("[()0-9]", "");
                    cell7 = cell7.toUpperCase();
                    cell8 = cell8.toUpperCase().replaceAll("[()+%0-9 ]", ".").substring(0, cell8.length() - 4);

                    String row2 = (output + "   " + cell2 + "   " + cell3 + "   " + cell4 + "   " + cell5 + "   " + cell6 + "   " + cell7 + "   " + cell8);
                    lines[1] = row2;

                } else if (cell1.equals("10-10-2017")) {

                    cell2 = cell2.toUpperCase();
                    cell3 = cell3.toUpperCase().replace(",", ".");
                    cell4 = cell4.toUpperCase();
                    cell5 = cell5.toUpperCase().replace("%", " ");
                    cell6 = cell6.toUpperCase().replace("()+%0-9 ", "");
                    cell7 = cell7.toUpperCase();
                    cell8 = cell8.toUpperCase().replaceAll("[()+%0-9 ]", ".");

                    String row3 = (cell1 + "   " + cell2 + "   " + cell3 + "   " + cell4 + "   " + cell5 + "   " + cell6 + "   " + cell7 + "   " + cell8);
                    lines[2] = row3;
                }

            }
        }
        for (String row :
                lines) {
            System.out.println(row);
        }

    }
}
