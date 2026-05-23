package com.hms.backend.dto;

public class ApiResponse<T> {

    private boolean success;

    private String message;

    private int totalPatients;

    private T data;

    // CONSTRUCTOR

    public ApiResponse(
            boolean success,
            String message,
            int totalPatients,
            T data
    ) {

        this.success = success;

        this.message = message;

        this.totalPatients = totalPatients;

        this.data = data;
    }

    // GETTERS

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public int getTotalPatients() {
        return totalPatients;
    }

    public T getData() {
        return data;
    }

    // SETTERS

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTotalPatients(int totalPatients) {
        this.totalPatients = totalPatients;
    }

    public void setData(T data) {
        this.data = data;
    }
}