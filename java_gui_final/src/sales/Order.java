package sales;

import hr.Employee;
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
    private Product product = new Product();
    
    private Employee employee;
    
    private int orderNumber, empID, itemsSold;
    private double subTotal, total, orderTotal;
    private String productList;
    
    private final double salesTax = 0.13;
    
    private GregorianCalendar orderDate;
    
    //ArrayList for storing muliple products
    private ArrayList<Product> products = new ArrayList<>();
    
    public Order()
    {
        // Empty Constructor
    }
    
    public Order(int empID, int itemsSold, String productList, double orderTotal)
    {
        this.empID = empID;
        this.itemsSold = itemsSold;
        this.productList = productList;
        this.orderTotal = orderTotal;
    }
    
    public Order(int orderNumber, Employee employee, Product product)
    {
        this.orderNumber = orderNumber;
        this.product = product;
        this.employee = employee;
        
        orderDate = new GregorianCalendar();
        
        //Add product to product list when created
        addProduct(product);
        
    } // end of Order constructor
    
    /* Mutator Methods */
    public void setOrderNumber(int orderNumber)
    {
        this.orderNumber = orderNumber;
    }
    
    public void setEmployee(Employee employee)
    {
        this.employee = employee;
    }
    
    public void setProductOrder(Product product)
    {
        this.product = product;
    }
    
    public void setEmpID(int empID)
    {
        this.empID = empID;
    }
    
    public void setItemsSold(int itemsSold)
    {
        this.itemsSold = itemsSold;
    }
    
    public void setProductList(String productList)
    {
        this.productList = productList;
    }
    
    public void setOrderTotal(double orderTotal)
    {
        this.orderTotal = orderTotal;
    }
    
    //Return the ID of the employee
    public int getEmployeeID()
    {
        return employee.getIDNumber();
    }
    
    public int getEmpID()
    {
        return this.empID;
    }
    
    public int getItemsSold()
    {
        return this.itemsSold;
    }
    
    public String getProductList()
    {
        return this.productList;
    }
    
    public double getOrderTotal()
    {
        return this.orderTotal;
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
