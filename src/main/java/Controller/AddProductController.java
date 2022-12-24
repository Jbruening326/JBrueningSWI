package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import bruening.jbrueningc482.InventoryApplication;
import javafx.collections.FXCollections;
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

import static Model.Inventory.getAllProducts;
import static bruening.jbrueningc482.InventoryApplication.errorMessage;
import static java.lang.Integer.parseInt;


/**
 * This class is a controller class for the 'AddProduct.fxml' file.
 */
public class AddProductController implements Initializable {

    /**
     * Button object is created for an add button
     */
    public Button addButton;

    /**
     * Button object is created for a remove button
     */
    public Button removeAssPartButton;

    /**
     * Button object is created for a save button
     */
    public Button saveButton;

    /**
     * Button object is created for a cancel button
     */
    public Button cancelButton;

    /**
     * A TableView Object is created for parts
     */
    public TableView<Part> listAllPartsTable;

    /**
     *  TableColumn object is created for an IdCol
     */
    public TableColumn<Inventory, Integer> allPartIdCol;

    /**
     *  TableColumn object is created for an NameCol
     */
    public TableColumn<Inventory, String> allPartNameCol;

    /**
     *  TableColumn object is created for an InvCol
     */
    public TableColumn<Inventory, Integer> allPartInvCol;

    /**
     *  TableColumn object is created for an PriceCol
     */
    public TableColumn<Inventory, Double> allPartPriceCol;

    /**
     * A TableView Object is created for parts
     */
    public TableView<Part> associatedPartsTable;

    /**
     * TableColumn object is created for an IdCol
     */
    public TableColumn<Inventory, Integer> assPartIdCol;

    /**
     * TableColumn object is created for an NameCol
     */
    public TableColumn<Inventory, String > assPartNameCol;

    /**
     * TableColumn object is created for an InvCol
     */
    public TableColumn<Inventory, Integer> assPartInvCol;

    /**
     * TableColumn object is created for an PriceCol
     */
    public TableColumn<Inventory, Double> assPartPriceCol;

    /**
     * TextField objects is created for id
     */
    public TextField idTextField;

    /**
     * TextField objects is created for name
     */
    public TextField nameTextField;

    /**
     * TextField objects is created for inv
     */
    public TextField invTextField;

    /**
     * TextField objects is created for price
     */
    public TextField priceTextField;

    /**
     * TextField objects is created for max
     */
    public TextField maxTextField;

    /**
     * TextField objects is created for min
     */
    public TextField minTextField;

    /**
     * TextField objects is created for search
     */
    public TextField searchPartTextField;

    /**
     * Stage object is created to set stage for application page
     */
    Stage stage;

    //List for associated parts that will populate the table view
    private ObservableList<Part> assPartsList = FXCollections.observableArrayList();


    /**
     * This method initializes the application screen. When the screen is loaded, values will be initially populated into table views.
     * @param url
     * @param resourceBundle
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listAllPartsTable.setItems(Inventory.getAllParts());
        allPartIdCol.setCellValueFactory( new PropertyValueFactory<>("Id"));
        allPartNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        allPartInvCol.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        allPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));


        associatedPartsTable.setItems(assPartsList);
        assPartIdCol.setCellValueFactory( new PropertyValueFactory<>("Id"));
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
            assPartsList.add(selectedPart);

        }
    }

    /**
     * This method will remove a part from the associated parts table.  When the user interacts with the remove button,
     * the selected item from the table of associated parts will be removed.
     * @param actionEvent
     * @throws IOException
     */
    public void onRemoveAssPartButtonClick(ActionEvent actionEvent) throws IOException{
        Part selectedPart = associatedPartsTable.getSelectionModel().getSelectedItem();

        if(selectedPart != null){
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove " + selectedPart.getName() +
                    "?", ButtonType.YES, ButtonType.CANCEL);
            confirmation.showAndWait();
            if(confirmation.getResult() == ButtonType.YES){
                assPartsList.remove(selectedPart);
            }
        }
    }

    /**
     * This method obtains and ID. The ID is obtained by obtaining the ID of the last item in the index and adding 1
     * to it in order to uniquely assign values to items.
     * @return Returns ID of the last index of a list and adds 1 to it.
     */
    public static int newId(){
        return (getAllProducts().get(getAllProducts().size()-1).getId()) +1;
    }


    /**
     * This method saves the values in the text fields. When the user interacts with the save button, text will be parsed from the text fields.
     * If text fields are empty or incorrectly formatted, an error message will be displayed. If data are entered without error, a new product object
     * will be made and an associated parts list will be created for said object. The user will then be redirected to the main screen.
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
                Product product = new Product(newId(), name, price, stock, min, max);
                Inventory.addProduct(product);

                for(Part temp : assPartsList){
                    product.addAssociatePart(temp);
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
