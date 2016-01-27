package jssp.clustering;

import com.google.common.collect.Lists;
import org.apache.commons.math3.ml.clustering.*;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@SuppressWarnings("serial")
public class ClusterAlgorithmComparison extends ExampleUtils.ExampleFrame {


    public ClusterAlgorithmComparison(List<List<DoublePoint>> dataSets) {

        setTitle("Commons-Math: Cluster algorithm comparison");
        setSize(800, 800);
        setLayout(new GridBagLayout());


        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(2, 2, 2, 2);

        List<Pair<String, Clusterer<DoublePoint>>> algorithms = algorithms();

        for (Pair<String, Clusterer<DoublePoint>> pair : algorithms) {
            JLabel text = new JLabel("<html><body>" + pair.getFirst().replace("\n", "<br>"));
            add(text, c);
            c.gridx++;
        }
        c.gridy++;

        for (List<DoublePoint> dataset : dataSets) {
            c.gridx = 0;
            for (Pair<String, Clusterer<DoublePoint>> pair : algorithms) {
                long start = System.currentTimeMillis();
                List<? extends Cluster<DoublePoint>> clusters = pair.getSecond().cluster(dataset);
                long end = System.currentTimeMillis();
                add(new ClusterPlot(clusters, end - start), c);
                c.gridx++;
            }
            c.gridy++;
        }
    }

    private List<Pair<String, Clusterer<DoublePoint>>> algorithms() {
        List<Pair<String, Clusterer<DoublePoint>>> algorithms = Lists.newArrayList();

        algorithms.add(new Pair<>("KMeans\n(k=2)", new KMeansPlusPlusClusterer<>(2)));
        algorithms.add(new Pair<>("KMeans\n(k=3)", new KMeansPlusPlusClusterer<>(3)));
        algorithms.add(new Pair<>("FuzzyKMeans\n(k=3, fuzzy=2)", new FuzzyKMeansClusterer<>(3, 2)));
        algorithms.add(new Pair<>("FuzzyKMeans\n(k=3, fuzzy=10)", new FuzzyKMeansClusterer<>(3, 10)));
        algorithms.add(new Pair<>("DBSCAN\n(eps=.1, min=3)", new DBSCANClusterer<>(0.1, 3)));
        return algorithms;
    }

    public static void main(String[] args) {
        ExampleUtils.showExampleFrame(new ClusterAlgorithmComparison(new DataSet(1500, new Well19937c(-2), true).generate()));
    }
}