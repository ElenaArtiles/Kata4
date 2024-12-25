package software.ulpgc.control.io;

import software.ulpgc.model.Title;

import java.io.IOException;

public interface TitleWriter {
    void write(Title title) throws IOException;
}
