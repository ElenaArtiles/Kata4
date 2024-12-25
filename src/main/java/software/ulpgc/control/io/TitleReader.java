package software.ulpgc.control.io;

import software.ulpgc.model.Title;

import java.io.IOException;

public interface TitleReader {
    Title read() throws IOException;
}
