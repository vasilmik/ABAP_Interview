package com.abap.converter;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public interface LoaderFile {

    File downloadFileOnDisk(URL url) throws IOException;
}
