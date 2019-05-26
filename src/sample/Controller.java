package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import org.controlsfx.control.textfield.TextFields;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    private String httpLink = "https://";

    private String addressLink;

    private WebEngine engine;

    private double currentZoom = 1.25;

    @FXML
    TextField addressBar;

    @FXML
    BorderPane root;

    @FXML
    WebView web;

    private String[] presetWords = {
            "ebay.com", "youtube.com", "amazon.com", "athleanonline.com/auth/login",
            "github.com", "stackoverflow.com"
    };

    private LinkedList<String> possibleWord = new LinkedList<>();

    @FXML
    public void selectText() {
        addressBar.selectAll();
    }

    @FXML
    public void enter(KeyEvent key) {
        if (key.getCode().toString().equals("ENTER")) {
            load();
        }
    }

    @FXML
    public void go(ActionEvent actionEvent) {
        load();
    }

    private void load() {
        addressLink = addressBar.getText();
        //addressLink = addressLink.toLowerCase();
        if (addressLink.contains(httpLink)) {
            engine.load(addressLink);
        } else {
            engine.load(httpLink + addressLink);
        }
        save(engine.getLocation());
    }

    private void save(String s) {
        addressBar.clear();
        addressBar.setText(s);
        possibleWord.add(s);
    }

    @FXML
    public void reset() {
        engine.reload();
        save(engine.getLocation());
    }

    @FXML
    public void goFoward() {
        WebHistory history = web.getEngine().getHistory();
        try {
            history.go(1);
            addressBar.clear();
            addressBar.setText(engine.getLocation());
        } catch (Exception ignored) {}
    }

    @FXML
    public void goBack() {
        WebHistory history = web.getEngine().getHistory();
        try {
            history.go(-1);
            addressBar.clear();
            addressBar.setText(engine.getLocation());
        } catch (Exception ignored) {}
    }

    @FXML
    public void zoom(ActionEvent event) {
        web.setZoom(currentZoom += 0.25);
    }


    @FXML
    public void unZoom(ActionEvent event) {
        web.setZoom(currentZoom -= 0.25);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.setImplicitExit(false);
        engine = web.getEngine();
        web.setZoom(currentZoom);
        engine.load(httpLink + "interbb.blackboard.com/webapps/login/");
        addressBar.setText(engine.getLocation());

        possibleWord.addAll(Arrays.asList(presetWords));

        TextFields.bindAutoCompletion(addressBar, possibleWord);
    }
}
