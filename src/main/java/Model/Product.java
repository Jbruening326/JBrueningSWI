package Model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Author: Joseph Bruening. Student ID 001678932
 */

/**
 * This class contains a constructor, getters and setters to create a Product object.
 */
public class Product {
    /**
     * ObservableList object created for all associated parts
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * Attribute for Product id
     */
    private int id;

    /**
     * Attribute for Product name
     */
    private String name;

    /**
     * Attribute for Product price
     */
    private double price;

    /**
     * Attribute for Product inventory
     */
    private int stock;

    /**
     * Attribute for Product minimum stock value
     */
    private int min;

    /**
     * Attribute for Product maximum stock value
     */
    private int max;

    /**
     * This method is a constructor of a Product object. When this method is called the object will be created using the arguments in the statement.
     * @param id The ID of the product
     * @param name The product name
     * @param price The product price
     * @param stock The product inventory
     * @param min The product minimum stock level
     * @param max The product maximum stock level
     */
    public Product(int id, String name, double price, int stock, int min, int max){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * This method gets a Product object ID. When this method is called, the Product object ID will be returned.
     * @return Returns the Product object ID.
     */
    public int getId() { return id;}

    /**
     * This method sets the Product object ID. When this method is called, the passed argument will be assigned to the Product object ID.
     * @param id The id to set the Product object to
     */
    public void setId(int id) { this.id = id;}

    /**
     * This method gets the name of the Product object. When this method is called, the Product object name will be returned.
     * @return Returns the name of the Product object
     */
    public String getName() { return name;}

    /**
     * This method sets the name of the Product object. When this method is called, the passed argument will be assigned to the Product object name.
     * @param name The name to set the Product object to
     */
    public void setName(String name) { this.name = name;}

    /**
     * This method gets the price of the Product object. When this method is called, the Product object price will be returned.
     * @return Returns the price of the Product object
     */
    public double getPrice() { return price;}

    /**
     * This method sets the price of the Product object. When this method is called, the passed argument will be assigned to the Product object price.
     * @param price The price to set the Product object to
     */
    public void setPrice(double price) { this.price = price;}

    /**
     * This method gets the inventory of the Product object. When this method is called, the Product object inventory will be returned.
     * @return Returns the inventory of the Product object
     */
    public int getStock() { return stock;}

    /**
     * This method sets the inventory of the Product object. When this method is called, the passed argument will be assigned to the Product object inventory.
     * @param stock The inventory to set the Product object to
     */
    public void setStock(int stock) { this.stock = stock;}

    /**
     * This method gets the minimum stock value of the Product object. When this method is called, the Product object minimum stock value will be returned.
     * @return Returns the minimum stock value of the Product object
     */
    public int getMin() { return min;}

    /**
     * This method sets the minimum stock value of the Product object. When this method is called, the passed argument will be assigned to the Product object minimum stock value.
     * @param min The minimum stock value to set the Product object to
     */
    public void setMin(int min) { this.min = min;}

    /**
     * This method gets the maximum stock value of the Product object. When this method is called, the Product object maximum stock value will be returned.
     * @return Returns the maximum stock value of the Product object
     */
    public int getMax() { return max;}

    /**
     * This method sets the maximum stock value of the Product object. When this method is called, the passed argument will be assigned to the Product object maximum stock value.
     * @param max The maximum stock value to set the Prodcut object to
     */
    public void setMax(int max) { this.max = max;}


    /**
     * This method adds a Part object. When this method is called, the Part object in the argument get added to a Product object's associatedParts.
     * @param part The Part object that will be added to the list of associated parts
     */
    public void addAssociatePart(Part part){ associatedParts.add(part);}

    /**
     * This method deletes a Part object. When this method is called, the Part object in the argument is removed from the Product object's associatedParts.
     * @param selectedAssociatedPart The Part object that will be removed from the list of associated parts
     * @return Returns true if part is successfully removed
     */
    public boolean deleteAssociatePart(Part selectedAssociatedPart){
        return associatedParts.remove(selectedAssociatedPart);
    }

    /**
     * This method returns the list lof associated parts in the 'associateParts' list
     */
    /**
     * This method returns an observable list of associated parts. When this method is called, the observable list of associated parts of a Product object will be returned.
     * @return Returns an observable list of associated parts
     */
    public ObservableList<Part> getAllAssociatedParts(){ return associatedParts;}
}
