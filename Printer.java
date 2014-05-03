import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by krejcir on 1.5.14.
 */
public class Printer {

    private DocumentBuilderFactory documentBuilderFactory;
    private DocumentBuilder documentBuilder;

    public Printer() {
        try {
            this.documentBuilderFactory = DocumentBuilderFactory.newInstance();
            this.documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }
    }

    public void print(Schedule schedule, String file) {
        Item[][] prepareSchedule = this.prepareSchedule(schedule);

        Document document = documentBuilder.newDocument();

        Element html = document.createElement("html");
        document.appendChild(html);

        Element head = document.createElement("head");
        html.appendChild(head);

        Element css = document.createElement("link");
        css.setAttribute("rel", "stylesheet");
        css.setAttribute("type", "text/css");
        css.setAttribute("href", "./src/style.css");
        head.appendChild(css);

        Element body = document.createElement("body");
        html.appendChild(body);

        Element table = document.createElement("table");
        body.appendChild(table);

        Element tHead = document.createElement("thead");
        table.appendChild(tHead);

        Element tr = document.createElement("tr");
        tHead.appendChild(tr);

        Element thn = document.createElement("th");
        thn.appendChild(document.createTextNode(""));
        tr.appendChild(thn);

        for (int i = 1; i < prepareSchedule[0].length; i++) {
            Element th = document.createElement("th");
            th.appendChild(document.createTextNode(i + ""));
            tr.appendChild(th);
        }

        Element tBody = document.createElement("tbody");
        table.appendChild(tBody);

        for (int i = 0; i < prepareSchedule.length; i++) {
            Element trb = document.createElement("tr");
            tBody.appendChild(trb);

            Element tday = document.createElement("td");
            String dayName = "";
            switch (i + 1) {
                case Item.MONDAY:
                    dayName = "Monday";
                    break;
                case Item.TUESDAY:
                    dayName = "Tuesday";
                    break;
                case Item.WEDNESDAY:
                    dayName = "Wednesday";
                    break;
                case Item.THURSDAY:
                    dayName = "Thursday";
                    break;
                case Item.FRIDAY:
                    dayName = "Friday";
                    break;
                case Item.SATURDAY:
                    dayName = "Saturday";
                    break;
                case Item.SUNDAY:
                    dayName = "Sunday";
                    break;
            }
            tday.appendChild(document.createTextNode(dayName));
            trb.appendChild(tday);

            for (int j = 1; j < prepareSchedule[i].length; j++) {

                Element td = document.createElement("td");

                Item item = prepareSchedule[i][j];
                if (prepareSchedule[i][j] != null) {
                    if (item.getStart() <= j && j <= item.getStart() + item.getLength()) {
                        if (item instanceof Exercise) {
                            td.appendChild(document.createTextNode(item.getSubjectName()));
                        } else if (item instanceof Lecture) {
                            Element bold = document.createElement("b");
                            bold.appendChild(document.createTextNode(item.getSubjectName()));
                            td.appendChild(bold);
                        }
                    }
                }
                trb.appendChild(td);
            }
        }
        this.write(document, file);
    }

    private Item[][] prepareSchedule(Schedule schedule) {
        ArrayList<Item> items = schedule.getItems();

        Item[][] table = new Item[7][20];

        for (Item item : items) {
            for (int i = 0; i < item.getLength(); i++) {
                table[item.getDay() - 1][item.getStart() + i] = item;
            }
        }
        return table;
    }

    private void write(Document document, String file) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(file));

            transformer.transform(source, result);
            System.out.println("Done!");
        } catch (TransformerConfigurationException e) {
            System.out.println(e.getMessage());
        } catch (TransformerException e) {
            System.out.println(e.getMessage());
        }

    }
}
