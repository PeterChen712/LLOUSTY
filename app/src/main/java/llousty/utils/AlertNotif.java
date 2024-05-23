package llousty.Utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertNotif {
    private static Alert alert;

    private static void setTitle(Alert alert, String title) {
        if (alert != null) {
            alert.setTitle(title);
        }
    }

    public static void setAlertWarning(String title, String headerText, String contentText) {
        alert = new Alert(AlertType.WARNING);
        setTitle(alert, title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public void setAlertError(String title, String headerText, String contentText) {
        alert = new Alert(AlertType.ERROR);
        setTitle(alert, title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public void setAlertInformation(String title, String headerText, String contentText) {
        alert = new Alert(AlertType.INFORMATION);
        setTitle(alert, title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public void setAlertConfirmation(String title, String headerText, String contentText) {
        alert = new Alert(AlertType.CONFIRMATION);
        setTitle(alert, title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public boolean setAlertConfirmationWithResult(String title, String headerText, String contentText) {
        alert = new Alert(AlertType.CONFIRMATION);
        setTitle(alert, title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
        return alert.getResult().getButtonData().isDefaultButton();
    }

    public void setCustomAlert(String title, AlertType alertType, String headerText, String contentText) {
        alert = new Alert(alertType);
        setTitle(alert, title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
