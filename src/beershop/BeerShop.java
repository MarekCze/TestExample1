/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beershop;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @name Owner
 */
public class BeerShop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ArrayList<Beer> beerShop = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        int IDSearch = 0;
        int option = 0;
        do {
            System.out.println("1. Add beer");
            System.out.println("2. Delete beer");
            System.out.println("3. Sell beer");
            System.out.println("4. Display beers by sales");
            System.out.println("5. Stock order");
            System.out.println("6. Exit");
            option = s.nextInt();
            switch (option) {
                case 1:
                    Beer b = new Beer();
                    b.addBeer();
                    break;

                case 2:
                    System.out.println("Enter beer ID to delete");
                    IDSearch = s.nextInt();
                    b = new Beer();
                    beerShop = b.findAllBeers(1);
                    b = findBeer(beerShop, IDSearch);
                    b.deleteBeer();
                    break;

                case 3:
                    System.out.println("Enter beer ID");
                    IDSearch = s.nextInt();
                    
                    b = new Beer();
                    beerShop = b.findAllBeers(1);
                    b = findBeer(beerShop, IDSearch);
                    b.sellBeer();
                    break;

                case 4:
                    displayBeer();
                    break;

                case 5:
                    b = new Beer();
                    beerShop = b.findAllBeers(1);
                    stockOrder(beerShop);
                    break;

            }
        } while (option != 6);
    }

    public static boolean updateBeer(ArrayList<Beer> list, Beer b) {
        boolean done = false;
        try {
            b.getUserInput();
            done=true;
        } catch (Exception ex) {
            done = false;
        }
        return done;
    }

    public static boolean deleteBeer(ArrayList<Beer> list, Beer b) {
        boolean done = false;
        Iterator i = list.iterator();
        while (i.hasNext()) {
            i.remove();
            done = true;
        }
        return done;
    }

    public static void displayBeer() throws SQLException, ClassNotFoundException {
        Beer b = new Beer();
        ArrayList<Beer> beers = new ArrayList<Beer>(b.findAllBeers(2));
        
        for (Beer Beer : beers) {
            Beer.print();
        }
    }

    public static Beer findBeer(ArrayList<Beer> list, int idSearch) {

        for (Beer b : list) {

            if (b.getID() == idSearch) {

                return b;
            }
        }
        return null;
    }
    
    public static void stockOrder(ArrayList<Beer> list) {
        ArrayList<Beer> stock = new ArrayList<Beer>();
        for (Beer b : list) {

            if (b.getQtyOnHand()< b.getReorderQty()) {
                stock.add(b);
                
            }
        }
        double qtyToOrder = 0;
        for(Beer b : stock){
            qtyToOrder = b.getReorderQty() * 1.2 - b.getQtyOnHand();
            System.out.println("Beer ID: " + b.getID());
            System.out.println("Beer name: " + b.getName());
            System.out.println("Quantity to order: " + qtyToOrder);
        }
        
        
    }
}

    
    

