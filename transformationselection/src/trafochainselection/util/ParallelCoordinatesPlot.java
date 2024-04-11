package trafochainselection.util;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class ParallelCoordinatesPlot extends JFrame {

   public ParallelCoordinatesPlot(final List<String> chainNames, final List<double[]> solutions,
         final List<String> header) {
      super("Parallel Coordinates Plot");

      final List<String> normalizeObjectives = List.of("Length", "T-COM");
      // Normalize T-COM values

      for(final String oName : header) {
         if(!normalizeObjectives.contains(oName)) {
            continue;
         }
         double minO = Double.MAX_VALUE;
         double maxO = Double.MIN_VALUE;

         final int oIdx = header.indexOf(oName);

         for(final double[] solution : solutions) {
            minO = Math.min(minO, solution[oIdx]);
            maxO = Math.max(maxO, solution[oIdx]);
         }

         if(maxO <= 1 && minO >= 0) {
            continue;
         }
         for(final double[] solution : solutions) {
            solution[oIdx] = (solution[oIdx] - minO) / (maxO - minO);
         }
      }

      final List<List<Double>> objectiveValues = new ArrayList<>();

      for(int i = 0; i < header.size(); i++) {
         objectiveValues.add(new ArrayList<>());
      }

      // Extract objective values for each dimension
      for(final double[] solution : solutions) {
         for(int i = 0; i < solution.length; i++) {
            objectiveValues.get(i).add(solution[i]);
         }
      }

      setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      setSize(800, 600);

      // Create dataset
      final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
      for(int i = 0; i < header.size(); i++) {
         for(int j = 0; j < objectiveValues.get(i).size(); j++) {
            dataset.addValue(Math.abs(objectiveValues.get(i).get(j)), String.valueOf(j + 1), header.get(i));
         }
      }

      // Create chart
      final JFreeChart chart = ChartFactory.createLineChart("", "Criteria", "Values", dataset, PlotOrientation.VERTICAL,
            true, true, false);

      // Customize plot
      final CategoryPlot plot = chart.getCategoryPlot();
      final LineAndShapeRenderer renderer = new LineAndShapeRenderer();

      // Set shades of grey for lines
      for(int i = 0; i < solutions.size(); i++) {
         final Color grey = Color.getHSBColor(0, 0, (float) (0.5 + 0.5 * i / solutions.size()));
         renderer.setSeriesPaint(i, grey);
      }

      plot.setRenderer(renderer);
      plot.setRangeGridlinesVisible(true);

      // Set axis labels
      final CategoryAxis domainAxis = plot.getDomainAxis();
      domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
      plot.setBackgroundPaint(new Color(0, 0, 0, 0)); // Transparent color

      // // Customize line colors to shades of grey
      // for(i = 0; i < solutions.get(0).length; i++) {
      // renderer.setSeriesPaint(i, Color.getHSBColor(0, 0, (float) (i * 1.0 / solutions.size())));
      // }

      // Display chart in a panel
      final ChartPanel chartPanel = new ChartPanel(chart);
      chartPanel.setPreferredSize(new Dimension(800, 600));
      setContentPane(chartPanel);
   }
}
