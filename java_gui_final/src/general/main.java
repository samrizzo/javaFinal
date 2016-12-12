/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import manufacturer.Manufacturer;
import product.Product;
import sales.Order;

/**
 *
 * @author Sam Rizzo
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        Manufacturer phoneManu = new Manufacturer();
        Product myProduct = new Product("This product is the new "
                + "Google Pixel Phone", 
        "Pixel", 4352, 649.99, phoneManu);
        
        Order m = new Order(12034, myProduct);
        
        System.out.println("Testing the order class...\n");
        
        System.out.println("Getting the order's subtotal...\n");
        System.out.println(m.getSubTotal());
        System.out.println();
        
        System.out.println("Getting the order total...\n");
        System.out.println(m.getTotalOrderCost());
        System.out.println();
        
        System.out.println(m.getCurrentDate());
    }
    
}
