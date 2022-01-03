package pl.pwsztar;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import pl.pwsztar.App;
import pl.pwsztar.services.models.TablesDb;

import java.io.IOException;

public class SuccessfulSendViewController {
    @FXML
    public Button secondaryButton;

    @FXML
    public Label allTables;

    public void initialize() {
        allTables.setText(String.join(",", TablesDb.getAll()));
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("email_view");
    }
}