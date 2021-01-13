package com.shresthabinod.reactivespring.service;

import com.shresthabinod.reactivespring.model.Appointment;
import reactor.core.publisher.Mono;

public interface AppointmentService {
    Mono<Appointment> getAppointment(String id);
    Mono<Appointment> createReservation(Mono<Appointment> appointmentMono);
    Mono<Appointment> updateAppointment(String id, Mono<Appointment> appointmentMono);
    Mono<Boolean> deleteReservation(String id);
}
