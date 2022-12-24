package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * This class contains attributes and methods for modifying part and product objects.
 */
public class Inventory {

    /**
     * ObservableList object created to store list of all parts
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     * ObservableList object created to store list of all products
     */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();



    /**
     * This method adds a part. When this method is called, a part object will be added to and observable list.
     * @param newPart The part object that will be added
     */
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }

    /**
     * This method adds a product. When this method is called, a product object will be added to and observable list.
     * @param newProduct The part object that will be added
     */
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    /**
     * This method will search for a part by it's ID. When this method is called an observable list will be iterated though and will return the match.
     * If no match is found, null is returned
     * @param partId The integer argument being compared to
     * @return Returns a part object if a part exists, or returns null if no part exists
     */
    public static Part lookupPart(int partId){
        Part foundPart;
        for (Part part : allParts){
            if(part.getId() == partId ){
                foundPart = part;
                return foundPart;
            }
        }
        return null;
    }

    /**
     * This method will search for a product by it's ID. When this method is called an observable list will be iterated though and will return the match.
     * If no match is found, null is returned
     * @param productId The integer argument being compared to
     * @return Returns a product object if a product exists, or returns null if no product exists
     */
    public static Product lookupProduct(int productId){
        Product foundProduct = null;
        for (Product iProduct : allProducts){
            if(productId == iProduct.getId() ){
                foundProduct = iProduct;
            }
        }
        return foundProduct;
    }

    /**
     * This method will search for a part by it's name. When this method is called an observable list will be iterated through and will return a match if the object
     * even contains the argument searched. An observable list with matched objects will be returned.
     * @param partName The string value argument to be compared to
     * @return Returns an observable list foundParts
     */
    public static ObservableList<Part> lookupPart (String partName){
        ObservableList<Part> foundParts = FXCollections.observableArrayList();
        for (Part iPart : allParts) {
            if(iPart.getName().contains(partName)){
                foundParts.add(iPart);
            }
        }
        return foundParts;
    }

    /**
     * This method will search for a product by it's name. When this method is called an observable list will be iterated through and will return a match if the object
     * even contains the argument searched. An observable list with matched objects will be returned.
     * @param productName The string value argument to be compared to
     * @return Returns an observable list foundProducts
     */
    public static ObservableList<Product> lookupProduct (String productName){
        ObservableList<Product> foundProducts = FXCollections.observableArrayList();
        for (Product iProduct : allProducts){
            if(iProduct.getName().contains(productName)){
                foundProducts.add(iProduct);
            }
        }
        return foundProducts;
    }


    /**
     * This method updates a part object. When this method is called, a current part object will be replaced by a new part object.
     * @param index The index of the part to be updated from the observable list
     * @param selectedPart The part object that will replace the old part object
     */
    public static void updatePart(int index, Part selectedPart){
        allParts.set(index,selectedPart);
    }

    /**
     * This method updates a product object. When this method is called, a current product object will be replaced by a new product object.
     * @param index The index of the product to be updated from the observable list
     * @param newProduct The product object that will replace the old product object
     */
    public static void updateProduct(int index, Product newProduct){
        allProducts.set(index, newProduct);
    }

    /**
     * This method will delete an existing part object. When this object is called, the argument part object will be deleted from the observable list.
     * @param selectedPart The part object in which to delete
     * @return Returns true when the part object is deleted
     */
    public static boolean deletePart(Part selectedPart){
        return allParts.remove(selectedPart);
    }

    /**
     * This method will delete an existing product object. When this object is called, the argument product object will be deleted from the observable list.
     * @param selectedProduct The product object in which to delete
     * @return Returns true when the product object is deleted
     */
    public static boolean deleteProduct(Product selectedProduct){
        return allProducts.remove(selectedProduct);
    }

    /**
     * This method gets objects from an observable list. When the method is called, a new observable list of all parts is returned.
     * @return Returns observable list of all parts
     */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    /**
     * This method gets objects from an observable list. When the method is called, a new observable list of all product is returned.
     * @return Returns observable list of all products
     */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }

}
