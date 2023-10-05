import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.collections.FXCollections;
import javafx.scene.input.MouseEvent;

import java.util.Arrays;
import java.util.Scanner;

public class AutoParkApp extends Application{

    private AutoPark model;
    private AutoParkView view;
    public AutoParkApp(){
        model = AutoPark.createPark();
    }
    public void start(Stage primaryStage) {
        Pane  aPane = new Pane();
        view = new AutoParkView();

        view.update(model,0);
        aPane.getChildren().add(view);

        // Set the title as the model name + " _Sales and Inventory"
        primaryStage.setTitle(model.getName() + " _Sales and Inventory");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(aPane));
        primaryStage.show();

        view.getInventoryList().setOnMousePressed(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent mouseEvent) {
                // Deselect option in the cart
                view.getCartList().getSelectionModel().clearSelection();
                view.update(model,0);
            }
        });

        view.getCartList().setOnMousePressed(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent mouseEvent) {
                //Deselect option in the inventory
                view.getInventoryList().getSelectionModel().clearSelection();
                view.update(model,0);
            }
        });

        view.getButtonPane().getAddButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                //Retrieve selected item
                Item selectedItem = view.getInventoryList().getSelectionModel().getSelectedItem();

                if(selectedItem != null) //if not null, add it to cart
                    model.addToCart(selectedItem);

                view.update(model,0);

            }
        });


        view.getButtonPane().getSaleButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {

                // Execute the sale
                model.executeSales();

                //disable all the buttons
                view.getInventoryList().getSelectionModel().clearSelection();
                view.getButtonPane().getSaleButton().setDisable(true);

                view.update(model,0);

            }
        });

        view.getButtonPane().getRemoveButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                // retrieve selected index
                int index =  view.getCartList().getSelectionModel().getSelectedIndex();

                if(index != -1) //if valid index, remove from cart
                    model.removeCartItem(index);
                view.update(model,0);

            }
        });



        view.getButtonPane().getResetButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                model = AutoPark.createPark();
                view.update(model,0);
            }
        });


    }



    public static void main(String[] args) {
        launch(args);
    }
}

