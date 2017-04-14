package com.oasis.factory;

import com.oasis.controller.Controller;
import com.oasis.ui.UI;
import com.oasis.ui.UIName;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.HashMap;

public class UIFactory {
    private static final HashMap<UIName, UI> UI_HASH_MAP= new HashMap<>();

    public static UI getUI(UIName name) {
        return UIFactory.UI_HASH_MAP.get(name);
    }

    public static void initializeAllUIs() {
        for (UIName uiName : UIName.values()) {
            try {
                loadUI(uiName, uiName.getLocation());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static UI loadUI(UIName uiName, String location) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UIFactory.class.getResource(location));
        Parent parent = fxmlLoader.load();
        Controller controller = fxmlLoader.getController();
        UI ui = new UI(parent, controller);
        UIFactory.UI_HASH_MAP.put(uiName, ui);
        return ui;
    }
}