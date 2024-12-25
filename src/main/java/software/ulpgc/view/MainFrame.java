package software.ulpgc.view;

import software.ulpgc.model.Histogram;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final JfreeBarChartHistogramDisplay histogramDisplay;

    public MainFrame() throws HeadlessException {
        setTitle("Histogram Display");
        setLocationRelativeTo(null);
        setSize(1100, 850);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        histogramDisplay = new JfreeBarChartHistogramDisplay();
        add(histogramDisplay);
    }

    public void put(Histogram histogram) {
        histogramDisplay.display(histogram);
    }
}
