package Model;


/**
 * This class is a subclass of Part
 */
public class InHouse extends Part{
    /**
     * Specific class attribute created for InHouse Part only
     */
    private int machineId;

    /**
     * This method is a constructor for the InHouse Part. When a new InHouse Part object is created, the constructor will be used
     * @param id The part ID
     * @param name The part name
     * @param price The part price
     * @param stock The part inventory
     * @param min The part minimum stock level
     * @param max The part maximum stock level
     * @param machineId The part machine ID; Specific to InHouse Part only
     */
    public InHouse (int id, String name, double price, int stock, int min, int max, int machineId){
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * This method gets the machineId. When the method is called on an InHouse Part object, the machineId is returned.
     * @return Returns the machineId
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * This method sets machineId for an InHouse Part object. If this method is called, the InHouse Part machineId will be set to specified argument.
     * @param machineId The machine Id to be set
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
