package manufacturer;

/**
 *
 * @author Sam Rizzo
 */
public class Manufacturer {
    
    // Declare private instance variables
    private String companyName;
    private String address;
    private String phoneNumber; 
    private int idNumber;
    
    public Manufacturer() 
    {
        
    }
    
    public Manufacturer(String companyName, String address, String phoneNumber, 
            int idNumber) {
        
        this.companyName = companyName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.idNumber = idNumber;
        
    } // end of Manufacturer constructor
    
    /********** Mutator Methods **********/
    
    /**
     * This method sets the companyName
     * @param companyName
     */
    public void setCompanyName(String companyName) {
        
        this.companyName = companyName;
    }
    
    /**
     * This method sets the address
     * @param address
     */
    public void setAddress(String address) {
        
        this.address = address;
    }
    
    /**
     * This method sets the phoneNumber
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        
        this.phoneNumber = phoneNumber;
    }
    
    /**
     * This method sets the idNumber
     * @param idNumber
     */
    public void setIDNumber(int idNumber) {
        
        this.idNumber = idNumber;
    }
    
    /********** Accessor Methods **********/
    
    /**
     * This method returns the companyName
     * @return
     */
    public String getCompanyName() {
        
        return this.companyName;
    }
    
    /**
     * This method returns the address
     * @return
     */
    public String getAddress() {
        
        return this.address;
    }
    
    /**
     * This method returns the phoneNumber
     * @return
     */
    public String getPhoneNumber() {
        
        return this.phoneNumber;
    }
    
    /**
     * This method returns the idNumber
     * @return
     */
    public int getIDNumber() {
        
        return this.idNumber;
    }
    
    /**
     * This method returns a string representation
     * of the manufacturer
     * @return
     */
    @Override
    public String toString() {
        
        return String.format("Company Name: %s\nAddress: %s\nPhoneNumber: %s\n"
                + "Manufacturer ID: %d", getCompanyName(), getAddress(), 
                getPhoneNumber(), getIDNumber());
    }
    
} // end of Manufacturer class

