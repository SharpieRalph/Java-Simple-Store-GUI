import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.LightBase;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;



public class AutoParkView extends Pane implements  ItemView{
    private AutoParkButtonPane buttonPane;
    private ListView<Item> inventoryList, popularList;
    private ListView<String> cartList;
    private TextField numField, revenueField, rnField;
    private Label cartLabel;

    public ListView<Item> getInventoryList() {
        return inventoryList;
    }

    public ListView<String> getCartList() {
        return cartList;
    }

    public AutoParkButtonPane getButtonPane() {
        return buttonPane;
    }

    public void update(AutoPark model, int selected){
        Item[] items = model.getInventory();

        ObservableList<Item> list = FXCollections.observableArrayList(items);
        inventoryList.setItems(FXCollections.observableArrayList(items));
        Item selectedInv = inventoryList.getSelectionModel().getSelectedItem();


        Item[] popularItems = model.getPopularItems();
        list = FXCollections.observableArrayList(popularItems);
        popularList.setItems(list);

        String[] cartItems = model.getCartItems();
        ObservableList<String> cartL= FXCollections.observableArrayList(cartItems);
        cartList.setItems(cartL);
        String selectedCart = cartList.getSelectionModel().getSelectedItem();


        //Update all corresponding buttons states
        if(model.getCartSize() != 0)
            buttonPane.getSaleButton().setDisable(false);
        else
            buttonPane.getSaleButton().setDisable(true);

        if(selectedInv != null)
            buttonPane.getAddButton().setDisable(false);
        else
            buttonPane.getAddButton().setDisable(true);

        if(selectedCart != null){
            buttonPane.getRemoveButton().setDisable(false);}
        else
            buttonPane.getRemoveButton().setDisable(true);

        // Update all text field

        numField.setText(String.valueOf(model.getNumSales()));
        revenueField.setText(String.format("$%,.2f", model.getRevenue()));
        if(model.getNumSales() == 0){
            rnField.setText("N/A");
        }
        else
            rnField.setText(String.valueOf(model.getRevenue()/ model.getNumSales()));


        String cartAmount = String.format("$%,.2f", model.getCartAmount());
        cartLabel.setText("Current cart (" + cartAmount + ")");



    }

    public AutoParkView(){
        // Create the labels
        Label label1 = new Label("Park Inventory:");
        label1.relocate(25, 10 );
        Label label2 = new Label("Park Summary");
        label2.relocate(600,10);
        Label label3 = new Label("Most Popular Items");
        label3.relocate(600,160);

        Label label4 = new Label("#Sales:");
        label4.relocate(600, 35);



        Label label5 = new Label("Revenue:");
        label5.relocate(600, 75);
        Label label6 = new Label("$/Sale:");
        label6.relocate(600, 115);


        numField = new TextField();
        numField.setPrefSize(85,10);
        numField.relocate(685,35);
        numField.setEditable(false);

        revenueField = new TextField();
        revenueField.setPrefSize(85,10);
        revenueField.relocate(685,75);
        revenueField.setEditable(false);

        rnField = new TextField();
        rnField.setPrefSize(85,10);
        rnField.relocate(685,115);
        rnField.setEditable(false);



        cartLabel = new Label("Current cart ($0.0)");
        cartLabel.relocate(310,10);

        inventoryList = new ListView<>();
        inventoryList.relocate(25,35);
        inventoryList.setPrefSize(280,300);

        cartList = new ListView<>();
        cartList.relocate(310,35);
        cartList.setPrefSize(280,300);

        popularList = new ListView<>();
        popularList.relocate(600, 185);
        popularList.setPrefSize(170,150);

        buttonPane = new AutoParkButtonPane();
        buttonPane.relocate(80,350);


        getChildren().addAll(label1, label2, label3, label4, label5, label6, numField, revenueField, rnField , inventoryList, cartList, popularList , cartLabel,buttonPane);
        setPrefSize(800,400);

    }
}
