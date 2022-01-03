package pl.pwsztar;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.HTMLEditor;
import pl.pwsztar.App;
import pl.pwsztar.model.MailMessage;
import pl.pwsztar.model.NewsletterRecipient;
import pl.pwsztar.services.MailService;
import pl.pwsztar.services.models.NewsletterDb;
import pl.pwsztar.utils.validators.EmailValidator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmailViewController {

    @FXML
    public TableView<NewsletterRecipient> tableView;
    @FXML
    public TextField emailTitle;
    @FXML
    public TextField newEmail;
    @FXML
    public HTMLEditor emailEditor;

    private List<NewsletterRecipient> newsletterRecipients = new ArrayList<>();

    public void initialize() {
        setUpTableCollumns();
        fetchData();

        tableView.setOnMouseClicked(event -> onListItemClick());
    }

    private void onListItemClick() {
            if(!tableView.getSelectionModel().getSelectedCells().isEmpty()) {
                int id = tableView.getSelectionModel().getSelectedCells().get(0).getRow();
                NewsletterRecipient newsletterRecipient = newsletterRecipients.get(id);
                newsletterRecipient.setTermsAccepted(!newsletterRecipient.isTermsAccepted());
                newsletterRecipients.set(id, newsletterRecipient);
                tableView.refresh();

                new Thread(() -> updateRecipientTerms(newsletterRecipient)).start();
            }
        }

    private void fetchData() {
        tableView.getItems().clear();
        tableView.refresh();

        newsletterRecipients = NewsletterDb.getAll();
        for(NewsletterRecipient newsletterRecipient : newsletterRecipients) {
            tableView.getItems().add(newsletterRecipient);
        }
    }

    private void updateRecipientTerms(NewsletterRecipient newsletterRecipient) {
        NewsletterDb.update(newsletterRecipient);
    }

    private void removeItem(NewsletterRecipient newsletterRecipient) {
        tableView.getItems().remove(newsletterRecipient);
        new Thread(() -> NewsletterDb.remove(newsletterRecipient)).start();
    }

    @FXML
    private void addNewEmail() {
        String email = newEmail.getText();
        if(EmailValidator.isValid(email)) {
            NewsletterDb.insert(email);
            fetchData();
        }
    }

    @FXML
    private void sendMessage() throws IOException {
        List<String> newsletterRecipientsMails = new ArrayList<>();
        for(NewsletterRecipient recipient : newsletterRecipients) {
            if( recipient.isTermsAccepted() ) {
                newsletterRecipientsMails.add(recipient.getEmail());
            }
        }
        MailMessage mailMessage = new MailMessage();
        mailMessage.setSubject(emailTitle.getText());
        mailMessage.setText(emailEditor.getHtmlText());
        mailMessage.setRecipients(newsletterRecipientsMails);

        MailService.sendMessage(mailMessage);

        App.setRoot("succesfull_send_view");
    }

    @FXML
    private void onExit() {
        Platform.exit();
    }

    private void setUpTableCollumns() {
        TableColumn<NewsletterRecipient, NewsletterRecipient> deleteColumn = new TableColumn<>(" ");
        deleteColumn.setCellValueFactory(
          param -> new ReadOnlyObjectWrapper<>(param.getValue())
        );
        deleteColumn.setCellFactory(param -> new TableCell<>() {
            final Button deleteButton = new Button("X");
            @Override
            protected void updateItem(NewsletterRecipient newsletterRecipient, boolean empty) {
                super.updateItem(newsletterRecipient, empty);
                if (newsletterRecipient == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(deleteButton);
                deleteButton.setOnAction(
                  event -> removeItem(newsletterRecipient)
                );
            }
        });`

        TableColumn<NewsletterRecipient, String> column1 = new TableColumn<>("ID");
        column1.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<NewsletterRecipient, String> column2 = new TableColumn<>("Email");
        column2.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<NewsletterRecipient, String> column3 = new TableColumn<>("Wyślij do");
        column3.setCellValueFactory(new PropertyValueFactory<>("termsAcceptedString"));

        tableView.getColumns().add(deleteColumn);
        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
    }
}
