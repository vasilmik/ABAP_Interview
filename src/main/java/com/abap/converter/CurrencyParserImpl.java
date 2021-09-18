package com.abap.converter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.List;
import java.util.Map;

public class CurrencyParserImpl implements CurrencyParser{

    @Override
    public boolean parseFileInMapCurrency(File file, Map<String,Currency> map) {

        File xmlFile = new File(file.getName());

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {

            builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("Valute");

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Currency currency = getCurrency(node);
                    map.put(currency.getCharCode(), currency);
                }
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    private Currency getCurrency(Node node) {

        Element element = (Element) node;

        Currency currency = new Currency();

        currency.setName(getTagValue("Name", element));
        currency.setCharCode(getTagValue("CharCode", element));
        currency.setNumCode(getTagValue("NumCode", element));
        currency.setNominal(Float.parseFloat(getTagValue("Nominal", element)));
        currency.setValue(Double.parseDouble(getTagValue("Value", element).replace(',', '.')));

        return currency;
    }

    private String getTagValue(String tag, Element element) {

        Node itemTag = element.getElementsByTagName(tag).item(0);

        return itemTag.getChildNodes().item(0).getNodeValue();

    }
}
