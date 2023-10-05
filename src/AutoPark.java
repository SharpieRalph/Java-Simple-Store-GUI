import java.io.*;
import java.util.*;

public class AutoPark implements Serializable{
    public final int MAX_ITEMS = 10;
    public static int totalItem;
    private String name;
    private double revenue;
    private Item[] items;

    private int numSales;

    private Item[] cartItems;
    private int cartSize;

    public int getCartSize() {
        return cartSize;
    }

    public String getName() {
        return name;
    }

    public AutoPark(String name){
        this.name = name;
        revenue = 0;
        items = new Item[MAX_ITEMS];
        cartItems = new Item[MAX_ITEMS];
        totalItem = 0;
        cartSize = 0;
        numSales = 0;

    }

    public int getNumSales() { return numSales;}

    public double getRevenue() {
        return revenue;
    }


    public Item[] getInventory(){
        // Check how many items we have in stock
        int num = 0;
        for(int i = 0; i< totalItem; i++){
            if(items[i].getInvQuantity() != 0)
                num++;

        }

        //add them to a new array
        Item[] itemsInv = new Item[num];
        num = 0;
        for(int i = 0; i< totalItem; i++){
            if(items[i].getInvQuantity() != 0) {
                itemsInv[num] = items[i];
                num++;
            }
        }

        return itemsInv;
    }


    //Adds an item and returns true if there is space in the array
    //Returns false otherwise
    public boolean addItem(Item newItem){
    if(totalItem < MAX_ITEMS){
     items[totalItem] = newItem;
      totalItem++;
            return true;
        }
        return false;
    }




    public static AutoPark createPark() {
        AutoPark park1 = new AutoPark("VroomVille Vehicle Haven");
        Sedan s1 = new Sedan("Hyundai", "Sonata", "Black", 2020, 35000, 10);
        Sedan s2 = new Sedan("BMW", "3 Series", "White", 2022, 42000, 10);
        park1.addItem(s1);
        park1.addItem(s2);
        SUV suv1 = new SUV("Chevy", "Trailblazer", "Red", 2021, true,32000,10);
        SUV suv2 = new SUV("Jeep", "Grand Cherokee", "Green", 2018, false,21000,10);
        park1.addItem(suv1);
        park1.addItem(suv2);
        Truck t1 = new Truck("Toyota", "Tacoma", 2019, "goods",true,28000,10);
        Truck t2 = new Truck("Ford", "Ranger", 2022, "equipment",false,30000,10);
        park1.addItem(t1);
        park1.addItem(t2);
        Van v1 = new Van("Ford", "Transit", 2020, "goods",true,22000,10);
        Van v2 = new Van("Ram", "ProMaster", 2019, "equipment",false,19000,10);
        park1.addItem(v1);
        park1.addItem(v2);
        Tire tire1 = new Tire(10,30,true,390, 20);
        Tire tire2 = new Tire(12,35,false,320,20);
        park1.addItem(tire1);
        park1.addItem(tire2);
        return park1;
    }

    public  Item[] getPopularItems() {
        Item[] itemsCopy = Arrays.copyOf(items, items.length);

        List<Item> popularItems = Arrays.asList(itemsCopy);

        //Sort the items collection
        Collections.sort(popularItems, new Comparator<Item>() {
            public int compare(Item item1, Item item2) {
                return item1.compareTo(item2);
            }
        });

        Item[] top3 = new Item[3];

        for(int i = 0 ; i<3; i++){
            top3[i] = popularItems.get(i);
        }
        return  top3;
    }

    public void addToCart(Item item){
        //if cart empty, add item to the art
        if(cartSize == 0){
            cartItems[cartSize] = item;
            item.setCartQuantity(1);
            item.setInvQuantity(1);
            cartSize++;
        }
        else{
            //if not check if the item is in cart , and increase cart quantity
            boolean  inCart = false;

            for(int i = 0; i<cartSize; i++){
                if(cartItems[i].equals(item)){
                    item.setCartQuantity(1);
                    item.setInvQuantity(1);
                    inCart = true;
                }
            }

            // if not in the cart. add item to the cart
            if(!inCart){
                cartItems[cartSize] = item;
                item.setCartQuantity(1);
                item.setInvQuantity(1);
                cartSize++;
            }

        }

    }

    public String[] getCartItems(){
        String[] cartItem = new String[cartSize];
        for(int i = 0; i<cartSize;i++ ){
            cartItem[i] = cartItems[i].cartString();
        }
        return cartItem;
    }


    public double getCartAmount() {
        double amount = 0.0;
        for(int i = 0; i<cartSize;i++){
            amount += cartItems[i].getCartQuantity() * cartItems[i].getPrice();
        }
        return amount;
    }



    public void removeCartItem(int index) {
        if (index >= 0 && index < cartSize) {
            Item item = cartItems[index];

            // Check if it is the last instance of the item in the cart
            if (item.getCartQuantity() == 1) {
                item.setCartQuantity(-1);
                item.updateStockQuantity(1);

                // Remove the item from the cart and adjust quantities
                for (int i = index; i < cartSize - 1; i++) {
                    cartItems[i] = cartItems[i + 1];
                }
                cartItems[cartSize - 1] = null;
                cartSize--;
            } else {
                // Just reduce the cart quantity and increase inventory quantity
                item.setCartQuantity(-1);
                item.updateStockQuantity(1);
            }
        }
    }


    public void executeSales(){
        revenue += this.getCartAmount();
        System.out.println(revenue);
        for(int i = 0; i<cartSize; i++){
            cartItems[i].sellUnits(cartItems[i].getCartQuantity());
        }

        numSales++;
        cartSize = 0;

    }
}
