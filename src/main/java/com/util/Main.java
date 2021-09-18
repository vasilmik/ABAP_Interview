package com.util;

import com.abap.converter.CurrencyConverter;
import com.abap.converter.CurrencyParserImpl;
import com.abap.converter.LoaderFileImpl;

public class Main {

    public static void main(String[] args) {

        CurrencyConverter converter = new CurrencyConverter();
        converter.setLoader(new LoaderFileImpl());
        converter.setParser(new CurrencyParserImpl());

        Double changeRatePair = converter.getChangeRatePair("NOK", "HUF");
        System.out.println("One norway crow equal "+changeRatePair+" forints");
    }

}
