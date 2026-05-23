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

        VBox sidebar = new VBox(15);

        sidebar.setPadding(new Insets(25));

        sidebar.setPrefWidth(330);

        sidebar.getStyleClass().add("sidebar");

        // TITLE

        Label title =
                new Label(
                        "Hospital Management"
                );

        title.getStyleClass().add("title");

        // NAME

        TextField nameField =
                new TextField();

        nameField.setPromptText(
                "Patient Name"
        );

        // AGE

        TextField ageField =
                new TextField();

        ageField.setPromptText(
                "Patient Age"
        );

        // GENDER

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

        // BLOOD GROUP

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

        // DISEASE

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

        // ROOM TYPE

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

        // SEARCH

        TextField searchField =
                new TextField();

        searchField.setPromptText(
                "Search Patient"
        );

        // BUTTONS

        Button addButton =
                new Button("Admit Patient");

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

        // ANALYTICS LABELS

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

        // PATIENT ID

        TableColumn<Patient, String> patientIdCol =
                new TableColumn<>("Patient ID");

        patientIdCol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue()
                                .getPatientId()
                ));

        // NAME

        TableColumn<Patient, String> nameCol =
                new TableColumn<>("Name");

        nameCol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue()
                                .getName()
                ));

        // AGE

        TableColumn<Patient, Number> ageCol =
                new TableColumn<>("Age");

        ageCol.setCellValueFactory(data ->
                new SimpleIntegerProperty(
                        data.getValue()
                                .getAge()
                ));

        // GENDER

        TableColumn<Patient, String> genderCol =
                new TableColumn<>("Gender");

        genderCol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue()
                                .getGender()
                ));

        // BLOOD GROUP

        TableColumn<Patient, String> bloodCol =
                new TableColumn<>("Blood");

        bloodCol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue()
                                .getBloodGroup()
                ));

        // DISEASE

        TableColumn<Patient, String> diseaseCol =
                new TableColumn<>("Disease");

        diseaseCol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue()
                                .getDisease()
                ));

        // DOCTOR

        TableColumn<Patient, String> doctorCol =
                new TableColumn<>("Doctor");

        doctorCol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue()
                                .getDoctor()
                ));

        // ROOM

        TableColumn<Patient, Number> roomCol =
                new TableColumn<>("Room");

        roomCol.setCellValueFactory(data ->
                new SimpleIntegerProperty(
                        data.getValue()
                                .getRoomNumber()
                ));

        // ROOM TYPE

        TableColumn<Patient, String> roomTypeCol =
                new TableColumn<>("Room Type");

        roomTypeCol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue()
                                .getRoomType()
                ));

        // BILL

        TableColumn<Patient, Number> billCol =
                new TableColumn<>("Final Bill");

        billCol.setCellValueFactory(data ->
                new SimpleDoubleProperty(
                        data.getValue()
                                .getFinalBill()
                ));

        // STATUS

        TableColumn<Patient, String> statusCol =
                new TableColumn<>("Status");

        statusCol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue()
                                .getStatus()
                ));

        // ADMISSION DATE

        TableColumn<Patient, String> admissionCol =
                new TableColumn<>("Admission");

        admissionCol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue()
                                .getAdmissionDate()
                ));

        // DISCHARGE DATE

        TableColumn<Patient, String> dischargeCol =
                new TableColumn<>("Discharge");

        dischargeCol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue()
                                .getDischargeDate()
                ));

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
                statusCol,
                admissionCol,
                dischargeCol
        );

        // ADMIT PATIENT

        addButton.setOnAction(e -> {

            try {

                if (nameField.getText().isEmpty()
                        || ageField.getText().isEmpty()
                        || genderBox.getValue() == null
                        || bloodBox.getValue() == null
                        || diseaseBox.getValue() == null
                        || roomTypeBox.getValue() == null) {

                    showAlert(
                            "Please fill all fields"
                    );

                    return;
                }

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

                patient.setRoomNumber(
                        room
                );

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

                patient.setDischargeDate(
                        "-"
                );

                HospitalService
                        .addPatient(patient);

                patientList.clear();

                patientList.addAll(
                        HospitalService
                                .getPatients()
                );

                updateDashboard(
                        patientList
                );

                clearFields(
                        nameField,
                        ageField,
                        genderBox,
                        bloodBox,
                        diseaseBox,
                        roomTypeBox
                );

            } catch (Exception ex) {

                showAlert(
                        "Invalid Input"
                );
            }
        });

        // DISCHARGE PATIENT

        dischargeButton.setOnAction(e -> {

            Patient selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected != null
                    && selected.getStatus()
                    .equals("Admitted")) {

                occupiedRooms.remove(
                        selected.getRoomNumber()
                );

                selected.setStatus(
                        "Discharged"
                );

                selected.setDischargeDate(
                        LocalDate.now()
                                .toString()
                );

                selected.setBillStatus(
                        "Paid"
                );

                selected.setFinalBill(
                        calculateBill(
                                selected
                        )
                );

                HospitalService
                        .dischargePatient(
                                selected
                        );

                patientList.clear();

                patientList.addAll(
                        HospitalService
                                .getPatients()
                );

                updateDashboard(
                        patientList
                );
            }
        });

        // SEARCH

        searchField.textProperty()
                .addListener((obs, oldVal, newVal) -> {

                    ObservableList<Patient> allPatients =
                            HospitalService
                                    .getPatients();

                    table.getItems().clear();

                    for (Patient p : allPatients) {

                        if (p.getName()
                                .toLowerCase()
                                .contains(
                                        newVal.toLowerCase()
                                )) {

                            table.getItems().add(p);
                        }
                    }
                });

        // SIDEBAR ITEMS

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

    // BILL CALCULATION

    private double calculateBill(
            Patient patient
    ) {

        double roomCharge = 0;

        switch (patient.getRoomType()) {

            case "ICU":
                roomCharge = 12000;
                break;

            case "Private Room":
                roomCharge = 5000;
                break;

            default:
                roomCharge = 2000;
        }

        double diseaseCharge = 0;

        switch (patient.getDisease()) {

            case "Heart Disease":
                diseaseCharge = 25000;
                break;

            case "Diabetes":
                diseaseCharge = 12000;
                break;

            case "Fracture":
                diseaseCharge = 18000;
                break;

            default:
                diseaseCharge = 5000;
        }

        return roomCharge
                + diseaseCharge
                + 3000;
    }

    // UPDATE DASHBOARD

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

            if (p.getStatus()
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