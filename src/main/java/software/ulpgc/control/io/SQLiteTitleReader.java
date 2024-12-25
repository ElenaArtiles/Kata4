package software.ulpgc.control.io;

import software.ulpgc.model.Title;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteTitleReader implements TitleReader, AutoCloseable{
    private final Connection connection;
    private final PreparedStatement selectStm;
    private final ResultSet titles;

    public SQLiteTitleReader(File dbFile) throws IOException{
        try {
            connection = openConnection(dbFile);
            selectStm = connection.prepareStatement("SELECT * FROM titles;");
            titles = selectStm.executeQuery();
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    @Override
    public Title read() throws IOException {
        try {
            if (titles.next()){
                return new Title(titles.getString(1),
                        Title.TitleType.valueOf(titles.getString(2)),
                        titles.getString(3),
                        titles.getString(4),
                        toGenres(titles.getString(5))
                );
            }
            return null;
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    private List<Title.Genre> toGenres(String text) {
        List<Title.Genre> genres = new ArrayList<>();
        if (text == null || text.isBlank()) {
            return genres;
        }
        for (String name : text.split(",")) {
            genres.add(Title.Genre.valueOf(name));
        }
        return genres;
    }

    private static Connection openConnection(File dbFile) throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + dbFile.getAbsolutePath());
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

}
