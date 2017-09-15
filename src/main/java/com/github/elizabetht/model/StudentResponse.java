package com.github.elizabetht.model;

public class StudentResponse {

    private String status;

    private Boolean studentResult;

    private Error error;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getStudentResult() {
        return studentResult;
    }

    public void setStudentResult(Boolean studentResult) {
        this.studentResult = studentResult;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
