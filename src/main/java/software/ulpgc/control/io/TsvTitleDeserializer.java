package software.ulpgc.control.io;

import software.ulpgc.model.Title;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TsvTitleDeserializer implements TitleDeserializer{
    public Title deserialize(String line) {
        String[] columns = line.split("\t");
        return new Title(columns[0], typeOf(columns[1]), columns[2], columns[3], genre(columns[8]));

    }

    private List<Title.Genre> genre(String column) {
        if (column.equals("\\N")) return Collections.emptyList();
        List<Title.Genre> genres = new ArrayList<>();
        for(String genre : column.split(","))
            genres.add(Title.Genre.valueOf(genre.trim().replace("-", "")));
        return genres;
    }

    private Title.TitleType typeOf(String column) {
        return Title.TitleType.valueOf(capitalize(column.trim()));
    }

    private String capitalize (String value) {
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }
}
