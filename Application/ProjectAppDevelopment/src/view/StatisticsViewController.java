package view;

import application.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.MathModels;
import model.Species;

public class StatisticsViewController {
	private MainApp mainApp;
	private MathModels models;
	
	@FXML
	private TextField numYears;
	@FXML
	private ComboBox<String> modelChoice;
	@FXML
	private LineChart<Number, Number> lineChart;
    @FXML
    private NumberAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    
    private int timeRange;
    private ObservableList<String> options;
	@FXML
	private void initialize() {
		options = FXCollections.observableArrayList("Exponential Growth","Logistic Growth");
		models = new MathModels();
		timeRange = 20;
		numYears.setText(Integer.toString(timeRange));
		modelChoice.setItems(options);
		modelChoice.getSelectionModel().selectFirst();
		lineChart.setTitle("Animal Populations");
		lineChart.setCreateSymbols(false);
		
		handleShowGraph();
	}
	
	@FXML
	public void handleShowGraph() {
		lineChart.getData().clear();
		Species cow = new Species("Cow", 30, 400, 1.33, 1);

		if(!numYears.getText().equals("")) {
			timeRange = Integer.parseInt(numYears.getText().toString());
		}

        lineChart.getData().add(models.basicLogisticGrowth(cow, timeRange));
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}
