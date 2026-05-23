package model;

public class Patient {

    private int id;

    private String patientId;

    private String name;

    private int age;

    private String gender;

    private String bloodGroup;

    private String disease;

    private String doctor;

    private int roomNumber;

    private String roomType;

    private double finalBill;

    private String billStatus;

    private String status;

    private String admissionDate;

    private String dischargeDate;

    // DEFAULT CONSTRUCTOR

    public Patient() {
    }

    // GETTERS

    public int getId() {
        return id;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getDisease() {
        return disease;
    }

    public String getDoctor() {
        return doctor;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getFinalBill() {
        return finalBill;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public String getStatus() {
        return status;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public String getDischargeDate() {
        return dischargeDate;
    }

    // SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setFinalBill(double finalBill) {
        this.finalBill = finalBill;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public void setDischargeDate(String dischargeDate) {
        this.dischargeDate = dischargeDate;
    }
}