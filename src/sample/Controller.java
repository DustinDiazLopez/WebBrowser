package sample;

import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class Controller implements Initializable {

    private String httpLink = "http://";

    private String addressLink;

    private WebEngine engine;

    @FXML
    TextField addressBar;

    @FXML
    WebView web;

    public void go(ActionEvent actionEvent) {
        addressLink = addressBar.getText();

        if (addressLink.equals("")) {
            robot();
        } else {
            engine.load(httpLink + addressLink);
        }
    }

    private void username() {
        tab();
        tab();
        try {
            Robot robot2 = new Robot();
            String text = "A00548394";
            StringSelection stringSelection = new StringSelection(text);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, stringSelection);

            robot2.keyPress(KeyEvent.VK_CONTROL);
            robot2.keyPress(KeyEvent.VK_V);
            robot2.keyRelease(KeyEvent.VK_V);
            robot2.keyRelease(KeyEvent.VK_CONTROL);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        tab();
    }


    private void password() {
        try {
            Robot robot = new Robot();
            String text2 = "7874782095";
            StringSelection stringSelection2 = new StringSelection(text2);
            Clipboard clipboard2 = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard2.setContents(stringSelection2, stringSelection2);

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

//            robot.keyPress(KeyEvent.VK_ENTER);
//            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private void tab(){
        try {
            Robot robot3 = new Robot();
            robot3.keyPress(KeyEvent.VK_TAB);
            robot3.keyRelease(KeyEvent.VK_TAB);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private void robot() {
        username();
        password();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        engine = web.getEngine();
        engine.load(httpLink + "interbb.blackboard.com/webapps/login/");

    }
}
