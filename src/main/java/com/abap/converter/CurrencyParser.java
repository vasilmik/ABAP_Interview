package com.abap.converter;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface CurrencyParser {

    boolean parseFileInMapCurrency(File file, Map<String,Currency> map);
}
