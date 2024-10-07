package com.example.colorchooser_jasonyi;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.text.NumberFormat;

public class ColorChooserController {
    // instance variables for interacting with GUI components
    @FXML private Slider redSlider;
    @FXML private Slider greenSlider;
    @FXML private Slider blueSlider;
    @FXML private Slider alphaSlider;
    @FXML private TextField redTextField;
    @FXML private TextField greenTextField;
    @FXML private TextField blueTextField;
    @FXML private TextField alphaTextField;
    @FXML private Rectangle colorRectangle;

    // instance variables for managing
    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private double alpha = 1;
    public void initialize() {
        // bind TextField values to corresponding Slider values

        redTextField.textProperty().bindBidirectional(redSlider.valueProperty(), NumberFormat.getNumberInstance());
        greenTextField.textProperty().bindBidirectional(greenSlider.valueProperty(), NumberFormat.getNumberInstance());
        blueTextField.textProperty().bindBidirectional(blueSlider.valueProperty(), NumberFormat.getNumberInstance());
        alphaTextField.textProperty().bindBidirectional(alphaSlider.valueProperty(), NumberFormat.getNumberInstance());

        // listeners that set Rectangle's fill based on Slider changes
        redSlider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> ov,
                                        Number oldValue, Number newValue) {
                        red = newValue.intValue();
                        colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
                    }
                }
        );
        greenSlider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> ov,
                                        Number oldValue, Number newValue) {
                        green = newValue.intValue();
                        colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
                    }
                }
        );
        blueSlider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> ov,
                                        Number oldValue, Number newValue) {
                        blue = newValue.intValue();
                        colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
                    }
                }
        );
        alphaSlider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> ov,
                                        Number oldValue, Number newValue) {
                        alpha = newValue.doubleValue();
                        colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
                    }
                }
        );
        redTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                redSlider.setValue(0);
            } else {
                try {
                    red = Integer.parseInt(newValue);
                    if (red < 0) red = 0;
                    if (red > 255) red = 255;
                    redSlider.setValue(red);
                } catch (NumberFormatException e) {
                    redTextField.setText(oldValue);
                }
            }
        });
        greenTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                alphaSlider.setValue(0);
        }
            try {
                green = Integer.parseInt(newValue);
                if (green < 0) green = 0;
                if (green > 255) green = 255;
                greenSlider.setValue(green);
            } catch (NumberFormatException e) {
                greenTextField.setText(oldValue);
            }
        });

        blueTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                blueSlider.setValue(0);
            } else {
                try {
                    blue = Integer.parseInt(newValue);
                    if (blue < 0) blue = 0;
                    if (blue > 255) blue = 255;
                    blueSlider.setValue(blue);
                } catch (NumberFormatException e) {
                    blueTextField.setText(oldValue);
                }
            }
        });

        alphaTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                alphaSlider.setValue(0);
            } else {
                try {
                    alpha = Double.parseDouble(newValue);
                    if (alpha < 0) alpha = 0;
                    if (alpha > 1.0) alpha = 1.0;
                    alphaSlider.setValue(alpha);
                } catch (NumberFormatException e) {
                    alphaTextField.setText(oldValue);
                }
            }
        });
    }

}
