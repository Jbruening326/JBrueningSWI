package bruening.jbrueningc482;

import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;

//Author: Joseph Bruening. Student ID 001678932.
//Javadoc location path: C:\Users\jbrue\Desktop\Applications\Java\jbrueningC482Final.zip\jbrueningC482\src\Doc



/** FUTURE ENHANCEMENT. The application can currently add parts or products, modify parts or products, add and remove associated parts to products, and delete parts or products.
 * In order to delete a product, all associated parts must be removed. This cannot currently be done easily from the main screen. Instead,
 * the user must go to the 'Modify Part' screen and remove each associated part individually. This current feature is wasteful of the users time.
 * To make the application more efficient, and additional confirmation window can be displayed to ask the user to remove all associated parts from the main window with
 * a click of a button rather than individually removing each one.*/

public class InventoryApplication extends Application {

    /**
     * This method starts the application. This method starts the application by displaying the 'MainMenu.fxml' file.
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InventoryApplication.class.getResource("MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 365);
        stage.setTitle("Inventory Application");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method displays an error message. The error message contents are dependent on the parameters.
     * @param title Used to set the title for the message
     * @param header Used to set the header for the message
     * @param message Used to set the message to be displayed to the user
     */
    public static void errorMessage(String title, String header, String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * This is the main method. The application is initially loaded with preset data, and then the program is launched.
     * @param args
     */
    public static void main(String[] args) {
        //Add inHouse parts
        Part in1 = new InHouse(1,"2x4x8", 6.50,50,25,100, 1);
        Part in2 = new InHouse(2, "4x4x8", 7.99, 80, 10, 100, 2);
        Part in3 = new InHouse(3, "plywood", 6.50, 60, 30, 150, 3);
        Part in4 = new InHouse(4, "roofing nails", 7.99, 15, 5, 90, 4);
        Part in5 = new InHouse(5, "outlet", 0.89, 12, 5, 50, 5);
        Part in6 = new InHouse(6,"wire strippers", 10.99, 10, 4, 20, 6);
        //Add outSourced parts
        Part o1 = new Outsourced(7, "decking screws", 8.99, 15, 10, 90, "Screw This");
        Part o2 = new Outsourced(8, "shingles", 19.99, 12, 10, 100, "Shingle All The Way");
        Part o3 = new Outsourced(9, "composite 2x4x6", 10.99, 50, 25, 100, "Wood Chuck");
        Part o4 = new Outsourced(10, "1/2in drywall", 8.99, 60, 20, 150, "Wall Kings");
        Part o5 = new Outsourced(11, "14/2 wire", 20.99, 6, 5, 10, "Shockers");
        //Add products and associated parts
        Product p1 = new Product(1, "decking package", 110.99, 5, 2, 10);
        p1.addAssociatePart(in1);
        p1.addAssociatePart(o1);
        p1.addAssociatePart(o3);

        Product p2 = new Product(2, "roofer package", 99.99, 4, 2, 10);
        p2.addAssociatePart(in3);
        p2.addAssociatePart(in4);
        p2.addAssociatePart(o2);
        Product p3 = new Product(3, "drywall package", 85.99, 5, 2, 10);
        p3.addAssociatePart(in1);
        p3.addAssociatePart(o4);

        Inventory.addPart(in1);
        Inventory.addPart(in2);
        Inventory.addPart(in3);
        Inventory.addPart(in4);
        Inventory.addPart(in5);
        Inventory.addPart(in6);
        Inventory.addPart(o1);
        Inventory.addPart(o2);
        Inventory.addPart(o3);
        Inventory.addPart(o4);
        Inventory.addPart(o5);
        Inventory.addProduct(p1);
        Inventory.addProduct(p2);
        Inventory.addProduct(p3);


        launch();
    }
}