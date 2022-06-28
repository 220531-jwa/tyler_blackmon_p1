package dev.blackmon.models;

import dev.blackmon.enums.ReimbursementType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Request {
    private int id;
    private int requesterId;
    private int fmId;
    private BigDecimal amount;
    private ReimbursementType type;
    private boolean approved;
    private LocalDate eventDate;
    private LocalTime eventTime;
    private String description;

    public Request() {
        super();
    }

    public Request(int id, int requesterId, int fmId, BigDecimal amount, ReimbursementType type, boolean approved, LocalDate eventDate, LocalTime eventTime, String description) {
        this.id = id;
        this.requesterId = requesterId;
        this.fmId = fmId;
        this.amount = amount;
        this.type = type;
        this.approved = approved;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getFmId() {
        return fmId;
    }

    public void setFmId(int fmId) {
        this.fmId = fmId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(int requesterId) {
        this.requesterId = requesterId;
    }

    public ReimbursementType getType() {
        return type;
    }

    public void setType(ReimbursementType type) {
        this.type = type;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public LocalTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalTime eventTime) {
        this.eventTime = eventTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
