package customers;

import sales.Order;


/**
 *
 * @author Sam Rizzo
 */
public class Customer 
{   
    //HAS-A reference to an Order
    Order custOrder = new Order();
    
    private String firstName, lastName, address, phoneNumber, city, postalCode;
    
    public Customer()
    {
        //Empty constructor
    }
    
    public Customer(String firstName, String lastName, String address, 
            String phoneNumber, String city, String postalCode, Order custOrder) 
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.postalCode = postalCode;
        this.custOrder = custOrder;
        
    } // end of Customer constructor 
    
    /* Mutator Methods */
    
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    
    public void setCity(String city)
    {
        this.city = city;
    }
    
    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }
    
    public void setCustOrder(Order custOrder)
    {
        this.custOrder = custOrder;
    }
    
    /* Accessor Methods */
    public String getFirstName() 
    {
        return this.firstName;
    }
    
    public String getLastName() 
    {
        return this.lastName;
    }
    
    public String getFullName() 
    {
        return this.firstName + ' ' + this.lastName;
    }
    
    public String getAddress() 
    {
        return this.address;
    }

    public String getPhoneNumber()
    {
        return this.phoneNumber;
    }
    
    public String getCity()
    {
        return this.city;
    }
    
    public String getPostalCode()
    {
        return this.postalCode;
    }
    
    public Order getCustOrder()
    {
        return this.custOrder;
    }
    
    //String representation of a customer
    @Override
    public String toString()
    {
        return String.format("%s\n%s\n%s, %s, %s\n", getFullName(), getAddress(), 
                getPhoneNumber(), getCity(), getPostalCode());
    }
    
} // end of Customer class
