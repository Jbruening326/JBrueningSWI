package Model;


/**
 * This class is a super class of InHouse and Outsourced parts and contains a constructor, getters and setters to create a Part object.
 */
public abstract class Part{
    /**
     * Attribute for Part id
     */
    private int id;

    /**
     * Attribute for Part name
     */
    private String name;

    /**
     * Attribute for Part price
     */
    private double price;

    /**
     * Attribute for Part inventory
     */
    private int stock;

    /**
     * Attribute for Part minimum stock value
     */
    private int min;

    /**
     * Attribute for Part maximum stock value
     */
    private int max;


    /**
     * This method is a constructor of a Part object. When this method is called the object will be created using the arguments in the statement.
     * @param id The ID of the part
     * @param name The part name
     * @param price The part price
     * @param stock The part inventory
     * @param min The part minimum stock level
     * @param max The part maximum stock level
     */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * This method gets a Part object ID. When this method is called, the Part object ID will be returned.
     * @return Returns the Part object ID.
     */
    public int getId() {
        return id;
    }

    /**
     * This method sets the Part object ID. When this method is called, the passed argument will be assigned to the Part object ID.
     * @param id The id to set the Part object to
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This method gets the name of the Part object. When this method is called, the Part object name will be returned.
     * @return Returns the name of the Part object
     */
    public String getName() {
        return name;
    }

    /**
     * This method sets the name of the Part object. When this method is called, the passed argument will be assigned to the Part object name.
     * @param name The name to set the Part object to
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method gets the price of the Part object. When this method is called, the Part object price will be returned.
     * @return Returns the price of the Part object
     */
    public double getPrice() {
        return price;
    }

    /**
     * This method sets the price of the Part object. When this method is called, the passed argument will be assigned to the Part object price.
     * @param price The price to set the Part object to
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * This method gets the inventory of the Part object. When this method is called, the Part object inventory will be returned.
     * @return Returns the inventory of the Part object
     */
    public int getStock() {
        return stock;
    }

    /**
     * This method sets the inventory of the Part object. When this method is called, the passed argument will be assigned to the Part object inventory.
     * @param stock The inventory to set the Part object to
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * This method gets the minimum stock value of the Part object. When this method is called, the Part object minimum stock value will be returned.
     * @return Returns the minimum stock value of the Part object
     */
    public int getMin() {
        return min;
    }

    /**
     * This method sets the minimum stock value of the Part object. When this method is called, the passed argument will be assigned to the Part object minimum stock value.
     * @param min The minimum stock value to set the Part object to
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * This method gets the maximum stock value of the Part object. When this method is called, the Part object maximum stock value will be returned.
     * @return Returns the maximum stock value of the Part object
     */
    public int getMax() {
        return max;
    }

    /**
     * This method sets the maximum stock value of the Part object. When this method is called, the passed argument will be assigned to the Part object maximum stock value.
     * @param max The maximum stock value to set the Part object to
     */
    public void setMax(int max) {
        this.max = max;
    }
}
