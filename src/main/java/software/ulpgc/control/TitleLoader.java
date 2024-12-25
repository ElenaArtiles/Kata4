package software.ulpgc.control;

import software.ulpgc.control.io.SQLiteTitleWriter;
import software.ulpgc.control.io.TsvTitleReader;
import software.ulpgc.model.Title;

import java.io.File;

public class TitleLoader {
    private final File source;
    private final File dbFile;

    public TitleLoader(File source, File dbFile) {
        this.source = source;
        this.dbFile = dbFile;
    }

    public void execute(){
        try (TsvTitleReader reader = new TsvTitleReader(source, true);
             SQLiteTitleWriter writer = new SQLiteTitleWriter(dbFile)) {
            while (true){
                Title title = reader.read();
                if (title == null) break;
                writer.write(title);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
