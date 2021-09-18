package com.abap.converter;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {

    private LoaderFile loader;
    private CurrencyParser parser;

    public void setLoader(LoaderFile loader) {
        this.loader = loader;
    }

    public void setParser(CurrencyParser parser) {
        this.parser = parser;
    }

    private String  urlPath = "https://www.cbr.ru/scripts/XML_daily.asp";
    private Map<String, Currency> mapCodeCurrency = new HashMap<>();

    public Double getChangeRatePair(String codeOne, String codeTwo) {

        try {

            URL url = createURLforDownload();
            File file = loader.downloadFileOnDisk(url);

            if (parser.parseFileInMapCurrency(file, mapCodeCurrency)) {

                double rate = mapCodeCurrency.get(codeOne).exchangeRateOneCurrencyToRub()/
                        mapCodeCurrency.get(codeTwo).exchangeRateOneCurrencyToRub();
                return Math.round(rate * 10000.0) / 10000.0;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0.0;

    }

    private URL createURLforDownload() throws MalformedURLException {
        return new URL(urlPath);
    }


}
