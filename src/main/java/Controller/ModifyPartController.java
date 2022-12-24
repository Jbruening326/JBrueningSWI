package Controller;

import Model.*;
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

import static bruening.jbrueningc482.InventoryApplication.errorMessage;

/**
 * This class is a controller class for the 'ModifyPart.fxml' file.
 */
public class ModifyPartController {

    /**
     * Button object created for save button
     */
    public Button saveButton;

    /**
     * Button object created for cancel button
     */
    public Button cancelButton;

    /**
     * RadioButton object created for inHouse radio button
     */
    public RadioButton inHouseRadio;

    /**
     * RadioButton object created for outSourced radio button
     */
    public RadioButton outSourcedRadio;

    /**
     * TextField object created for id
     */
    public TextField idTextField;

    /**
     * TextField object created for name
     */
    public TextField nameTextField;

    /**
     * TextField object created for inv
     */
    public TextField invTextField;

    /**
     * TextField object created for price
     */
    public TextField priceTextField;

    /**
     * TextField object created for max
     */
    public TextField maxTextField;

    /**
     * TextField object created for min
     */
    public TextField minTextField;

    /**
     * TextField object created for machineId
     */
    public TextField machineIdTextField;

    /**
     * Label object created for machineId label
     */
    public Label machineIdLabel;


    /**
     * Stage object created to set stage for an application window
     */
    Stage stage;


    /**
     * This method shares data across the application. This method will send part data from one controller to another.
     * @param part
     */
    public void sendPart(Part part){

        idTextField.setText(String.valueOf(part.getId()));
        nameTextField.setText(part.getName());
        invTextField.setText(String.valueOf(part.getStock()));
        priceTextField.setText(String.valueOf(part.getPrice()));
        maxTextField.setText(String.valueOf(part.getMax()));
        minTextField.setText(String.valueOf(part.getMin()));

        if(part instanceof InHouse ) {
            machineIdTextField.setText(String.valueOf(((InHouse) part).getMachineId()));
        }else{
            outSourcedRadio.fire();
            machineIdTextField.setText(part.getName());

        }

    }

    /** RUNTIME ERROR. Two runtime errors occurred on this method. One was a thrown number format exception. To correct this,
     * I added  try and catch clauses where the catch statement displays a general message if the user put text in the incorrect format.
     * The second runtime error was a concurrent modification exception being thrown. The error was caused by the use of an enhanced for loop
     * accessing and modifying items through an observable list. To correct this, I changed the loop to a standard for loop.*/
    public void onSaveClick(ActionEvent actionEvent) throws IOException {
        try {
            int id = Integer.parseInt(idTextField.getText());
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
                int index = -1;
                for (Part temp : Inventory.getAllParts()) {
                    if (id == temp.getId()) {
                        index = Inventory.getAllParts().indexOf(temp);
                    }
                }
                    if (machineIdLabel.getText().equals("Machine ID")) {
                        int machineId = Integer.parseInt(machineIdTextField.getText());
                        Part inHousePart = new InHouse(id, name,price, stock, min, max, machineId);
                        Inventory.updatePart(index,inHousePart);
                        for (int i = 0; i < Inventory.getAllProducts().size(); i++){
                            Product product = Inventory.getAllProducts().get(i);
                            for(int j = 0; j < product.getAllAssociatedParts().size(); j++){
                                Part part = product.getAllAssociatedParts().get(j);
                                if (inHousePart.getId() == part.getId()){
                                    product.deleteAssociatePart(part);
                                    product.addAssociatePart(inHousePart);
                                }
                            }
                        }
                    } else {
                        String companyName = machineIdTextField.getText();
                        Part outSourcedPart = new Outsourced(id, name, price, stock, min, max, companyName);
                        Inventory.updatePart(index, outSourcedPart);
                        for (int i = 0; i < Inventory.getAllProducts().size(); i++){
                            Product product = Inventory.getAllProducts().get(i);
                            for(int j = 0; j < product.getAllAssociatedParts().size(); j++){
                                Part part = product.getAllAssociatedParts().get(j);
                                if (outSourcedPart.getId() == part.getId()){
                                    product.deleteAssociatePart(part);
                                    product.addAssociatePart(outSourcedPart);
                                }
                            }
                        }
                    }
                    FXMLLoader fxmlLoader = new FXMLLoader(InventoryApplication.class.getResource("MainMenu.fxml"));
                    stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                    Scene scene = new Scene(fxmlLoader.load(), 1000, 365);
                    stage.setTitle("Inventory Application");
                    stage.setScene(scene);
                    stage.show();
                }
            }
        catch(NumberFormatException e){
            errorMessage("Error", "Other Error", "All fields must have a value and must be the correct value type ");
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method cancels changes made to data. When the user interacts with the 'Cancel' button, no data will be saved, and the user
     * will be redirected to the Main Menu.
     * @param actionEvent
     * @throws IOException
     */
    public void onCancelClick(ActionEvent actionEvent) throws IOException {
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
