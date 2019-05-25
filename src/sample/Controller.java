package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private String httpLink = "http://";

    private String addressLink;

    private WebEngine engine;

    private double currentZoom = 1;

    private String lastSearch = "google.com";

    @FXML
    TextField addressBar;

    @FXML
    WebView web;

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

        addressLink = addressLink.toLowerCase();

        if (checkCom("google") || checkCom("youtube") || checkCom("amazon")
                || checkCom("stackoverflow") || checkCom("github")) {
            addressLink += ".com";
            engine.load(httpLink + addressLink);
        } else {
            engine.load(httpLink + addressLink);
        }

        lastSearch = httpLink + addressLink;
    }

    private boolean checkCom(String s) {
        return addressLink.equals(s);
    }

    @FXML
    public void reset() {
        engine.reload();
    }

    @FXML
    public void goFoward() {
        WebHistory history = web.getEngine().getHistory();
        try {
            history.go(1);
        } catch (Exception e) {
        }
    }

    @FXML
    public void goBack() {
        WebHistory history = web.getEngine().getHistory();
        try {
            history.go(-1);
        } catch (Exception e) {
        }
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
        engine = web.getEngine();
        web.setZoom(currentZoom);
        engine.load(httpLink + "interbb.blackboard.com/webapps/login/");
    }
}
