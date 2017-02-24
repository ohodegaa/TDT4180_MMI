package Oving_2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by olehakon95 on 22/02/2017.
 */
public class AppointmentController implements Initializable {
    @FXML
    TextArea reason;
    @FXML
    TextField room;

    @FXML
    DatePicker date;

    @FXML
    ChoiceBox fromHour;
    @FXML
    ChoiceBox fromMinute;

    @FXML
    ChoiceBox toHour;
    @FXML
    ChoiceBox toMinute;

    @FXML
    CheckBox repetitionBox;
    @FXML
    TextField repeatDays;
    @FXML
    DatePicker repeatUntil;

    @FXML
    Label timeError;
    @FXML
    Label dateError;
    @FXML
    Label roomError;

    @FXML
    Button submitButton;

    @FXML
    VBox repetitionSettings;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List hours = new ArrayList();
        for (int i = 0; i < 23; i++) {
            hours.add(Integer.toString(i));
        }

        List minutes = new ArrayList();
        for (int i = 0; i < 59; i += 5) {
            minutes.add(Integer.toString(i));
        }
        fromHour.getItems().addAll(hours);
        fromMinute.getItems().addAll(minutes);
        toHour.getItems().addAll(hours);
        toMinute.getItems().addAll(minutes);

        date.setValue(LocalDate.now());
        repetitionSettings.setDisable(true);
        repetitionSettings.setVisible(false);
        submitButton.setDisable(true);

        dateError.setVisible(false);
        timeError.setVisible(false);
        roomError.setVisible(false);

    }

    private boolean ready_for_submit() {
        if (!(reason.getText() != null && room.getText() != null && fromHour.getValue() != null && fromMinute.getValue() != null
                && toHour.getValue() != null && toMinute.getValue() != null)) {
            return false;
        }

        if (fromHour.getValue() != null && fromMinute.getValue() != null && toHour.getValue() != null && toMinute.getValue() != null) {
            LocalTime fromTime = LocalTime.of(Integer.parseInt(fromHour.getValue().toString()), Integer.parseInt(fromMinute.getValue().toString()));
            LocalTime toTime = LocalTime.of(Integer.parseInt(toHour.getValue().toString()), Integer.parseInt(toMinute.getValue().toString()));
            System.out.println(fromTime.toString());
            System.out.println(toTime.toString());
            if (fromTime.isAfter(toTime)) {
                timeError.setVisible(true);
                return false;
            }
        }

        if (!(!repetitionBox.isSelected() || (repetitionBox.isSelected() && repeatDays.getText() != null && repeatUntil.getValue() != null))) {
            return false;
        }

        if (repetitionBox.isSelected() && date.getValue() != null && repeatUntil.getValue() != null) {
            LocalDate repeatStart = date.getValue();
            LocalDate repeateEnd = repeatUntil.getValue();

            if (repeatStart.isAfter(repeateEnd)) {
                dateError.setVisible(true);
                return false;
            }
        }

        if (!(room.getText().matches("([\\-\\w ]+) \\d+"))) {
            System.out.println("Wrong room format");
            roomError.setVisible(true);
            return false;
        }


        timeError.setVisible(false);
        dateError.setVisible(false);
        roomError.setVisible(false);
        return true;


    }


    private void set_submit() {
        if (ready_for_submit()) {
            submitButton.setDisable(false);
        } else {
            submitButton.setDisable(true);
        }
    }

    @FXML
    private void reason_changed() {
        set_submit();
    }

    @FXML
    private void room_changed() {
        set_submit();
    }

    @FXML
    private void date_changed() {
        set_submit();
    }

    @FXML
    private void fromHour_changed() {
        set_submit();
    }

    @FXML
    private void fromMinute_changed() {
        set_submit();
    }

    @FXML
    private void toHour_changed() {
        set_submit();
    }

    @FXML
    private void toMinute_changed() {
        set_submit();
    }

    @FXML
    private void repetitionBox_changed() {
        if (repetitionBox.isSelected()) {
            repetitionSettings.setDisable(false);
            repetitionSettings.setVisible(true);
        } else {
            repetitionSettings.setDisable(true);
            repetitionSettings.setVisible(false);
        }
    }

    @FXML
    private void repeatDays_changed() {
        set_submit();
    }

    @FXML
    private void repeatUntil_changed() {
        set_submit();
    }

    @FXML
    private void submit() {
        if (ready_for_submit()) {
            submitButton.setText("Submitted");
            update_model();
        } else {
            submitButton.setText("Submit");
        }
    }


    Appointment testModel = new Appointment();

    public void update_model() {
        update_reason();
        update_room();
        update_date();
        update_from();
        update_to();
        update_repeatDays();
        update_repeatUntil();

    }


    private void update_reason() {
        testModel.setFormal(reason.getText());
        System.out.println("AppointmentController.update_reason");
    }

    private void update_room() {
        testModel.setRom(room.getText());
        System.out.println("AppointmentController.update_room");
    }

    private void update_date() {
        testModel.setDato(date.getValue());
        System.out.println("AppointmentController.update_date");
    }

    private void update_from() {
        LocalTime from = LocalTime.of(Integer.parseInt(fromHour.getValue().toString()), Integer.parseInt(fromMinute.getValue().toString()));
        testModel.setFra(from);
        System.out.println("AppointmentController.update_from");
    }

    private void update_to() {
        LocalTime to = LocalTime.of(Integer.parseInt(toHour.getValue().toString()), Integer.parseInt(toMinute.getValue().toString()));
        testModel.setTil(to);
        System.out.println("AppointmentController.update_to");
    }

    private void update_repeatDays() {
        if (repetitionBox.isSelected() && !repeatDays.getText().isEmpty()) {
            testModel.setRepetisjon(Integer.parseInt(repeatDays.getText()));
            System.out.println("AppointmentController.update_repeatDays");
        }
    }

    private void update_repeatUntil() {
        if (repetitionBox.isSelected() && repeatUntil.getValue() != null) {
            testModel.setSlutt(repeatUntil.getValue());
            System.out.println("AppointmentController.update_repeatUntil");
        }
    }
}
