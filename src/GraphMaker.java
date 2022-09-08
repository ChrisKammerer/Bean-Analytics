import java.io.IOException;
import java.util.LinkedList;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class GraphMaker extends JFrame{
    private static DataReader beans;
    private XYDataset xydataset;

    public GraphMaker(String title) throws IOException {
        beans = new DataReader("Dry_Beans.csv");
        xydataset = createDataset(beans.createBeanList());
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Bean Data", "Roundness", "AspectRatio", xydataset,
                PlotOrientation.VERTICAL, false, false, false);
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setBackgroundPaint(new Color(255,228,196));
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private XYDataset createDataset(LinkedList<Bean> beanDataset) {
        XYSeries series1 = new XYSeries("Bean");
        XYSeriesCollection dataset = new XYSeriesCollection();
        for (Bean bean : beanDataset) {
            series1.add(bean.roundness, bean.aspectRatio);
        }
        dataset.addSeries(series1);
        return dataset;
    }

    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(()->{
            try {
                GraphMaker graph = new GraphMaker("Bean Data");
                graph.setSize(1000,600);
                graph.setLocationRelativeTo(null);
                graph.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                graph.setVisible(true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
