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

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    private String httpLink = "https://";

    private String addressLink;

    private WebEngine engine;

    private double currentZoom = 1.25;

    private String home;

    @FXML
    TextField addressBar;

    @FXML
    BorderPane root;

    @FXML
    WebView web;

    private void setHome(String home) {
        if (home.contains(httpLink)) {
            this.home = home;
        } else {
            this.home = httpLink + home;
        }
    }

    private String[] presetWords = {
            "ebay.com", "youtube.com", "amazon.com", "athleanonline.com/auth/login",
            "github.com", "stackoverflow.com", "google.com", "https://10.0.0.85:8080/"
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
    public void go() {
        load();
    }

    private void load() {
        addressLink = addressBar.getText();

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
    public void goForward() {
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
    public void zoom() {
        web.setZoom(currentZoom += 0.25);
    }


    @FXML
    public void unZoom() {
        web.setZoom(currentZoom -= 0.25);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addressBar.setOnKeyPressed(e -> {if (e.getCode().toString().equals("ENTER")) load();});
        Platform.setImplicitExit(false);
        engine = web.getEngine();
        web.setZoom(currentZoom);
        setHome("https://interbb.blackboard.com/webapps/login/");
        //setHome("https://10.0.0.85:8080/");

        engine.load(home);
        addressBar.setText(engine.getLocation());

        possibleWord.addAll(Arrays.asList(presetWords));

        TextFields.bindAutoCompletion(addressBar, possibleWord);
    }
}
