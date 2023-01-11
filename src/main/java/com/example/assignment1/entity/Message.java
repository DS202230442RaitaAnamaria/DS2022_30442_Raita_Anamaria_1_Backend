package com.example.assignment1.entity;

import java.util.Date;

public class Message {

    private Date readingDate;
    private Double value;
    private Integer idDevice;

    public Date getReadingDate() {
        return readingDate;
    }

    public void setReadingDate(Date readingDate) {
        this.readingDate = readingDate;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Integer getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(Integer idDevice) {
        this.idDevice = idDevice;
    }

    public Message(Date readingDate, Double value, Integer idDevice) {
        this.readingDate = readingDate;
        this.value = value;
        this.idDevice = idDevice;
    }

    public Message() {
    }

    @Override
    public String toString() {
        return "Message{" +
                "readingDate=" + readingDate +
                ", value=" + value +
                ", idDevice=" + idDevice +
                '}'+ "\n\n";
    }
}