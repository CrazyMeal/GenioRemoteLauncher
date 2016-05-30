package gui;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 * Created by KVN on 29/05/2016.
 */
public class ApplicationViewController {
    @FXML
    private TreeView<String> interfacesTreeView;

    // the initialize method is automatically invoked by the FXMLLoader - it's magic
    public void initialize() {
        loadTreeItems("Interface Gescom - Rime", "Extraction carnet de commande", "Gescom 50D");
    }

    // loads some strings into the tree in the application UI.
    public void loadTreeItems(String... rootItems) {
        TreeItem<String> root = new TreeItem<String>("Interfaces disponibles");
        root.setExpanded(true);
        for (String itemString : rootItems) {
            root.getChildren().add(new TreeItem<String>(itemString));
        }

        interfacesTreeView.setRoot(root);
    }
}
