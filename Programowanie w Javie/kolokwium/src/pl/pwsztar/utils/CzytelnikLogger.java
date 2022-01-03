package pl.pwsztar.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CzytelnikLogger {
    FileOutputStream outputStream;

    public void otworzPlik() throws IOException {
        File userFile = new File("listaCzytelnikow.txt");
        outputStream = new FileOutputStream(userFile);
    }

    public void wpiszDoPliku(String text) throws IOException {
        byte[] strToBytes = text.getBytes();
        outputStream.write(strToBytes);
    }

    public void zamknijPlik() throws IOException {
        outputStream.close();
    }
}
