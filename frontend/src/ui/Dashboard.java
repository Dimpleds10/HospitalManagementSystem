package ui;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;

import javafx.scene.control.*;
import javafx.scene.layout.*;

import model.Patient;
import service.HospitalService;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Dashboard extends BorderPane {

    private Label totalPatientsLabel;

    private Label occupiedRoomsLabel;

    private Label availableRoomsLabel;

    private Label revenueLabel;

    private int patientCounter = 1001;

    private Set<Integer> occupiedRooms =
            new HashSet<>();

    public Dashboard() {

        setPadding(new Insets(15));

        // SIDEBAR

        VBox sidebar =
                new VBox(15);

        sidebar.setPadding(
                new Insets(25)
        );

        sidebar.setPrefWidth(330);

        sidebar.getStyleClass()
                .add("sidebar");

        // TITLE

        Label title =
                new Label(
                        "Hospital Management"
                );

        title.getStyleClass()
                .add("title");

        // INPUTS

        TextField nameField =
                new TextField();

        nameField.setPromptText(
                "Patient Name"
        );

        TextField ageField =
                new TextField();

        ageField.setPromptText(
                "Patient Age"
        );

        ComboBox<String> genderBox =
                new ComboBox<>();

        genderBox.getItems().addAll(
                "Male",
                "Female",
                "Other"
        );

        genderBox.setPromptText(
                "Gender"
        );

        ComboBox<String> bloodBox =
                new ComboBox<>();

        bloodBox.getItems().addAll(
                "A+",
                "A-",
                "B+",
                "B-",
                "AB+",
                "AB-",
                "O+",
                "O-"
        );

        bloodBox.setPromptText(
                "Blood Group"
        );

        ComboBox<String> diseaseBox =
                new ComboBox<>();

        diseaseBox.getItems().addAll(
                "Fever",
                "Diabetes",
                "Fracture",
                "Heart Disease"
        );

        diseaseBox.setPromptText(
                "Disease"
        );

        ComboBox<String> roomTypeBox =
                new ComboBox<>();

        roomTypeBox.getItems().addAll(
                "General Ward",
                "Private Room",
                "ICU"
        );

        roomTypeBox.setPromptText(
                "Room Type"
        );

        TextField searchField =
                new TextField();

        searchField.setPromptText(
                "Search Patient"
        );

        // BUTTONS

        Button addButton =
                new Button(
                        "Admit Patient"
                );

        Button dischargeButton =
                new Button(
                        "Discharge Patient"
                );

        addButton.setMaxWidth(
                Double.MAX_VALUE
        );

        dischargeButton.setMaxWidth(
                Double.MAX_VALUE
        );

        // ANALYTICS

        totalPatientsLabel =
                new Label();

        occupiedRoomsLabel =
                new Label();

        availableRoomsLabel =
                new Label();

        revenueLabel =
                new Label();

        // TABLE

        TableView<Patient> table =
                new TableView<>();

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        ObservableList<Patient> patientList =
                HospitalService.getPatients();

        table.setItems(patientList);

        // COLUMNS

        TableColumn<Patient, String>
                patientIdCol =
                new TableColumn<>(
                        "Patient ID"
                );

        patientIdCol.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue()
                                        .getPatientId()
                        )
        );

        TableColumn<Patient, String>
                nameCol =
                new TableColumn<>("Name");

        nameCol.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue()
                                        .getName()
                        )
        );

        TableColumn<Patient, Number>
                ageCol =
                new TableColumn<>("Age");

        ageCol.setCellValueFactory(
                data ->
                        new SimpleIntegerProperty(
                                data.getValue()
                                        .getAge()
                        )
        );

        TableColumn<Patient, String>
                genderCol =
                new TableColumn<>("Gender");

        genderCol.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue()
                                        .getGender()
                        )
        );

        TableColumn<Patient, String>
                bloodCol =
                new TableColumn<>("Blood");

        bloodCol.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue()
                                        .getBloodGroup()
                        )
        );

        TableColumn<Patient, String>
                diseaseCol =
                new TableColumn<>("Disease");

        diseaseCol.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue()
                                        .getDisease()
                        )
        );

        TableColumn<Patient, String>
                doctorCol =
                new TableColumn<>("Doctor");

        doctorCol.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue()
                                        .getDoctor()
                        )
        );

        TableColumn<Patient, Number>
                roomCol =
                new TableColumn<>("Room");

        roomCol.setCellValueFactory(
                data ->
                        new SimpleIntegerProperty(
                                data.getValue()
                                        .getRoomNumber()
                        )
        );

        TableColumn<Patient, String>
                roomTypeCol =
                new TableColumn<>("Room Type");

        roomTypeCol.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue()
                                        .getRoomType()
                        )
        );

        TableColumn<Patient, Number>
                billCol =
                new TableColumn<>("Final Bill");

        billCol.setCellValueFactory(
                data ->
                        new SimpleDoubleProperty(
                                data.getValue()
                                        .getFinalBill()
                        )
        );

        TableColumn<Patient, String>
                statusCol =
                new TableColumn<>("Status");

        statusCol.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue()
                                        .getStatus()
                        )
        );

        table.getColumns().addAll(
                patientIdCol,
                nameCol,
                ageCol,
                genderCol,
                bloodCol,
                diseaseCol,
                doctorCol,
                roomCol,
                roomTypeCol,
                billCol,
                statusCol
        );

        // ADD PATIENT

        addButton.setOnAction(e -> {

            try {

                Patient patient =
                        new Patient();

                patient.setPatientId(
                        "P-" + patientCounter++
                );

                patient.setName(
                        nameField.getText()
                );

                patient.setAge(
                        Integer.parseInt(
                                ageField.getText()
                        )
                );

                patient.setGender(
                        genderBox.getValue()
                );

                patient.setBloodGroup(
                        bloodBox.getValue()
                );

                patient.setDisease(
                        diseaseBox.getValue()
                );

                patient.setDoctor(
                        assignDoctor(
                                diseaseBox.getValue()
                        )
                );

                int room =
                        generateRoom();

                patient.setRoomNumber(room);

                occupiedRooms.add(room);

                patient.setRoomType(
                        roomTypeBox.getValue()
                );

                patient.setFinalBill(0);

                patient.setBillStatus(
                        "Pending"
                );

                patient.setStatus(
                        "Admitted"
                );

                patient.setAdmissionDate(
                        LocalDate.now()
                                .toString()
                );

                patient.setDischargeDate("-");

                // SEND TO CLOUD BACKEND

                HospitalService.addPatient(
                        patient
                );

                // WAIT FOR RENDER

                Thread.sleep(2500);

                // REFRESH TABLE

                ObservableList<Patient>
                        refreshedPatients =
                        HospitalService
                                .getPatients();

                table.setItems(
                        refreshedPatients
                );

                updateDashboard(
                        refreshedPatients
                );

                // CLEAR INPUTS

                clearFields(
                        nameField,
                        ageField,
                        genderBox,
                        bloodBox,
                        diseaseBox,
                        roomTypeBox
                );

                System.out.println(
                        "Patient Added Successfully"
                );

            } catch (Exception ex) {

                ex.printStackTrace();

                showAlert(
                        "Error Adding Patient"
                );
            }
        });

        // DISCHARGE

        dischargeButton.setOnAction(e -> {

            Patient selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected != null) {

                HospitalService
                        .dischargePatient(
                                selected
                        );

                ObservableList<Patient>
                        refreshedPatients =
                        HospitalService
                                .getPatients();

                table.setItems(
                        refreshedPatients
                );

                updateDashboard(
                        refreshedPatients
                );
            }
        });

        // SEARCH

        searchField.textProperty()
                .addListener((obs, oldVal, newVal) -> {

                    ObservableList<Patient>
                            allPatients =
                            HospitalService
                                    .getPatients();

                    table.getItems().clear();

                    for (Patient p : allPatients) {

                        if (p.getName()
                                .toLowerCase()
                                .contains(
                                        newVal.toLowerCase()
                                )) {

                            table.getItems()
                                    .add(p);
                        }
                    }
                });

        // SIDEBAR

        sidebar.getChildren().addAll(
                title,
                nameField,
                ageField,
                genderBox,
                bloodBox,
                diseaseBox,
                roomTypeBox,
                searchField,
                addButton,
                dischargeButton,
                totalPatientsLabel,
                occupiedRoomsLabel,
                availableRoomsLabel,
                revenueLabel
        );

        VBox centerLayout =
                new VBox(table);

        centerLayout.setPadding(
                new Insets(10)
        );

        VBox.setVgrow(
                table,
                Priority.ALWAYS
        );

        setLeft(sidebar);

        setCenter(centerLayout);

        updateDashboard(patientList);
    }

    // ROOM GENERATOR

    private int generateRoom() {

        int room = 101;

        while (occupiedRooms.contains(room)) {

            room++;
        }

        return room;
    }

    // DOCTOR ASSIGNMENT

    private String assignDoctor(
            String disease
    ) {

        switch (disease) {

            case "Heart Disease":
                return "Dr. Sharma";

            case "Diabetes":
                return "Dr. Mehta";

            case "Fracture":
                return "Dr. Rao";

            default:
                return "Dr. Verma";
        }
    }

    // DASHBOARD UPDATE

    private void updateDashboard(
            ObservableList<Patient> patients
    ) {

        totalPatientsLabel.setText(
                "Total Patients: "
                        + patients.size()
        );

        int occupied = 0;

        double revenue = 0;

        for (Patient p : patients) {

            if (p.getStatus() != null
                    && p.getStatus()
                    .equals("Admitted")) {

                occupied++;
            }

            revenue += p.getFinalBill();
        }

        occupiedRoomsLabel.setText(
                "Occupied Rooms: "
                        + occupied
        );

        availableRoomsLabel.setText(
                "Available Rooms: "
                        + (50 - occupied)
        );

        revenueLabel.setText(
                "Revenue: ₹"
                        + revenue
        );
    }

    // CLEAR FIELDS

    private void clearFields(
            TextField nameField,
            TextField ageField,
            ComboBox<String> genderBox,
            ComboBox<String> bloodBox,
            ComboBox<String> diseaseBox,
            ComboBox<String> roomTypeBox
    ) {

        nameField.clear();

        ageField.clear();

        genderBox.setValue(null);

        bloodBox.setValue(null);

        diseaseBox.setValue(null);

        roomTypeBox.setValue(null);
    }

    // ALERT

    private void showAlert(
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.WARNING
                );

        alert.setContentText(message);

        alert.show();
    }
}