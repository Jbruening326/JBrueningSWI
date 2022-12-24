package Model;

/**
 * Author: Joseph Bruening. Student ID 001678932
 */
/**
 * This class is a subclass of Part
 */
public class Outsourced extends Part{
    /**
     * Special class attribute created for Outsourced Part only
     */
    private String companyName;

    /**
     * This method is a constructor for the InHouse Part. When a new InHouse Part object is created, the constructor will be used
     * @param id The part ID
     * @param name The part name
     * @param price The part price
     * @param stock The part inventory
     * @param min The part minimum stock level
     * @param max The part maximum stock level
     * @param companyName The part name; Specific to Outsourced Part only
     */
    public Outsourced (int id, String name, double price, int stock, int min, int max, String companyName){
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * This method gets the companyName. When the method is called on an Outsource Part object, the companyName is returned.
     * @return Returns the company
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * This method sets companyName for an Outsource Part object. If this method is called, the Outsource Part companyName will be set to specified argument.
     * @param companyName The machine Id to be set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
