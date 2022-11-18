package ui.graphs;

import model.HealthLog;
import model.HealthProgress;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

/*
REFERENCES:
JFreeChart - Scatter Chart Tutorial
https://www.javatpoint.com/jfreechart-scatter-chart

CodeJava
https://www.codejava.net/java-se/graphics/using-jfreechart-to-draw-xy-line-chart-with-xydataset

 */

// class to create a Scatter-Plot for water glass progress
public class WaterGlassesGraph {
    HealthProgress progressList;
    JFrame waterGlassesGraph;

    XYDataset dataset;

    // EFFECTS: creates a graph for water glasses progress
    public WaterGlassesGraph(HealthProgress progressList) {
        waterGlassesGraph = new JFrame();
        this.progressList = progressList;
        waterGlassesGraph.setTitle("Water Glasses Graph");
        waterGlassesGraph.setSize(700,500);
        waterGlassesGraph.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createGraph();
        waterGlassesGraph.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds data points and displays graph
    public void createGraph() {
        dataset = loadValues();

        JFreeChart scatterChart = ChartFactory.createScatterPlot("Comparison of Water Glasses Drank",
                "Days","Number of Water Glasses Drank",dataset);
        ChartPanel chartPanel = new ChartPanel(scatterChart);
        chartPanel.setPreferredSize(new Dimension(500,500));
        waterGlassesGraph.setContentPane(chartPanel);
    }

    // MODIFIES: this
    // EFFECTS: adds the water glasses values from progressList to the dataset and returns dataset
    public XYDataset loadValues() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries newSeries = new XYSeries("Water Glasses");
        int numHealthLog = progressList.getHealthLogList().size();
        for (int healthLogCounter = 0; (healthLogCounter < 7
                && healthLogCounter < numHealthLog); healthLogCounter++) {
            HealthLog currentLog = progressList.getHealthLogList().get(healthLogCounter);
            newSeries.add(healthLogCounter,currentLog.getWaterGlassesDrank());
        }
        dataset.addSeries(newSeries);
        return dataset;

    }
}
