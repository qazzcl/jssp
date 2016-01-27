package jssp.clustering;

import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.Clusterable;
import org.apache.commons.math3.ml.clustering.DoublePoint;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.List;

import static java.awt.Color.*;

@SuppressWarnings("serial")
public class ClusterPlot extends JComponent {

    private static double PAD = 10;

    private List<? extends Cluster<DoublePoint>> clusters;
    private long duration;

    public ClusterPlot(final List<? extends Cluster<DoublePoint>> clusters, long duration) {
        this.clusters = clusters;
        this.duration = duration;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        g2.clearRect(0, 0, w, h);

        g2.setPaint(black);
        g2.drawRect(0, 0, w - 1, h - 1);

        int index = 0;
        Color[] colors = new Color[]{red, blue, green.darker()};
        for (Cluster<DoublePoint> cluster : clusters) {
            g2.setPaint(colors[index++]);
            for (DoublePoint point : cluster.getPoints()) {
                Clusterable p = transform(point, w, h);
                double[] arr = p.getPoint();
                g2.fill(new Ellipse2D.Double(arr[0] - 1, arr[1] - 1, 3, 3));
            }

            if (cluster instanceof CentroidCluster) {
                Clusterable p = transform(((CentroidCluster<?>) cluster).getCenter(), w, h);
                double[] arr = p.getPoint();
                Shape s = new Ellipse2D.Double(arr[0] - 4, arr[1] - 4, 8, 8);
                g2.fill(s);
                g2.setPaint(Color.black);
                g2.draw(s);
            }
        }

        g2.setPaint(Color.black);
        g2.drawString(String.format("%.2f s", duration / 1e3), w - 40, h - 5);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(150, 150);
    }

    private Clusterable transform(Clusterable point, int width, int height) {
        double[] arr = point.getPoint();
        return new DoublePoint(new double[]{PAD + (arr[0] + 1) / 2.0 * (width - 2 * PAD),
                height - PAD - (arr[1] + 1) / 2.0 * (height - 2 * PAD)});
    }
}