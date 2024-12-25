package software.ulpgc.control;

import software.ulpgc.model.Histogram;
import software.ulpgc.model.Title;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistogramGenerator {
    public Histogram generate(List<Title> titles) {
        Map<String, Integer> map = new HashMap<>();
        for(Title title : titles) {
            map.putIfAbsent(title.type().name(), 0);
            map.compute(title.type().name(), (k, v) -> v + 1);
        }
        return new FromMapHistogram(map);
    }

    private static class FromMapHistogram implements Histogram {
        private final Map<String, Integer> histogram;

        public FromMapHistogram(Map<String, Integer> histogram) {
            this.histogram = histogram;
        }

        @Override
        public String title() {
            return "Title types Distribution";
        }

        @Override
        public List<String> labels() {
            return new ArrayList<>(histogram.keySet());
        }

        @Override
        public int valueOf(String label) {
            return histogram.get(label);
        }
    }
}
