package com.example.studentdashboard;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDashboardViewController {
    @javafx.fxml.FXML
    private TextField idTextField;
    @javafx.fxml.FXML
    private TextField cgpaTextField;
    @javafx.fxml.FXML
    private ToggleGroup gender;
    @javafx.fxml.FXML
    private TextField nameTextField;
    @javafx.fxml.FXML
    private TableColumn<Student, String> genderColumn;
    @javafx.fxml.FXML
    private TableColumn<Student, LocalDate> dobColumn;
    @javafx.fxml.FXML
    private PasswordField passTextField;
    @javafx.fxml.FXML
    private ComboBox<String> filteredMajorComboBox;
    @javafx.fxml.FXML
    private RadioButton maleRadio;
    @javafx.fxml.FXML
    private ComboBox<String> majorComboBox;
    @javafx.fxml.FXML
    private TableColumn<Student, String> nameColumn;
    @javafx.fxml.FXML
    private TableView<Student> studentTableView;
    @javafx.fxml.FXML
    private RadioButton femaleRadio;
    @javafx.fxml.FXML
    private TextField filteredCgpaTextField;
    @javafx.fxml.FXML
    private ComboBox<String> filteredGenderComboBox;
    @javafx.fxml.FXML
    private TableColumn<Student, Float> cgpaColumn;
    @javafx.fxml.FXML
    private TableColumn<Student, String> majorColumn;
    @javafx.fxml.FXML
    private DatePicker dobDatePicker;
    @javafx.fxml.FXML
    private TableColumn<Student, Integer> idColumn;

    private final List<Student> studList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        majorComboBox.getItems().addAll("CSE", "EEE", "MIS", "Finance");
        filteredGenderComboBox.getItems().addAll("Male", "Female");
        filteredMajorComboBox.getItems().addAll("CSE", "EEE", "MIS", "Finance");

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        cgpaColumn.setCellValueFactory(new PropertyValueFactory<>("cgpa"));
        majorColumn.setCellValueFactory(new PropertyValueFactory<>("major"));
        dobColumn.setCellValueFactory(new PropertyValueFactory<>("dob"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
    }

    @javafx.fxml.FXML
    public void handleAddButtonOnAction() {
        // Validate input fields
        if (idTextField.getText().isEmpty() || nameTextField.getText().isEmpty() ||
                cgpaTextField.getText().isEmpty() || passTextField.getText().isEmpty() ||
                dobDatePicker.getValue() == null || majorComboBox.getValue() == null ||
                (!maleRadio.isSelected() && !femaleRadio.isSelected())) {

            alertShow("Please fill in all fields.");
            return;
        }

        // Validate ID (must be a number)
        int id;
        try {
            id = Integer.parseInt(idTextField.getText());
            if (id <= 0) {
                alertShow("ID must be a positive integer.");
                return;
            }
        } catch (NumberFormatException e) {
            alertShow("Invalid ID. Please enter a valid integer.");
            return;
        }

        // Check for duplicate ID
        for (Student s : studList) {
            if (id == s.getId()) {
                alertShow("Duplicate ID. Please enter a new ID.");
                return;
            }
        }

        // Validate CGPA (must be a valid float between 0 and 4)
        float cgpa;
        try {
            cgpa = Float.parseFloat(cgpaTextField.getText());
            if (cgpa < 0.0 || cgpa > 4.0) {
                alertShow("CGPA must be between 0.0 and 4.0.");
                return;
            }
        } catch (NumberFormatException e) {
            alertShow("Invalid CGPA. Please enter a valid number.");
            return;
        }

        // Determine gender
        String strGender = femaleRadio.isSelected() ? "Female" : "Male";

        // Add student to list
        studList.add(new Student(id, nameTextField.getText(), strGender, cgpa,
                dobDatePicker.getValue(), majorComboBox.getValue(), passTextField.getText()));

        // Refresh table
        studentTableView.getItems().clear();
        studentTableView.getItems().addAll(studList);
    }

    @javafx.fxml.FXML
    public void handleLoadButtonOnAction() {
        studentTableView.getItems().clear();

        for (Student s : studList) {
            if (filteredMajorComboBox.getValue().equals(s.getMajor()) &&
                    filteredGenderComboBox.getValue().equals(s.getGender()) &&
                    Float.parseFloat(filteredCgpaTextField.getText()) <= s.getCgpa()) {

                studentTableView.getItems().add(s);
            }
        }
    }

    public void alertShow(String alertMessage) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText(alertMessage);
        a.showAndWait();
    }
}
