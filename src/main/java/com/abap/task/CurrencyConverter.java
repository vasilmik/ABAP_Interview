package com.abap.task;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrencyConverter {

    public static final String URL = "https://www.cbr.ru/scripts/XML_daily.asp";
    public static final String FILE = System.getProperty("user.dir") + "\\currency.xml";
    public static final String HUB_ID = "R01135";
    public static final String NOK = "R01535";

    public static Map<String, Currency> currencyMap = new HashMap<>();

    public static void main(String[] args) {

        try {
            downloadFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        loadMapCurrency();

        Currency crown = currencyMap.get(NOK);
        double exchangeRateOneCrownToRub = crown.getValue()/crown.getNominal();

        Currency forint = currencyMap.get(HUB_ID);
        double exchangeRateFromRubRoForint = forint.getNominal()/forint.getValue();

        double exchangeRateOneCrownToForint = exchangeRateOneCrownToRub*exchangeRateFromRubRoForint;

        System.out.println("One crown is equals "+exchangeRateOneCrownToForint + " forints");
    }

    private static void downloadFile() throws IOException {

        URL url = new URL(URL);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(FILE);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();

    }

    private static void loadMapCurrency() {

        File xmlFile = new File(FILE);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {

            builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("Valute");

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);
                String ID = node.getAttributes().getNamedItem("ID").getNodeValue();

                if(HUB_ID.equals(ID) || NOK.equals(ID)){
                    currencyMap.put(ID,getCurrency(node));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Currency getCurrency(Node node) {

        Currency currency = new Currency();

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            currency.setName(getTagValue("Name", element));
            currency.setCharCode(getTagValue("CharCode", element));
            currency.setNumCode(getTagValue("NumCode", element));
            currency.setNominal(Float.parseFloat(getTagValue("Nominal", element)));
            currency.setValue(Double.parseDouble(getTagValue("Value", element).replace(',','.')));

        }

        return currency;
    }

    private static String getTagValue(String tag, Element element) {

        Node itemTag = element.getElementsByTagName(tag).item(0);

        return itemTag.getChildNodes().item(0).getNodeValue();

    }

}
