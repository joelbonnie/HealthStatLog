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

// class to create a Scatter-Plot for fat percent progress
public class FatPercentGraph {
    HealthProgress progressList;
    JFrame fatPercentGraph;

    XYDataset dataset;

    // EFFECTS: creates a graph for fat percent progress
    public FatPercentGraph(HealthProgress progressList) {
        fatPercentGraph = new JFrame();
        this.progressList = progressList;
        fatPercentGraph.setTitle("Fat Percent Graph");
        fatPercentGraph.setSize(700,500);
        fatPercentGraph.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createGraph();
        fatPercentGraph.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds data points and displays graph
    public void createGraph() {
        dataset = loadValues();

        JFreeChart scatterChart = ChartFactory.createScatterPlot("Comparison of Fat Percent",
                "Days","Fat Percent (Percentage)",dataset);
        ChartPanel chartPanel = new ChartPanel(scatterChart);
        chartPanel.setPreferredSize(new Dimension(500,500));
        fatPercentGraph.setContentPane(chartPanel);
    }

    // MODIFIES: this
    // EFFECTS: adds the fat percent values from progressList to the dataset and returns dataset
    public XYDataset loadValues() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries newSeries = new XYSeries("Fat Percent");
        int numHealthLog = progressList.getHealthLogList().size();
        for (int healthLogCounter = 0; (healthLogCounter < 30
                && healthLogCounter < numHealthLog); healthLogCounter++) {
            HealthLog currentLog = progressList.getHealthLogList().get(healthLogCounter);
            newSeries.add(healthLogCounter,currentLog.getFatPercentage());
        }
        dataset.addSeries(newSeries);
        return dataset;

    }
}