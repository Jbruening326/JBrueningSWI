package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import bruening.jbrueningc482.InventoryApplication;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static bruening.jbrueningc482.InventoryApplication.errorMessage;
import static java.lang.Integer.parseInt;


/**
 * This class is a controller class for the 'ModifyProduct.fxml' file.
 */
public class ModifyProductController implements Initializable {
    /**
     * Button object created for add button
     */
    public Button addButton;

    /**
     * Button object created for remove button
     */
    public Button removeAssPart;

    /**
     * Button object created for save button
     */
    public Button saveButton;

    /**
     * Button object created for cancel button
     */
    public Button cancelButton;


    /**
     * TableView object created for all parts table
     */
    public TableView<Part> listAllPartsTable;

    /**
     * TableColumn object created for IdCol
     */
    public TableColumn<Inventory, Integer> allPartIdCol;

    /**
     * TableColumn object created for NameCol
     */
    public TableColumn<Inventory, String> allPartNameCol;

    /**
     * TableColumn object created for InvCol
     */
    public TableColumn<Inventory, Integer> allPartInvCol;

    /**
     * TableColumn object created for PriceCol
     */
    public TableColumn<Inventory, Double> allPartPriceCol;

    /**
     * TableView object created for associated parts table
     */
    public TableView<Part> associatedPartsTable;

    /**
     * TableColumn object created for IdCol
     */
    public TableColumn<Inventory, Integer> assPartIdCol;

    /**
     * TableColumn object created for NameCol
     */
    public TableColumn<Inventory, String> assPartNameCol;

    /**
     * TableColumn object created for InvCol
     */
    public TableColumn<Inventory, Integer> assPartInvCol;

    /**
     * TableColumn object created for PriceCol
     */
    public TableColumn<Inventory, Double> assPartPriceCol;

    /**
     * TextField object created for search field
     */
    public TextField searchPartTextField;

    /**
     * TextField object created for id field
     */
    public TextField idTextField;

    /**
     * TextField object created for name field
     */
    public TextField nameTextField;

    /**
     * TextField object created for inv field
     */
    public TextField invTextField;

    /**
     * TextField object created for price field
     */
    public TextField priceTextField;

    /**
     * TextField object created for max field
     */
    public TextField maxTextField;

    /**
     * TextField object created for min field
     */
    public TextField minTextField;


    /**
     * Stage object created to set stage for application window
     */
    Stage stage;




    /**
     * This method initializes the application screen. When the screen is loaded, values will be initially populated into table views.
     * @param url
     * @param resourceBundle
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listAllPartsTable.setItems(Inventory.getAllParts());
        allPartIdCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        allPartNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        allPartInvCol.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        allPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));

    }

    /**
     * This method shares data across the application. This method will send part data from one controller to another.
     * @param product
     */
    public void sendProduct(Product product){

        idTextField.setText(String.valueOf(product.getId()));
        nameTextField.setText(product.getName());
        invTextField.setText(String.valueOf(product.getStock()));
        priceTextField.setText(String.valueOf(product.getPrice()));
        maxTextField.setText(String.valueOf(product.getMax()));
        minTextField.setText(String.valueOf(product.getMin()));


        associatedPartsTable.setItems(product.getAllAssociatedParts());
        assPartIdCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        assPartNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        assPartInvCol.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        assPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
    }


    /**
     * This method will add a part to the associated parts table. When the user interacts with the 'add' button, a selected part from the table with all parts
     * will be copied and added to the table of associated parts.
     * @param actionEvent
     * @throws IOException
     */
    public void onAddButtonClick(ActionEvent actionEvent) throws IOException {
        Part selectedPart = listAllPartsTable.getSelectionModel().getSelectedItem();

        if(selectedPart != null){
        associatedPartsTable.getItems().add(selectedPart);
        }
    }

    /**
     * This method will remove a part from the associated parts table.  When the user interacts with the remove button,
     * the selected item from the table of associated parts will be removed.
     * @param actionEvent
     * @throws IOException
     */
    public void onRemoveAssPartClick(ActionEvent actionEvent) throws IOException{
        Part selectedPart = associatedPartsTable.getSelectionModel().getSelectedItem();

        if(selectedPart != null){
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove " + selectedPart.getName() +
                    "?", ButtonType.YES, ButtonType.CANCEL);
            confirmation.showAndWait();
            if(confirmation.getResult() == ButtonType.YES){
                associatedPartsTable.getItems().remove(selectedPart);
            }
        }
    }

    /**
     * This method saves the values in the text fields. When the user interacts with the save button, text will be parsed from the text fields.
     * If text fields are empty or incorrectly formatted, an error message will be displayed. If data are entered without error, a project object will be updated
     * along with the associated parts. The user will then be redirected to the main screen.
     * @param actionEvent
     * @throws IOException
     */
    public void onSaveButtonClick(ActionEvent actionEvent) throws IOException{
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
                Product product = new Product(id, name, price, stock, min, max);

                int index = -1;
                for (Product temp : Inventory.getAllProducts()){
                    if(id == temp.getId()){
                        index = Inventory.getAllProducts().indexOf(temp);
                    }
                }

                Inventory.updateProduct(index, product);
                for(Part removePart: product.getAllAssociatedParts()){
                    product.deleteAssociatePart(removePart);
                }
                for(Part addPart : associatedPartsTable.getItems()){
                    product.addAssociatePart(addPart);
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
     * This method cancels changes. When the user interacts with the cancel button, data wil not be saved and the user will redirected to the main screen.
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
     * The method searches for existing parts by ID or name. When user enters data in the search field, a partial search function will be ran.
     * If the data is present, the table will display the results. If data is not present, an error message will display.
     * @param actionEvent
     */
    public void onSearch(ActionEvent actionEvent) {
        String searchItem = searchPartTextField.getText();

        if (searchItem.isEmpty() || searchItem.isBlank()){
            listAllPartsTable.setItems(Inventory.getAllParts());
        }else {
            ObservableList foundParts = Inventory.lookupPart(searchItem);
            if(foundParts.size() == 0){
                try{
                    int idLookup = parseInt(searchItem);
                    if(Inventory.lookupPart(idLookup) != null){
                        foundParts.add(Inventory.lookupPart(idLookup));
                        listAllPartsTable.setItems(foundParts);
                    }
                }
                catch(NumberFormatException ex){
                    //do nothing
                }
            }
            else if(foundParts.size() != 0){
                try{
                    int idLookup = parseInt(searchItem);
                    if(Inventory.lookupPart(idLookup) != null) {
                        foundParts.add(Inventory.lookupPart(idLookup));
                        listAllPartsTable.setItems(foundParts);
                    }
                }
                catch(NumberFormatException ex){
                    //do nothing
                }
            }
            if(foundParts.size() == 0){
                errorMessage("Search Result", "No Part Match", "No parts match your search");
            }else {
                listAllPartsTable.setItems(foundParts);
            }
        }
    }


}
