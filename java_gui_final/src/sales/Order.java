package sales;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import product.Product;

/**
 *
 * @author Sam Rizzo
 */
public class Order 
{
    //HAS-A reference to a product
    Product product = new Product();
    
    private int orderNumber;
    private double subTotal, total;
    
    private final double salesTax = 0.13;
    
    private GregorianCalendar orderDate;
    
    //ArrayList for storing muliple products
    private ArrayList<Product> products = new ArrayList<>();
    
    public Order()
    {
        // Empty Constructor
    }
    
    public Order(int orderNumber, Product product)
    {
        this.orderNumber = orderNumber;
        this.product = product;
        
        orderDate = new GregorianCalendar();
        
        //Add product to product list when created
        addProduct(product);
        
    } // end of Order constructor
    
    /* Mutator Methods */
    public void setOrderNumber(int orderNumber)
    {
        this.orderNumber = orderNumber;
    }
    
    public void setProductOrder(Product product)
    {
        this.product = product;
    }
    
    /**
     * This method adds a product to the products ArrayList
     * @param product
     */
    public void addProduct(Product product)
    {
        products.add(product);
    }
    
    /**
     * This method returns a list of current products
     */
    public void printOrderList()
    {
        System.out.println("Printing a list of Products...\n");
        
        for (int position = 0; position < products.size(); position ++) 
        {
            System.out.println(products.get(position) + "\n");
        }
    }
    
    /* Accessor Methods */
    public int getOrderNumber()
    {
        return this.orderNumber;
    }
    
    public Product getProduct()
    {
        return this.product;
    }
    
    //This method returns the subtotal
    public double getSubTotal()       
    {
        subTotal = product.getProductCost();
        
        return subTotal;
    }
    
    //This method returns the total cost of the order
    public double getTotalOrderCost()
    {
        //Used for formatting 
        DecimalFormat numFormat = new DecimalFormat("#.##");
        
        total = (subTotal * salesTax) + subTotal;
        
        total = Double.valueOf(numFormat.format(total));
        
        return total;
    }
    
    //This method returns the current date and time
    public String getCurrentDate()
    {
        return String.format("%s", orderDate.getTime());
    }
  
} // end of Order class
