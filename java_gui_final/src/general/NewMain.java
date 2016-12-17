/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import hr.Employee;
import hr.HourlyEmployee;
import manufacturer.Manufacturer;
import product.Product;
import sales.Order;

/**
 *
 * @author Sam Rizzo
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Manufacturer man = new Manufacturer();
        Product prod = new Product();
        Employee s = new HourlyEmployee("s", "r", "m", "4", "456", 748, 1995, 2, 23, "dd", "hfjd", "d22", 34, 25.00);
        
        Order o = new Order(356, s, prod);
        
        System.out.println(o.getEmployeeID());
        
        System.out.println(s.getDateOfBirth());
        
        String productList = "Product 1\n";
        String productName = "Product 2";
        String productNameList = productList.concat(productName);
        
        System.out.println(productNameList);
    }
    
}
