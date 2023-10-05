import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
public class AutoParkButtonPane extends Pane{
    private Button addButton, removeButton, saleButton, resetButton;

    public Button getAddButton(){
        return addButton;
    }

    public Button getRemoveButton() {
        return removeButton;
    }

    public Button getSaleButton() {
        return saleButton;
    }

    public Button getResetButton() {
        return resetButton;
    }

    public AutoParkButtonPane(){
        Pane aPane = new Pane();

        // "Add to cart" button
        addButton = new Button("Add to Cart");
        addButton.setStyle("-fx-font: 12 arial; -fx-base: rgb(0,100,0); -fx-text-fill: rgb(255,255,255);");
        addButton.relocate(0, 0);
        addButton.setPrefSize(120,30);
        addButton.setDisable(true);

        // "Remove item" button
        removeButton = new Button("Remove Item");
        removeButton.setDisable(true);
        removeButton.setStyle("-fx-font: 12 arial; -fx-base: rgb(0,100,0); -fx-text-fill: rgb(255,255,255);");
        removeButton.relocate(240,0);
        removeButton.setPrefSize(120,30);

        // "Complete Sale" button
        saleButton = new Button("Complete Sale");
        saleButton.setStyle("-fx-font: 12 arial; -fx-base: rgb(0,100,0); -fx-text-fill: rgb(255,255,255);");
        saleButton.relocate(375,0);
        saleButton.setDisable(true);
        saleButton.setPrefSize(120,30);

        // "Reset Stock" button
        resetButton = new Button("Reset Stock");
        resetButton.setStyle("-fx-font: 12 arial; -fx-base: rgb(0,100,0); -fx-text-fill: rgb(255,255,255);");
        resetButton.relocate(555,0);
        resetButton.setPrefSize(120,30);

        aPane.getChildren().addAll(addButton, removeButton, saleButton, resetButton);
        getChildren().addAll(aPane);
    }
}
