package com.shresthabinod.reactivespring.service;

import com.shresthabinod.reactivespring.model.Appointment;
import com.sun.jna.platform.win32.WinNT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.publisher.Mono;

public class AppointmentServiceImpl implements AppointmentService{

    private final ReactiveMongoOperations reactiveMongoOperations;
    @Autowired
    public AppointmentServiceImpl(ReactiveMongoOperations reactiveMongoOperations) {
        this.reactiveMongoOperations = reactiveMongoOperations;
    }

    @Override
    public Mono<Appointment> getAppointment(String id) {
        return reactiveMongoOperations.findById(id, Appointment.class);
    }

    @Override
    public Mono<Appointment> createReservation(Mono<Appointment> appointmentMono) {
        return reactiveMongoOperations.save(appointmentMono);
    }

    @Override
    public Mono<Appointment> updateAppointment(String id, Mono<Appointment> appointmentMono) {

        //return reactiveMongoOperations.save(appointmentMono);
        return appointmentMono.flatMap(appointment ->
            reactiveMongoOperations.findAndModify(
                    Query.query(Criteria.where("id").is(id)),
                    Update.update("price", appointment.getPrice()), Appointment.class
            ).flatMap(result ->{
             result.setPrice(appointment.getPrice());
             return Mono.just(result);
            })
        );
    }

    @Override
    public Mono<Boolean> deleteReservation(String id) {
        return reactiveMongoOperations.remove(
                Query.query(Criteria.where("id").is(id)), Appointment.class).flatMap(deleteResult -> Mono.just(deleteResult.wasAcknowledged()));

    }
}
