package software.ulpgc;

import software.ulpgc.control.HistogramGenerator;
import software.ulpgc.control.TitleLoader;
import software.ulpgc.control.io.SQLiteTitleReader;
import software.ulpgc.control.io.TitleReader;
import software.ulpgc.control.io.TsvTitleReader;
import software.ulpgc.model.Histogram;
import software.ulpgc.model.Title;
import software.ulpgc.view.MainFrame;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        File dbFile = new File(args[1]);
        new TitleLoader(new File(args[0]), dbFile).execute();
        display(generateHistogram(dbFile));

    }

    private static void display(Histogram histogramData) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.put(histogramData);
        mainFrame.setVisible(true);
    }

    private static Histogram generateHistogram(File dbFile) {
        HistogramGenerator generator = new HistogramGenerator();
        try (SQLiteTitleReader reader = new SQLiteTitleReader(dbFile)){
            Title title;
            while ((title = reader.read()) != null){
                generator.feed(title);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return generator.get();
    }

}
