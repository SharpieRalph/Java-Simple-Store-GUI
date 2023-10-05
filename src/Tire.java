public class Tire extends Item {
    int wheelDiameter;
    int sectionWidth;
    boolean passengerTire;

    public Tire(int w, int s, boolean pt,double price, int invQuantity){
        super(price, invQuantity);
        this.wheelDiameter = w;
        this.sectionWidth = s;
        this.passengerTire = pt;
    }

    public String toString(){
        String result = "";
        if(passengerTire) result += "Passenger tire";
        else result = "Front Tire";
        return result + " with " + wheelDiameter + "\" wheel diameter " + sectionWidth +
                " mm. section width, price $" + String.format("%,.2f",getPrice()) + " each (" + getInvQuantity() +" in stock, " + getSoldQuantity()
                + " sold).";
    }

    public String cartString(){
        String result = "";
        if(passengerTire) result += getCartQuantity()+  "x Passenger tire";
        else result = getCartQuantity()+  "x Front Tire";
        return result + " with " + wheelDiameter + "\" wheel diameter " + sectionWidth +
                " mm. section width, price $" + String.format("%,.2f",getPrice()) + " each (" + getInvQuantity() +" in stock, " + getSoldQuantity()
                + " sold).";
    }
}
