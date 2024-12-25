package software.ulpgc.control.io;

import software.ulpgc.model.Title;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TsvTitleReader implements TitleReader, AutoCloseable{
    private final boolean header;
    private final BufferedReader reader;

    public TsvTitleReader(File file, boolean header) throws IOException{
        this.header = header;
        this.reader = new BufferedReader(new FileReader(file));
    }

    public Title read() throws IOException {
        if(header) readerWith(reader);
        String line = reader.readLine();
        if(line == null) return null;
        return new TsvTitleDeserializer().deserialize(line);
    }

    private void readerWith(BufferedReader reader) throws IOException {
        reader.readLine();
    }

    @Override
    public void close() throws Exception {
        reader.close();
    }
}
