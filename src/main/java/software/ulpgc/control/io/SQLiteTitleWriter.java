package software.ulpgc.control.io;

import software.ulpgc.model.Title;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLiteTitleWriter implements TitleWriter, AutoCloseable{
    private final Connection connection;
    private static final String createTable = """
            CREATE TABLE IF NOT EXISTS titles (
                id TEXT PRIMARY KEY,
                type TEXT,
                primaryTitle TEXT,
                originalTitle TEXT,
                genres TEXT
            );
            """;
    private static final String insertStatement = "INSERT INTO titles(id, type, primaryTitle, originalTitle, genres) VALUES(?,?,?,?,?)";
    private PreparedStatement insertStm;

    public SQLiteTitleWriter(File dbFile) throws IOException{
        try {
            connection = openConnection(dbFile);
            prepareDatabase();
        } catch (SQLException e) {
            throw new IOException(e.getCause());
        }
    }

    @Override
    public void write(Title title) throws IOException {
        try {
            insertStm.setString(1, title.id());
            insertStm.setString(2, title.type().name());
            insertStm.setString(3, title.primaryTitle());
            insertStm.setString(4, title.originalTitle());
            insertStm.setString(5, String.join(",", toString(title.genres())));
            insertStm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
    }

    private List<String> toString(List<Title.Genre> genres) {
        List<String> names = new ArrayList<>();
        for (Title.Genre genre : genres) {
            names.add(genre.name());
        }
        return names;
    }

    private static Connection openConnection(File dbFile) throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + dbFile.getAbsolutePath());
    }

    private void prepareDatabase() throws SQLException {
        connection.createStatement().execute(createTable);
        this.insertStm = connection.prepareStatement(insertStatement);
        connection.setAutoCommit(false);
    }

    @Override
    public void close() throws Exception {
        connection.commit();
        connection.close();
    }
}
