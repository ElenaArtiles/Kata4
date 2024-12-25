package software.ulpgc.control;

import software.ulpgc.model.Title;

import java.io.IOException;
import java.util.List;

public interface TitleReader {
    List<Title> read() throws IOException;
}
