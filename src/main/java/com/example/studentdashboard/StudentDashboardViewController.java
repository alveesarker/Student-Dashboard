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
        String strGender = "";
        if (femaleRadio.isSelected()) {
            strGender = "Female";
        } else strGender = "Male";

//      int id, String name, String gender, float cgpa, LocalDate dob, String major, String pw
        studList.add(
                new Student(
                        Integer.parseInt(idTextField.getText()),
                        nameTextField.getText(),
                        strGender,
                        Float.parseFloat(cgpaTextField.getText()),
                        dobDatePicker.getValue(),
                        majorComboBox.getValue(),
                        passTextField.getText()
                )
        );
        studentTableView.getItems().clear();

        for (Student s : studList) {
            studentTableView.getItems().add(s);
        }
    }

    @javafx.fxml.FXML
    public void handleLoadButtonOnAction() {
        studentTableView.getItems().clear();

        for (Student s : studList) {
            if (filteredMajorComboBox.getValue().equals(s.getMajor()) && filteredGenderComboBox.getValue().equals(s.getGender()) && Float.parseFloat(filteredCgpaTextField.getText()) <= s.getCgpa()) {
                studentTableView.getItems().add(s);
            }
        }
    }
}