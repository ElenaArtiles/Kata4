package software.ulpgc;

import software.ulpgc.control.HistogramGenerator;
import software.ulpgc.control.TitleReader;
import software.ulpgc.control.TsvTitleReader;
import software.ulpgc.model.Histogram;
import software.ulpgc.model.Title;
import software.ulpgc.view.MainFrame;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        TitleReader reader = new TsvTitleReader(new File(args[0]), true);
        Histogram histogramData = generateHistogram(reader);
        display(histogramData);

    }

    private static void display(Histogram histogramData) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.put(histogramData);
        mainFrame.setVisible(true);
    }

    private static Histogram generateHistogram(TitleReader reader) throws IOException {
        List<Title> titles = reader.read();
        return new HistogramGenerator().generate(titles);
    }

}
