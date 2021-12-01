package logistic.agent;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.FileReader;
import java.util.List;
import logistic.controller.*;

public class LRAgent extends Agent {
	
	private LRGui myGui;

  protected void setup() {
	  
	myGui = new LRGui(this);
	myGui.showGui();
  }
  
  // Put agent clean-up operations here
	protected void takeDown() {
		// Close the GUI
		myGui.dispose();
		// Printout a dismissal message
		System.out.println("LR-agent " + getAID().getName() + " terminating.");
	}
  
    public void simpleLR(String input) {
		addBehaviour(new OneShotBehaviour() {
			public void action() {
				double[][] dataSet = null;
				String[] userRawValues;
				

				try {
					FileReader filereader = new FileReader("diabetes.csv");
					CSVReader csvReader = new CSVReaderBuilder(filereader).build();
					List<String[]> csvData = csvReader.readAll();

					int pivotForIndex = csvData.get(0).length;

					dataSet = new double[csvData.size()][pivotForIndex];
					for (int i = 0 ; i < csvData.size(); i++){
						for (int j=0;j<pivotForIndex;j++){
							String[] row = csvData.get(i);
							dataSet[i][j] = Double.parseDouble(row[j]);
						}

					}

				}
				catch (Exception e) {
					e.printStackTrace();
				}

				userRawValues = input.split(",");

				double [] inputs = new double [userRawValues.length];

				for (int i = 0; i < userRawValues.length; i++) {
					inputs[i] = Double.parseDouble(userRawValues[i]);
				}

				LogisticRegression lr = new LogisticRegression();

				lr.setData(dataSet);
				System.out.println(lr.classify(inputs));
		
			}
		} );
	}
	
	public void multipleLR(String input) {
		addBehaviour(new OneShotBehaviour() {
			public void action() {
				double[][] dataSet = null;
				String[] userRawValues;
				

				try {
					FileReader filereader = new FileReader("basquet.csv");
					CSVReader csvReader = new CSVReaderBuilder(filereader).build();
					List<String[]> csvData = csvReader.readAll();

					int pivotForIndex = csvData.get(0).length;

					dataSet = new double[csvData.size()][pivotForIndex];
					for (int i = 0 ; i < csvData.size(); i++){
						for (int j=0;j<pivotForIndex;j++){
							String[] row = csvData.get(i);
							dataSet[i][j] = Double.parseDouble(row[j]);
						}

					}

				}
				catch (Exception e) {
					e.printStackTrace();
				}

				userRawValues = input.split(",");

				double [] inputs = new double [userRawValues.length];

				for (int i = 0; i < userRawValues.length; i++) {
					inputs[i] = Double.parseDouble(userRawValues[i]);
				}

				LogisticRegression lr = new LogisticRegression();

				lr.setData(dataSet);
				System.out.println(lr.classify(inputs));
		
			}
		} );
	}
}
