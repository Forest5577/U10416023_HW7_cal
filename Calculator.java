//U10416023 佛瑞

//import API
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//Main class
public class Calculator extends Application {

	//Declare boolean and operator to make sure the calculator can be execute
	static Operator currentOperator;
	static boolean operatorSelected;
	static boolean resultDisplayed;
	static boolean isPM = false;

	//create main method
	public static void main(String[] args) {
		launch(args);
	}

	//Override the method
	@Override
	public void start(Stage stage) {
		BorderPane layout = new BorderPane();
		//show the display area
		TextField auxiliary = new TextField();
		auxiliary.setStyle("-fx-font-size: 15; -fx-text-fill: gray");
		auxiliary.setMaxWidth(415); // 415 = total width, including margins of buttons
		auxiliary.setEditable(false);

		TextField result = new TextField();
		result.setStyle("-fx-font-size: 40");
		result.setMaxWidth(415);
		result.setEditable(false);

		VBox resultLayout = new VBox();
		resultLayout.getChildren().addAll(auxiliary, result);
		layout.setTop(resultLayout);

		// set the buttons of calculator
		GridPane buttonLayout = new GridPane();
		buttonLayout.setPadding(new Insets(10, 0, 0, 0));
		buttonLayout.setHgap(5);
		buttonLayout.setVgap(5);
		layout.setCenter(buttonLayout);

		//set DEL button
		Button backButton = new Button("DEL");
		backButton.setMinSize(100, 100);
		backButton.setOnAction(e -> {
			// add listener for del
			String currentText = result.getText();
			if (!currentText.isEmpty() && !resultDisplayed){
				result.setText(currentText.substring(0, currentText.length() - 1));
			}
		});
		buttonLayout.add(backButton, 1, 0);

		//set the button C to get back to the initial condition
		Button clearButton = new Button("C");
		clearButton.setMinSize(100, 100);
		clearButton.setOnAction(e -> {
			//add listener for C
			result.clear();
			auxiliary.clear();
			isPM = false;
			operatorSelected = false;
		});
		GridPane.setColumnSpan(clearButton, 1);
		buttonLayout.add(clearButton, 0, 0);

		//create the +/- button
		Button pmButton = new Button("+/-");
		pmButton.setMinSize(100, 100);
		pmButton.setOnAction(e -> {
			//add listener for this button
			if(isNumeric(result.getText())){
				double d = (-1)*Double.parseDouble(result.getText());
				result.clear();
				result.setText(String.valueOf(d));
				operatorSelected = false;
			}
			else{
				// deal the exception
				result.clear();
				result.setText("Error");
				auxiliary.clear();
				operatorSelected = false;
			}
			if(!isPM){
				isPM = true;
			}else{

				isPM = false;
			}
		});
		GridPane.setColumnSpan(pmButton, 1);
		buttonLayout.add(pmButton, 2, 0);

		//set buttons from 1 to 10 and add listeners
		Button[] numberButtons = new Button[10];
		for (int i = 3, target = 1; i >= 1; i--) {
			for (int j = 0; j <= 2; j++) {
				String number = Integer.toString(target);
				numberButtons[target] = new Button(number);
				numberButtons[target].setMinSize(100, 100);
				numberButtons[target].setOnAction(e -> {
					//add listener
					if (resultDisplayed) {
						result.setText(number);
						resultDisplayed = false;
					}
					else{
						result.appendText(number);
					}
					operatorSelected = false;
				});
				buttonLayout.add(numberButtons[target++], j, i);
			}
		}

		//add button and listener
		numberButtons[0] = new Button("0");
		numberButtons[0].setMinSize(200, 100);
		numberButtons[0].setOnAction(e -> {
			if (!result.getText().isEmpty() && !resultDisplayed) {
				result.appendText("0");
				operatorSelected = false;
			}
		});
		GridPane.setColumnSpan(numberButtons[0], 2);
		buttonLayout.add(numberButtons[0], 0, 4);

		//add . button
		Button decimalButton = new Button(".");
		decimalButton.setMinSize(100, 100);
		decimalButton.setOnAction(e -> {
			//determine whether the . is already in the text field
			if (result.getText().indexOf('.') == -1) {
				result.appendText(".");
			}
		});
		buttonLayout.add(decimalButton, 2, 4);

		//add operator signs
		for (Operator op : Operator.values()) {
			String symbol = op.toString();
			Button button = new Button(symbol);
			button.setMinSize(100, 100);
			button.setOnAction(e -> {
				//add listener to deal with the auxiliary
				if (auxiliary.getText().isEmpty()) {
					auxiliary.setText(result.getText().isEmpty() ? "0" : acquireValue(result.getText()));
					auxiliary.appendText(" " + symbol);
					currentOperator = op;
					resultDisplayed = true;
					operatorSelected = true;
				} else if (operatorSelected) {
					currentOperator = op;
					int end = auxiliary.getText().length();
					auxiliary.replaceText(end - 1, end,  symbol);
				} else {
					auxiliary.setText(calculate(currentOperator, result, auxiliary) + " " + symbol);
					result.clear();
					currentOperator = op;
					resultDisplayed = true;
					operatorSelected = true;
				}
			});
			buttonLayout.addColumn(3, button);
		}

		// add button  =
		Button equalsButton = new Button("=");
		equalsButton.setMinSize(100, 100);
		equalsButton.setOnAction(e -> {
			//add listener
			if (!auxiliary.getText().isEmpty()) {
					result.setText(calculate(currentOperator, result, auxiliary));
				}
				isPM = false;
				resultDisplayed = true;
				operatorSelected = false;
				auxiliary.clear();

		});
		buttonLayout.addColumn(3, equalsButton);
		equalsButton.setDefaultButton(true);

		Scene scene = new Scene(layout);
		//create the stage for calculator
		stage.setScene(scene);
		stage.setResizable(false);
		stage.sizeToScene();
		stage.setTitle("U10416023_Calculator");
		stage.show();
	}

	//calculate the numbers
	private static String calculate(Operator op, TextField main, TextField auxiliary) {
		double val1 = Double.parseDouble(auxiliary.getText().replaceAll("[^\\.0-9]", ""));
		double val2 = Double.parseDouble(main.getText());
		//judge the +/-
		if(isPM){
			val1 = (-1)*val1;
		}

		if (val2 == 0 && op == Operator.DIVIDE) {
			return "Cannot divide by 0";
		}

		double result = op.compute(val1, val2);
		return toCalculatorString(result);
	}

	//modify the number
	private static String acquireValue(String val) {
		double result = Double.parseDouble(val);
		return toCalculatorString(result);
	}

	//remove decimal trailing zeroes
	private static String removeDecimalTrailingZeroes(String s) {
		return s.indexOf(".") < 0 ? s : s.replaceAll("0*$", "").replaceAll("\\.$", "");
	}

	//deal with int and double
	private static String toCalculatorString(double input) {
		return input == (int)input ?
				Integer.toString((int)input) : removeDecimalTrailingZeroes(String.format("%.6f", input));
	}

	//judge whether the number in the text field is a number
	public static boolean isNumeric(String str){
		try	{
			double d = Double.parseDouble(str);
		}
		catch(NumberFormatException nfe){
			return false;
		}
		return true;
	}
}
