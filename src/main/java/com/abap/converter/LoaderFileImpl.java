package com.abap.converter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class LoaderFileImpl implements LoaderFile{

    @Override
    public File downloadFileOnDisk(URL url) throws IOException {

        File file = new File(System.getProperty("user.dir") + "\\currency.xml");

        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();

        return file;

    }
}
