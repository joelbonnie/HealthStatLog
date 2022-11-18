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

// class to create a Scatter-Plot for body mass progress
public class BodyMassGraph {
    HealthProgress progressList;
    JFrame bodyMassGraph;

    XYDataset dataset;

    // EFFECTS: creates a graph for body mass progress
    public BodyMassGraph(HealthProgress progressList) {
        bodyMassGraph = new JFrame();
        this.progressList = progressList;
        bodyMassGraph.setTitle("Body Mass Graph");
        bodyMassGraph.setSize(700,500);
        bodyMassGraph.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createGraph();
        bodyMassGraph.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds data points and displays graph
    public void createGraph() {
        dataset = loadValues();

        JFreeChart scatterChart = ChartFactory.createScatterPlot("Comparison of Body Mass",
                "Days","Body Mass (kg)",dataset);
        ChartPanel chartPanel = new ChartPanel(scatterChart);
        chartPanel.setPreferredSize(new Dimension(500,500));
        bodyMassGraph.setContentPane(chartPanel);
    }

    // MODIFIES: this
    // EFFECTS: adds the body mass values from progressList to the dataset and returns dataset
    public XYDataset loadValues() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries newSeries = new XYSeries("Body Mass");
        int numHealthLog = progressList.getHealthLogList().size();
        for (int healthLogCounter = 0; (healthLogCounter < 30
                && healthLogCounter < numHealthLog); healthLogCounter++) {
            HealthLog currentLog = progressList.getHealthLogList().get(healthLogCounter);
            newSeries.add(healthLogCounter,currentLog.getBodyMass());
        }
        dataset.addSeries(newSeries);
        return dataset;

    }
}
