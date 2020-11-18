package com.fiap.lejourchallenge.dashboard;

public class AppointmentResume {

    private Long numberAppointments;
    private Long numberAppointmentsCreated;
    private Long numberAppointmentsVisited;
    private Long numberAppointmentsConfirmed;
    private Long numberAppointmentsCanceled;

    public Long getNumberAppointments() {
        return numberAppointments;
    }

    public void setNumberAppointments(Long numberAppointments) {
        this.numberAppointments = numberAppointments;
    }

    public Long getNumberAppointmentsCreated() {
        return numberAppointmentsCreated;
    }

    public void setNumberAppointmentsCreated(Long numberAppointmentsCreated) {
        this.numberAppointmentsCreated = numberAppointmentsCreated;
    }

    public Long getNumberAppointmentsVisited() {
        return numberAppointmentsVisited;
    }

    public void setNumberAppointmentsVisited(Long numberAppointmentsVisited) {
        this.numberAppointmentsVisited = numberAppointmentsVisited;
    }

    public Long getNumberAppointmentsConfirmed() {
        return numberAppointmentsConfirmed;
    }

    public void setNumberAppointmentsConfirmed(Long numberAppointmentsConfirmed) {
        this.numberAppointmentsConfirmed = numberAppointmentsConfirmed;
    }

    public Long getNumberAppointmentsCanceled() {
        return numberAppointmentsCanceled;
    }

    public void setNumberAppointmentsCanceled(Long numberAppointmentsCanceled) {
        this.numberAppointmentsCanceled = numberAppointmentsCanceled;
    }
}
