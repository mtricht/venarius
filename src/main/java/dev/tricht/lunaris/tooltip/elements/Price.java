package dev.tricht.lunaris.tooltip.elements;

import dev.tricht.lunaris.info.poeprices.ItemPricePrediction;
import dev.tricht.lunaris.item.Item;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.jetbrains.annotations.NotNull;

public class Price implements Element {

    private Item item;
    private ItemPricePrediction prediction;

    public Price(Item item) {
        this.item = item;
    }

    public Price(ItemPricePrediction prediction) {
        this.prediction = prediction;
    }

    @Override
    public Node build() {
        if (item != null) {
            return buildForItem();
        }
        return buildForPrediction();
    }

    @NotNull
    private Node buildForItem() {
        Item chaos = new Item();
        chaos.setIconUrl("https://web.poecdn.com/image/Art/2DItems/Currency/CurrencyRerollRare.png?scale=1&w=1&h=1");

        Icon icon = new Icon(chaos, 32);

        Label price = new Label(String.format("~ %s chaos %s", item.getMeanPrice().getPrice(), item.getMeanPrice().isLowConfidence() ? "(low confidence)": ""));
        price.setTextFill(Color.WHITE);
        price.setFont(new Font("Arial", 14));
        price.setBackground(new Background(new BackgroundFill(Color.rgb(33, 33, 33), CornerRadii.EMPTY, Insets.EMPTY)));
        price.setPadding(new Insets(10, 10, 10, 10));
        price.setMinHeight(32 + Icon.PADDING);

        GridPane gridPanePrice = new GridPane();
        gridPanePrice.add(icon.build(), 0, 0);
        gridPanePrice.add(price, 1, 0);

        return gridPanePrice;
    }

    @NotNull
    private Node buildForPrediction() {
        Item currency = new Item();
        if (prediction.getCurrency().equals("exalt")) {
            currency.setIconUrl("https://web.poecdn.com/image/Art/2DItems/Currency/CurrencyAddModToRare.png?scale=1&w=1&h=1");
        } else { // Assume it's chaos
            currency.setIconUrl("https://web.poecdn.com/image/Art/2DItems/Currency/CurrencyRerollRare.png?scale=1&w=1&h=1");
        }

        Icon icon = new Icon(currency, 32);

        Label price = new Label(prediction.getPrice());
        price.setTextFill(Color.WHITE);
        price.setFont(new Font("Arial", 14));
        price.setBackground(new Background(new BackgroundFill(Color.rgb(33, 33, 33), CornerRadii.EMPTY, Insets.EMPTY)));
        price.setPadding(new Insets(10, 10, 10, 10));
        price.setMinHeight(32 + Icon.PADDING);

        GridPane gridPanePrice = new GridPane();
        gridPanePrice.add(icon.build(), 0, 0);
        gridPanePrice.add(price, 1, 0);

        return gridPanePrice;
    }
}
