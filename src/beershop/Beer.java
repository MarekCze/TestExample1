/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beershop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @name Owner
 */
public class Beer {
    
    private int ID;
    private String name;
    private double price;
    private String description;
    private int qtyOnHand;
    private int reorderQty;
    private int qtySold;
    private String img;
    private String abv;

    public Beer() {

    }

    public Beer(int ID, String name, double price, String description, int qtyOnHand, int reorderQty, int qtySold, String img, String abv) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.description = description;
        this.qtyOnHand = qtyOnHand;
        this.reorderQty = reorderQty;
        this.qtySold = qtySold;
        this.img = img;
        this.abv = abv;
    }
    
    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }
    
    public int getReorderQty() {
        return reorderQty;
    }

    public void setReorderQty(int reorderQty) {
        this.reorderQty = reorderQty;
    }
    
    public int getQtySold() {
        return qtySold;
    }

    public void setQtySold(int qtySold) {
        this.qtySold = qtySold;
    }
    
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    public String getAbv() {
        return abv;
    }

    public void setAbv(String abv) {
        this.abv = abv;
    }
    

    public void getUserInput() {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter Name");
        name = s.next();
        System.out.println("Enter Price");
        price = s.nextDouble();
        System.out.println("Enter Description");
        description = s.next();
        System.out.println("Enter qtyOnHand");
        qtyOnHand = s.nextInt();
        System.out.println("Enter reorderQty");
        reorderQty = s.nextInt();
        System.out.println("Enter qtySold");
        qtySold = s.nextInt();
        System.out.println("Enter Image");
        img = s.next();
        System.out.println("Enter ABV");
        abv = s.next();
        
    }

    public void print() {

        System.out.println("Beer ID " + ID);
        System.out.println("Name " + name);
        System.out.printf("Price %.2f\n", price);
        System.out.println("Description " + description);
        System.out.println("Quantity On Hand " + qtyOnHand);
        System.out.println("Reorder Quantity " + reorderQty);
        System.out.println("Quantity Sold " + qtySold);
        System.out.println("Image " + img);
        System.out.println("ABV " + abv);
        
        System.out.println("****************************");

    }

    public ArrayList<Beer> findAllBeers(int option) throws SQLException, ClassNotFoundException {
        ArrayList<Beer> allBeers = new ArrayList<Beer>();

        Connection c = DBHelperClass.getConnection();
        String template = "";
        
        if(option == 1){
            template = "SELECT * FROM products";
        } else if(option == 2){
            template = "SELECT * FROM products ORDER BY qtySold DESC";
        }

        if (c != null) {
            try {
                PreparedStatement inserter = c.prepareStatement(template);
                ResultSet resultSet = inserter.executeQuery();
//                System.out.println("All beers");
                while (resultSet.next()) {
                    Beer b = new Beer();
                    b.ID = resultSet.getInt("ID");
                    b.name = resultSet.getString("name");
                    b.price = resultSet.getFloat("price");
                    b.description = resultSet.getString("description");
                    b.qtyOnHand = resultSet.getInt("qtyOnHand");
                    b.reorderQty = resultSet.getInt("reorderQty");
                    b.qtySold = resultSet.getInt("qtySold");
                    b.img = resultSet.getString("image");
                    b.abv = resultSet.getString("abv");
                    
                    allBeers.add(b);
                }

//                System.out.println(inserter);
                inserter.close();
                c.close();
            } catch (SQLException ex) {
                System.out.println("Error on find all " + ex);
            }
        }

        return allBeers;
    }
    
    public ArrayList<Beer> displayBeersBySales() throws SQLException, ClassNotFoundException {
        ArrayList<Beer> allBeers = new ArrayList<Beer>();

        Connection c = DBHelperClass.getConnection();
        String template = "SELECT * FROM products ORDER BY qtySold DESC";

        if (c != null) {
            try {
                PreparedStatement inserter = c.prepareStatement(template);
                ResultSet resultSet = inserter.executeQuery();
//                System.out.println("All beers");
                while (resultSet.next()) {
                    Beer b = new Beer();
                    b.ID = resultSet.getInt("ID");
                    b.name = resultSet.getString("name");
                    b.price = resultSet.getFloat("price");
                    b.description = resultSet.getString("description");
                    b.qtyOnHand = resultSet.getInt("qtyOnHand");
                    b.reorderQty = resultSet.getInt("reorderQty");
                    b.qtySold = resultSet.getInt("qtySold");
                    b.img = resultSet.getString("image");
                    b.abv = resultSet.getString("abv");
                    
                    allBeers.add(b);
                }

//                System.out.println(inserter);
                inserter.close();
                c.close();
            } catch (SQLException ex) {
                System.out.println("Error on find all " + ex);
            }
        }

        return allBeers;
    }

    public void addBeer() throws SQLException, ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        Connection c = DBHelperClass.getConnection();

        String template = "INSERT INTO PRODUCTS VALUES(?,?,?,?,?,?,?,?,?)";

        if (c != null) {
            try {
                getUserInput();

                PreparedStatement inserter = c.prepareStatement(template);

                inserter.setInt(1, this.ID);
                inserter.setString(2, this.name);
                inserter.setDouble(3, this.price);
                inserter.setString(4, this.description);
                inserter.setInt(5, this.qtyOnHand);
                inserter.setInt(6, this.reorderQty);
                inserter.setInt(7, this.qtySold);
                inserter.setString(8, this.img);
                inserter.setString(9, this.abv);
                inserter.executeUpdate();
                inserter.close();
                c.close();
            } catch (SQLException e) {
                System.out.println("Error while adding data " + e);
            }
        }
    }

    public void deleteBeer() throws SQLException, ClassNotFoundException {
        Connection c = DBHelperClass.getConnection();

        String template = "DELETE FROM PRODUCTS WHERE ID=?";

        if (c != null) {
            try {
                PreparedStatement inserter = c.prepareStatement(template);

                inserter.setInt(1, this.ID);
                inserter.executeUpdate();
                inserter.close();
                c.close();
            } catch (SQLException e) {
                System.out.println("Error while deleting record " + e);
            }
        }
    }

    public void updateBeer() throws SQLException, ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        Connection c = DBHelperClass.getConnection();

        String template = "UPDATE BEERS SET NAME=?,DESCRIPTION=?,PRICE=? WHERE ID=?";

        if (c != null) {
            try {
                System.out.println("Enter name");
                setName(input.nextLine());
                System.out.println("Enter description");
                setDescription(input.nextLine());
                System.out.println("Enter price");
                setPrice(input.nextDouble());

                PreparedStatement inserter = c.prepareStatement(template);

                inserter.setString(1, this.name);
                inserter.setString(2, this.description);
                inserter.setDouble(3, this.price);
                inserter.setInt(4, this.ID);
                inserter.executeUpdate();
                inserter.close();
                c.close();
            } catch (SQLException e) {
                System.out.println("Error while updating " + e);
            }
        }
    }
    
        public void sellBeer() throws SQLException, ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        Connection c = DBHelperClass.getConnection();

        

        if (c != null) {
            try {
                String template = "UPDATE products SET qtyOnHand=?, qtySold=? WHERE ID=?";
                PreparedStatement inserter = c.prepareStatement(template);

                System.out.println("Enter sale quantity");
                int sale = input.nextInt();
                
                this.qtyOnHand -= sale;
                this.qtySold += sale;
                

                inserter = c.prepareStatement(template);
                
                inserter.setInt(1, this.qtyOnHand);
                inserter.setInt(2, this.qtySold);
                inserter.setInt(3, this.ID);
                inserter.executeUpdate();
                inserter.close();
                c.close();
                
                System.out.println("Sale successful");
            } catch (SQLException e) {
                System.out.println("Error while selling " + e);
            }
        }
    }
}


