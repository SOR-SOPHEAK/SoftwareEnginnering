import java.util.ArrayList;
import java.util.Scanner;

import javax.annotation.processing.SupportedSourceVersion;

public class TP06_5_NewYearGiftShop {
    private ArrayList<Product> productlList = new ArrayList<>();

    Product p1 = new Product(1, "Robot", (float) 19.7, 12);
    Product p2 = new Product(2, "Book", (float) 6.5, 20);
    Product p3 = new Product(3, "Doll", (float) 23.5, 10);
    Product p4 = new Product(4, "Flower", (float) 9.95, 50);
    Product p5 = new Product(5, "Ball", (float) 12.0, 15);

    public TP06_5_NewYearGiftShop(){
        productlList.add(p1);
        productlList.add(p2);
        productlList.add(p3);
        productlList.add(p4);
        productlList.add(p5);
    }

    public void listAllProduct(){
        System.out.println("\n\tNumber\tName\tPrice\tInstock");
        System.out.println("\t-------------------------------");
          for(Product product: productlList){
            System.out.println(product);
          }
    }

    public void addNewProduct(Product product){
        productlList.add(product);
    }

    public void removeProductByIndex(int index){
        index=0;
        for(int i=0; i<productlList.size(); i++){
            Product pro = productlList.get(i);
            if(productlList.get(i).getIndex() == index){
                index = i;
                productlList.remove(pro);
                System.out.println("\t*** Remove Success!!");
            }
        }
    }

    public void updateProduct(int productNum){
        Scanner sc = new Scanner(System.in);
        for(Product product: productlList){
            if(product.getPnumber() == productNum){
                System.out.print("\tInput new product number: ");
                productNum = sc.nextInt();
                System.out.print("\tInput new product name: ");
                String productName = sc.next();
                System.out.print("\tInput new product price: ");
                Float productPrice = sc.nextFloat();
                System.out.print("\tInput new product amount in stock: ");
                int productInstock = sc.nextInt();

                product.setPnumber(productNum);
                product.setPname(productName);
                product.setPrice(productNum);
                product.setPinstock(productInstock);

                System.out.println("\t***** Product Update Successful!");
            }
        }
    }

    public Product inputProduct(){
        Scanner sc = new Scanner(System.in);

        System.out.print("\tInput product number: ");
        int num = sc.nextInt();
        System.out.print("\tInput product name: ");
        String name = sc.nextLine();
        name = sc.nextLine();
        System.out.print("\tInput product price: ");
        float price = sc.nextFloat();
        System.out.print("\tInput product amount in stock: ");
        int instock = sc.nextInt();

        Product newProduct = new Product(num, name, price, instock);
        return newProduct;
    }

    public void Menu(){
        System.out.println("\n\t============================================");
        System.out.println("\t\t\t    Menu");
        System.out.println("\t============================================");
        System.out.println("\t1. List all products in shop with product\n\t   number, name, price, and amount in stock.");
        System.out.println("\t2. Add new product to the list.");
        System.out.println("\t3. Remove product from list by index.");
        System.out.println("\t4. Update product in list.");
        System.out.println("\t5. Exit program");
        System.out.println("\t----------------------x----------------------");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TP06_5_NewYearGiftShop pro = new TP06_5_NewYearGiftShop();


        int option;
        do{
            pro.Menu();
            System.out.print("\n-->  Chose an option: ");
            option = sc.nextInt();

            switch(option){
                case 1:
                // List all products in shop with product number, name, price, and amount in stock
                    pro.listAllProduct();
                    break;
                case 2:
                //Add new product to the list
                    Product p = pro.inputProduct();
                    pro.addNewProduct(p);
                    break;
                case 3:
                //Remove product from list by index
                    System.out.print("\tInput index of product to remove: ");
                    pro.removeProductByIndex(sc.nextInt());
                    break;
                case 4:
                //Update product in list
                    System.out.print("\tInput product number to update: ");
                    pro.updateProduct(sc.nextInt());
                    break;
                case 5:
                //exit prodram
                    System.out.println("Program exit!!");
                    System.exit(1);
                    break;
            }
        }while(option != 5);
    
    }
}
