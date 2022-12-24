package Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import bruening.jbrueningc482.InventoryApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static Model.Inventory.getAllParts;
import static bruening.jbrueningc482.InventoryApplication.errorMessage;

/**
 * This class is a controller class for the 'AddPart.fxml' file.
 */
public class AddPartController {

    /**
     * Creates a Button object for a cancel button
     */
    public Button cancelButton;

    /**
     * Creates a button object for a save button
     */
    public Button saveButton;

    /**
     * Creates a text field object for ID
     */
    public TextField idTextField;

    /**
     * Creates a text field object for name
     */
    public TextField nameTextField;

    /**
     * Creates a text field object for stock
     */
    public TextField invTextField;

    /**
     * Creates a text field object for price
     */
    public TextField priceTextField;

    /**
     * Creates a text field object for min
     */
    public TextField minTextField;

    /**
     * Creates a text field object for max
     */
    public TextField maxTextField;

    /**
     * Creates a text field object for machine ID
     */
    public TextField machineIdTextField;

    /**
     * Creates a RadioButton object for In-House radio button
     */
    public RadioButton inHouseRadio;

    /**
     * Creates a RadioButton object for outsourced radio button
     */
    public RadioButton outsourcedRadio;

    /**
     * Creates a label object on machine ID label
     */
    public Label machineIdLabel;
    Stage stage;


    /**
     * This method obtains and ID. The ID is obtained by obtaining the ID of the last item in the index and adding 1
     * to it in order to uniquely assign values to items.
     * @return Returns ID of the last index of a list and adds 1 to it.
     */
    public static int newId(){
        return (getAllParts().get(getAllParts().size()-1).getId()) +1;
    }

    /**
     * This method saves changes. When the save button is clicked, the method will obtain the data from the fields and create a new part.
     * If data are missing or incorrectly formatted, an error message will display. If the values are entered correctly,
     * The user will be redirected to the main menu.
     * @param actionEvent
     * @throws IOException
     */
    public void onSaveButtonClick(ActionEvent actionEvent) throws IOException{
        try {
            String name = nameTextField.getText();
            int stock = Integer.parseInt(invTextField.getText());
            double price = Double.parseDouble(priceTextField.getText());
            int max = Integer.parseInt(maxTextField.getText());
            int min = Integer.parseInt(minTextField.getText());

            if (min > max) {
                errorMessage("Error", "Correct min and max", "Min must be less than Max");
            } else if (stock > max || stock < min) {
                errorMessage("Error", "Stock Error", "Inv must be equal to or between min and max");
            } else {
                if (machineIdLabel.getText().equals("Machine ID")) {
                    int machineId = Integer.parseInt(machineIdTextField.getText());
                    Inventory.addPart(new InHouse(newId(), name, price, stock, min, max, machineId));
                } else {
                    String companyName = machineIdTextField.getText();
                    Inventory.addPart(new Outsourced(newId(), name, price, stock, min, max, companyName));
                }
                FXMLLoader fxmlLoader = new FXMLLoader(InventoryApplication.class.getResource("MainMenu.fxml"));
                stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(fxmlLoader.load(), 1000, 365);
                stage.setTitle("Inventory Application");
                stage.setScene(scene);
                stage.show();
            }
        } catch(Exception e){
            errorMessage("Error", "Other Error", "All fields must have a value and must be the correct value type ");
        }
    }

    /**
     * This method cancels adding a part. The user clicks on the cancel button, no data is stored and the user will be redirected to the main menu.
     * @param actionEvent
     * @throws IOException
     */
    public void onCancelButtonClick(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(InventoryApplication.class.getResource("MainMenu.fxml"));
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1000, 365);
        stage.setTitle("Inventory Application");
        stage.setScene (scene);
        stage.show();
    }

    /**
     * This method changes the value of a label. When the user clicks on the 'In House' radio button, the 'Machine ID' label will be displayed.
     * @param actionEvent
     */
    public void onInHouseRadio(ActionEvent actionEvent) {
        machineIdLabel.setText("Machine ID");
    }

    /**
     * This method changes the value of a label. When the user clicks on the 'Outsourced' radio button, the 'Company Name' label will be displayed.
     * @param actionEvent
     */
    public void onOutsourcedRadio(ActionEvent actionEvent) {
        machineIdLabel.setText("Company Name");
    }
}
