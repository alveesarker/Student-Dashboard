package com.example.studentdashboard;

import java.time.LocalDate;

public class Student {
    private int id;
    private String name;
    private String gender;
    private float cgpa;
    private LocalDate dob;
    private String major;
    private String pw;


    public Student() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public float getCgpa() {
        return cgpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", cgpa=" + cgpa +
                ", dob=" + dob +
                ", major='" + major + '\'' +
                ", pw='" + pw + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getMajor() {
        return major;
    }

    public String getPw() {
        return pw;
    }

    public Student(int id, String name, String gender, float cgpa, LocalDate dob, String major, String pw) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.cgpa = cgpa;
        this.dob = dob;
        this.major = major;
        this.pw = pw;
    }


}
