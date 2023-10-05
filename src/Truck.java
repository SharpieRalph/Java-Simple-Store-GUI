public class Truck extends CommercialVehicle {
    private boolean heavyDuty;

    public Truck(String make, String model, int year,
                 String carries, boolean h,double price, int invQuantity) {
        super(make, model, year, carries,price, invQuantity);
        this.heavyDuty = h;
    }

    public String toString(){
        String result = super.toString();
        if(heavyDuty){ result = "Heavy-duty Truck: " + result; }
        else{ result = "Truck: " + result; }
        return result;
    }

    public String cartString(){
        String result = super.cartString();
        if(heavyDuty){ result = getCartQuantity()+ "x Heavy-duty Truck: " + result; }
        else{ result = getCartQuantity()+ "x Truck: " + result; }
        return result;
    }






}
