package hr;

/**
 *
 * @author Sam Rizzo
 */
public class CommissionSalesEmployee extends Employee{
    
    private double grossSales;
    private double commissionRate;

    public CommissionSalesEmployee()
    {
        
    }
    
    public CommissionSalesEmployee(String firstName, String lastName, 
            String gender,String address, String phoneNumber, int sin, 
            int year, int month, int day) {
        
        super(firstName, lastName, gender, address, phoneNumber, sin, year, 
                month, day);     
        
    } // end of basic CommissionSalesEmployee constructor

    
    public CommissionSalesEmployee(String firstName, String lastName, 
            String gender, String address, String phoneNumber,int sin, 
            int year, int month, int day, String position,String status, 
            String department, int idNumber, double grossSales, 
            double commissionRate) {
        
        super(firstName, lastName, gender, address, phoneNumber, sin, year, 
                month, day, position, status, department, idNumber);
        
        this.grossSales = grossSales;
        this.commissionRate = commissionRate;
        
    } // end of advanced CommissionSalesEmployee constructor
    
    //Mutator Methods 

    public void setGrossSales(double grossSales) {
        
        this.grossSales = grossSales;
    }

    public void setCommissionRate(double commissionRate){
        this.commissionRate = commissionRate;
    }
    
    // Accessor Methods

    public double getGrossSales(){
        
        return this.grossSales;
    }
    
    public double getCommissionRate() {
        
        return this.commissionRate;
    }
    
    /**
     * This method calculates the employee's earnings by
     * multiplying the grossSales by the commissionRate
     * @return 
     */
    @Override
    public double calculateEarnings() {
        
        return this.grossSales * this.commissionRate;
    }
    
    /**
     * This method overrides the super.advancedToString by adding 
     * line elements for grossSales and commissionRate
     * @return 
     */
    @Override
    public String advancedToString() {
        
        return super.advancedToString() + String.format("Gross Sales: %.2f\n"
                + "Commission Rate: %.2f\n", getGrossSales(), 
                getCommissionRate());
    }
} // end of CommissionSalesEmployee class
