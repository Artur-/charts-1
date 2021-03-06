package com.vaadin.addon.charts.examples.container;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.ChartDataSeries;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.server.data.DataSource;
import com.vaadin.server.data.ListDataSource;
import com.vaadin.ui.Component;

@SkipFromDemo
public class BeansFromContainer extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Simple Chart with BeanItemContainer";
    }

    @Override
    protected Component getChart() {
        DataSource<ClaimsReportItem> ds = new ListDataSource<>(getMockData());


        // Create ChartDataSeries
        ChartDataSeries<ClaimsReportItem> series = new ChartDataSeries(ds);
        series.setName("Claims");
        series.setPlotOptions(new PlotOptionsColumn());
        series.setYValueProvider(ClaimsReportItem::getAmount);
        series.setNameProvider(ClaimsReportItem::getType);

        // Create chart & configuration
        Chart chart = new Chart();
        Configuration configuration = chart.getConfiguration();
        configuration.getTitle().setText("Claim Processing");

        // Create Y Axis
        YAxis y = new YAxis();
        y.setTitle("Amount");
        configuration.addyAxis(y);

        // use category names from beans on x axis instead of index
        configuration.getxAxis().setType(AxisType.CATEGORY);
        configuration.setSeries(series);

        chart.setSizeFull();

        return chart;
    }
    private Collection<ClaimsReportItem> getMockData(){
        Collection<ClaimsReportItem> col = new ArrayList<>();
        col.add(new ClaimsReportItem("manual", 100));
        col.add(new ClaimsReportItem("automatic", 600));
        return col;
    }
    private class ClaimsReportItem implements Serializable {
        private String type;
        private Integer amount;

        public ClaimsReportItem(String type, Integer amount) {
            this.type = type;
            this.amount = amount;

        }

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

}
