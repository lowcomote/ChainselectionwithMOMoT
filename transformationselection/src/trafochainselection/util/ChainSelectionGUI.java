package trafochainselection.util;

import java.awt.Component;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.apache.commons.math3.stat.correlation.KendallsCorrelation;

public class ChainSelectionGUI extends JFrame {

   class ComboBoxItem {
      private final String label;
      private final int value;

      public ComboBoxItem(final String label, final int value) {
         this.label = label;
         this.value = value;
      }

      public String getLabel() {
         return label;
      }

      public int getValue() {
         return value;
      }

      @Override
      public String toString() {
         return label;
      }
   }

   class GreyBackgroundRenderer extends DefaultTableCellRenderer {
      @Override
      public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected,
            final boolean hasFocus, final int row, final int column) {
         final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
         c.setBackground(new java.awt.Color(238, 238, 238));

         setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

         if(row == -1) {
            c.setFont(c.getFont().deriveFont(java.awt.Font.BOLD)); // Make header row bold
         }
         return c;
      }
   }

   java.text.DecimalFormat df = new java.text.DecimalFormat("#.###");

   private JTable table;

   private JTable correlationTable;
   private DefaultTableModel tableModel;
   private JComboBox<ComboBoxItem> sortComboBox;

   // private final List<double[]> solutions;
   private DefaultTableModel correlationTableModel;

   // private final List<String> chainNames;

   private final List<ChainSolution> chainSolutions;

   final List<String> header;

   public ChainSelectionGUI(final List<String> chainNames, final List<double[]> solutions, final List<String> header) {
      // this.solutions = solutions;
      // this.chainNames = chainNames;
      df.setRoundingMode(java.math.RoundingMode.CEILING);

      this.chainSolutions = new ArrayList<>();
      for(int i = 0; i < chainNames.size(); i++) {
         this.chainSolutions.add(
               new ChainSolution(chainNames.get(i), Arrays.stream(solutions.get(i)).map(x -> Math.abs(x)).toArray()));
      }

      this.header = header;
      initializeUI();
   }

   private double computeKendallsTau(final double[] x, final double[] y) {
      final KendallsCorrelation correlation = new KendallsCorrelation();
      return correlation.correlation(x, y);
   }

   private double[] getColumnValues(final int columnIndex) {
      final double[] columnValues = new double[chainSolutions.size()];
      for(int i = 0; i < chainSolutions.size(); i++) {
         columnValues[i] = chainSolutions.get(i).values[columnIndex];
      }
      return columnValues;
   }

   private void initializeUI() {
      setTitle("Transformation chains and criteria correlations");
      setSize(600, 400);
      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

      tableModel = new DefaultTableModel();
      table = new JTable(tableModel);
      final JScrollPane solutionScrollPane = new JScrollPane(table);

      // Correlation table
      correlationTableModel = new DefaultTableModel();
      correlationTable = new JTable(correlationTableModel);
      final JScrollPane correlationScrollPane = new JScrollPane(correlationTable);

      // Solutions panel
      final JPanel solutionsPanel = new JPanel(new java.awt.BorderLayout());
      solutionsPanel.add(solutionScrollPane, java.awt.BorderLayout.CENTER);

      // Control panel
      final JPanel controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());
      final JLabel sortLabel = new JLabel("Sort by Criterion:");
      controlPanel.add(sortLabel);

      sortComboBox = new JComboBox<>();

      for(int i = 0; i < header.size(); i++) {
         sortComboBox.addItem(new ComboBoxItem(header.get(i), i));
      }
      sortComboBox.addActionListener(e -> sortSolutions()); // Adding ActionListener to JComboBox
      controlPanel.add(sortComboBox);

      solutionsPanel.add(controlPanel, java.awt.BorderLayout.SOUTH);

      final JLabel solutionsHeaderLabel = new JLabel("Solution set", javax.swing.SwingConstants.CENTER);

      solutionsPanel.add(solutionsHeaderLabel, java.awt.BorderLayout.NORTH);

      // Correlation panel
      final JPanel correlationPanel = new JPanel(new java.awt.BorderLayout());
      correlationPanel.add(new JScrollPane(correlationTable), java.awt.BorderLayout.CENTER);

      final JLabel correlationHeaderLabel = new JLabel("Correlation (Kendall's tau-b)",
            javax.swing.SwingConstants.CENTER);

      correlationPanel.add(correlationHeaderLabel, java.awt.BorderLayout.NORTH);
      // Main panel
      final JPanel mainPanel = new JPanel(new java.awt.GridLayout(2, 1));
      mainPanel.add(solutionsPanel);
      mainPanel.add(correlationPanel);

      // Center align cell contents in correlation table
      final DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
      renderer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
      correlationTable.setDefaultRenderer(Object.class, renderer);

      // Add the main panel to the frame
      setContentPane(mainPanel);
      // add(controlPanel, BorderLayout.SOUTH);

      updateTable();
      updateCorrelationTable();

   }

   private void sortSolutions() {
      final ComboBoxItem selectedItem = (ComboBoxItem) sortComboBox.getSelectedItem();
      if(selectedItem != null) {
         final int sortDimension = selectedItem.getValue();

         chainSolutions.sort((o1, o2) -> Double.compare(o2.values[sortDimension], o1.values[sortDimension]));

         updateTable();
      }
   }

   private void updateCorrelationTable() {
      correlationTableModel.setColumnCount(0);
      correlationTableModel.setRowCount(0);

      // Calculate correlation matrix
      final double[][] correlationMatrix = new double[header.size()][header.size()];
      for(int i = 0; i < header.size(); i++) {
         for(int j = 0; j < header.size(); j++) {
            if(i == j) {
               correlationMatrix[i][j] = 1.0; // Correlation of a variable with itself is always 1
            } else if(i < j) {
               final double[] x = getColumnValues(i);
               final double[] y = getColumnValues(j);
               final double tau = Double.valueOf(df.format(computeKendallsTau(x, y)));
               correlationMatrix[i][j] = tau;
               correlationMatrix[j][i] = tau; // Correlation matrix is symmetric
            }
         }
      }

      // Add column names to the correlation table
      correlationTableModel.addColumn("");

      for(final String element : header) {
         correlationTableModel.addColumn(element);
      }

      // Add rows with correlation values
      // Add rows with correlation values and row headers
      for(int i = 0; i < header.size(); i++) {
         final Object[] rowData = new Object[header.size() + 1]; // Additional space for row header
         rowData[0] = header.get(i); // Set row header
         for(int j = 0; j < header.size(); j++) {
            rowData[j + 1] = correlationMatrix[i][j]; // Add correlation value to row data
         }
         correlationTableModel.addRow(rowData); // Add row to table model
      }
      // correlationTableModel.setColumnCount(0);
      // correlationTableModel.setRowCount(0);
      //
      // // Add columns for correlation measures
      // correlationTableModel.addColumn("Combination");
      // correlationTableModel.addColumn("Correlation statistic");
      //
      // // Compute Kendall's tau for each pair of objectives
      // for(int i = 0; i < header.size(); i++) {
      // for(int j = i + 1; j < header.size(); j++) {
      // final double[] x = getColumnValues(i);
      // final double[] y = getColumnValues(j);
      // final double tau = computeKendallsTau(x, y);
      // correlationTableModel.addRow(new Object[] { header.get(i) + " - " + header.get(j), tau });
      // }
      // }
      correlationTable.getColumnModel().getColumn(0).setCellRenderer(new GreyBackgroundRenderer());

   }

   private void updateTable() {
      tableModel.setColumnCount(0);
      tableModel.setRowCount(0);

      // Add a column for labels
      tableModel.addColumn("Chain");

      for(final String element : this.header) {
         tableModel.addColumn(element);
      }

      for(final ChainSolution chainSolution : chainSolutions) {
         final Object[] rowData = new Object[chainSolution.values.length + 1];
         rowData[0] = chainSolution.name;
         for(int j = 0; j < chainSolution.values.length; j++) {
            rowData[j + 1] = Math.round(Math.abs(chainSolution.values[j]) * 1000.0) / 1000.0;
         }
         tableModel.addRow(rowData);
      }

      // Autoscale column widths
      for(int i = 0; i < tableModel.getColumnCount(); i++) {
         final TableColumn column = table.getColumnModel().getColumn(i);
         int maxWidth = 0;
         for(int row = 0; row < table.getRowCount(); row++) {
            final TableCellRenderer cellRenderer = table.getCellRenderer(row, i);
            final Component component = table.prepareRenderer(cellRenderer, row, i);
            maxWidth = Math.max(component.getPreferredSize().width, maxWidth);
         }
         column.setPreferredWidth(maxWidth);
      }

      table.doLayout();

   }
}
