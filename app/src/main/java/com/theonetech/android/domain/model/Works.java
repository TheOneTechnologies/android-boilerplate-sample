package com.theonetech.android.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Works {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("userName")
    @Expose
    private String userName;

    @SerializedName("subjectName")
    @Expose
    private String subjectName;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("schoolId")
    @Expose
    private String schoolId;

    @SerializedName("standardId")
    @Expose
    private String standardId;

    @SerializedName("subjectId")
    @Expose
    private String subjectId;

    @SerializedName("boardId")
    @Expose
    private String boardId;

    @SerializedName("divisionId")
    @Expose
    private String divisionId;

    @SerializedName("dueDate")
    @Expose
    private String dueDate;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("hasAttachment")
    @Expose
    private String hasAttachment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getStandardId() {
        return standardId;
    }

    public void setStandardId(String standardId) {
        this.standardId = standardId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHasAttachment() {
        return hasAttachment;
    }

    public void setHasAttachment(String hasAttachment) {
        this.hasAttachment = hasAttachment;
    }
}
