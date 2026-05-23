package ui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import model.Patient;
import service.HospitalService;

public class PatientForm extends VBox {

    public PatientForm(TableView<Patient> table) {

        setSpacing(15);
        setPadding(new Insets(20));

        TextField nameField = new TextField();
        nameField.setPromptText("Enter Patient Name");

        TextField ageField = new TextField();
        ageField.setPromptText("Enter Age");

        ComboBox<String> diseaseBox = new ComboBox<>();

        diseaseBox.getItems().addAll(
                "Fever",
                "Heart Disease",
                "Diabetes",
                "Fracture"
        );

        diseaseBox.setPromptText("Select Disease");

        Button addButton = new Button("Add Patient");

        addButton.setOnAction(e -> {

    
            String name = nameField.getText();

    
            int age = Integer.parseInt(ageField.getText());

    
            String disease = diseaseBox.getValue();

    
            Patient patient = new Patient();

    
            patient.setName(name);

    
            patient.setAge(age);

    
            patient.setDisease(disease);

    
            HospitalService.addPatient(patient);

    
            table.getItems().add(patient);

    
            nameField.clear();

    
            ageField.clear();

    
            diseaseBox.setValue(null);

    
            System.out.println("Patient Added Successfully!");

        });

        getChildren().addAll(
                nameField,
                ageField,
                diseaseBox,
                addButton
        );
    }
}