package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import bruening.jbrueningc482.InventoryApplication;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
 * This class is the main controller for the application.
 */
public class MainMenuController implements Initializable {

    /**
     * A TableView object is created for parts table
     */
    public TableView<Part> partsTable;

    /**
     * TableColumn object is created for IdCol
     */
    public TableColumn<Inventory, Integer> partIdCol;

    /**
     * TableColumn object is created for NameCol
     */
    public TableColumn<Inventory, String> partNameCol;

    /**
     * TableColumn object is created for inventoryLevelCol
     */
    public TableColumn<Inventory, Integer> inventoryLevelCol;

    /**
     * TableColumn object is created for PricePerUnitCol
     */
    public TableColumn<Inventory, Double> partPricePerUnitCol;

    /**
     * A TableView object is created for products table
     */
    public TableView <Product> productsTable;

    /**
     * TableColumn object is created for IdCol
     */
    public TableColumn<Inventory, Integer> productIdCol;

    /**
     * TableColumn object is created for NameCol
     */
    public TableColumn<Inventory, String> productNameCol;

    /**
     * TableColumn object is created for InventoryLevelCol
     */
    public TableColumn<Inventory, Integer> productInventoryLevelCol;

    /**
     * TableColumn object is created for PricePerUnitCol
     */
    public TableColumn<Inventory, Double> productPricePerUnitCol;


    /**
     * Button object created for add button
     */
    public Button partsAddButton;

    /**
     * Button object created for modify button
     */
    public Button partsModifyButton;

    /**
     * Button object created for delete button
     */
    public Button partsDeleteButton;

    /**
     * Button object created for add button
     */
    public Button productsAddButton;

    /**
     * Button object created for modify button
     */
    public Button productsModifyButton;

    /**
     * Button object created for delete button
     */
    public Button productsDeleteButton;

    /**
     * Button object created for exit button
     */
    public Button exitButton;

    /**
     * TextField object created for search field
     */
    public TextField partsSearchField;

    /**
     * TextField object created for search field
     */
    public TextField productsSearchField;

    /**
     * Stage object created to set stage for application window
     */
    Stage stage;


     /**
     * This method initializes the application screen. When the screen is loaded, values will be initially populated into table views.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsTable.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory( new PropertyValueFactory<>("Id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        inventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        partPricePerUnitCol.setCellValueFactory(new PropertyValueFactory<>("Price"));


        productsTable.setItems(Inventory.getAllProducts());
        productIdCol.setCellValueFactory( new PropertyValueFactory<>("Id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        productInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        productPricePerUnitCol.setCellValueFactory(new PropertyValueFactory<>("Price"));

    }

    /**
     * This method will launch a new window. When the user interacts with the 'add part' button, the user will be redirected to the Add Part window.
     * @param actionEvent
     * @throws IOException
     */
    public void onAddPartsButtonClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InventoryApplication.class.getResource("AddPart.fxml"));
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 600, 550);
        stage.setTitle("Add Part");
        stage.setScene (scene);
        stage.show();
    }

    /**
     * This method will launch a new window. When the user interacts with the 'modify part' button, the user will be redirected to the Modify Part window.
     * @param actionEvent
     * @throws IOException
     */
    public void onModifyPartsButtonClick (ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(InventoryApplication.class.getResource("ModifyPart.fxml"));
            loader.load();


            ModifyPartController MPController = loader.getController();
            MPController.sendPart(partsTable.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch(NullPointerException e){
            errorMessage("Error", "No selection made", "Please select a part to modify");
        }

    }


    /**
     * This method will remove a part. When the user interacts with the 'Delete' button in the parts pane, the selected part will be deleted.
     * The part will NOT be removed from products with the associated part as a products associated parts can exist independent of current inventory of parts
     * @param actionEvent
     * @throws IOException
     */
    public void onDeletePartsButtonClick(ActionEvent actionEvent) throws IOException {
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        if(selectedPart != null){
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove " + selectedPart.getName() +
                    "?", ButtonType.YES, ButtonType.CANCEL);
            confirmation.showAndWait();
            if(confirmation.getResult() == ButtonType.YES){
                Inventory.deletePart(selectedPart);

                //Add statements below if Products Associated parts cannot exits independent of the current inventory of parts
                /*for (int i = 0; i < Inventory.getAllProducts().size(); i++){
                    Product product = Inventory.getAllProducts().get(i);
                    for(int j = 0; j < product.getAllAssociatedParts().size(); j++){
                        Part part = product.getAllAssociatedParts().get(j);
                        if (selectedPart.getId() == part.getId()){
                            product.deleteAssociatePart(part);
                        }
                    }
                }*/

            }
        }
    }

    /**
     * This method will launch a new window. When the user interacts with the  'Add' button on the products pane,the Add Product window will be launched.
     */
    public void onAddProductsButtonClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InventoryApplication.class.getResource("AddProduct.fxml"));
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 900, 800);
        stage.setTitle("Add Product");
        stage.setScene (scene);
        stage.show();
    }


    /**
     * This method launches a new window. When the user interacts with the  'Modify' button on the products pane, the Modify Product window will be launched.
     * @param actionEvent
     * @throws IOException
     */
    public void onModifyProductsButtonClick(ActionEvent actionEvent) throws IOException{
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(InventoryApplication.class.getResource("ModifyProduct.fxml"));
            loader.load();


            ModifyProductController MPController = loader.getController();
            MPController.sendProduct(productsTable.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch(NullPointerException e){
            errorMessage("Error", "No selection made", "Please select a product to modify");
        }
    }


    /**
     * This method will delete a product. When the user interacts with the 'Delete' button on the products pane, the selected product will be
     * deleted. If the product contains associated parts, an error message will be displayed.
     * @param actionEvent
     */
    public void onDeleteProductsButtonClick(ActionEvent actionEvent) {
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        if(selectedProduct != null){
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove "
                    + selectedProduct.getName() + "?", ButtonType.YES, ButtonType.CANCEL);
            confirmation.showAndWait();
            if(confirmation.getResult() == ButtonType.YES){
                if(selectedProduct.getAllAssociatedParts().size() > 0){
                    errorMessage("Error", "Cannot Delete", "This product contains associated parts" +
                            " that must be removed.");
                }else{
                    Inventory.deleteProduct(selectedProduct);
                }
            }
        }
    }

    /**
     * This method will stop the program. When the user interacts with the 'Exit' button, the application will be closed.
     * @param actionEvent
     */
    public void onExitButtonClick(ActionEvent actionEvent) {
        System.exit(0);
    }

    /**
     * The method searches for existing parts by ID or name. When user enters data in the search field, a partial search function will be ran.
     * If the data is present, the table will display the results. If data is not present, an error message will display.
     * @param actionEvent
     */
    public void onPartNameOrIdSearch(ActionEvent actionEvent) {
        String searchItem = partsSearchField.getText();

        if (searchItem.isEmpty() || searchItem.isBlank()){
            partsTable.setItems(Inventory.getAllParts());
        }else {
            ObservableList foundParts = Inventory.lookupPart(searchItem);
            try {
                if (foundParts.size() == 0) {
                    int idLookup = parseInt(searchItem);
                    if (Inventory.lookupPart(idLookup) != null) {
                        foundParts.add(Inventory.lookupPart(idLookup));
                        partsTable.setItems(foundParts);
                    }
                } else if (foundParts.size() != 0) {
                    int idLookup = parseInt(searchItem);
                    if (Inventory.lookupPart(idLookup) != null) {
                        foundParts.add(Inventory.lookupPart(idLookup));
                        partsTable.setItems(foundParts);
                    }
                }
            }
            catch(NumberFormatException ex){
            }
            if(foundParts.size() == 0){
                errorMessage("Search Result", "No Part Match", "No parts match your search");
            }else {
                partsTable.setItems(foundParts);
            }
        }
    }

    /**
     * The method searches for existing products by ID or name. When user enters data in the search field, a partial search function will be ran.
     * If the data match, the table will display the results. If not, an error message will display.
     * @param actionEvent
     */
    public void onProductNameOrIdSearch(ActionEvent actionEvent) {
        String searchItem = productsSearchField.getText();

        if (searchItem.isEmpty() || searchItem.isBlank()){
            productsTable.setItems(Inventory.getAllProducts());
        }else {
            ObservableList foundProducts = Inventory.lookupProduct(searchItem);
            if(foundProducts.size() == 0){
                try{
                    int idLookup = parseInt(searchItem);
                    if(Inventory.lookupProduct(idLookup) != null){
                        foundProducts.add(Inventory.lookupProduct(idLookup));
                        productsTable.setItems(foundProducts);
                    }
                }
                catch(NumberFormatException ex){
                    //do nothing
                }
            }
            else if(foundProducts.size() != 0){
                try{
                    int idLookup = parseInt(searchItem);
                    if(Inventory.lookupProduct(idLookup) != null) {
                        foundProducts.add(Inventory.lookupPart(idLookup));
                        productsTable.setItems(foundProducts);
                    }
                }
                catch(NumberFormatException ex){
                    //do nothing
                }
            }
            if(foundProducts.size() == 0){
                errorMessage("Search Result", "No Product Match", "No products match your search");
            }else {
                productsTable.setItems(foundProducts);
            }
        }
    }
}