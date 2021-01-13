package com.shresthabinod.reactivespring.controller;

import com.shresthabinod.reactivespring.model.Appointment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(AppointmentResource.SERVICE_V_1_APPOINTMENT)
@CrossOrigin
public class AppointmentResource {
    public static final String SERVICE_V_1_APPOINTMENT = "/service/v1/appointment/";
    @GetMapping(path="{serviceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<String> getAppointmentById(@PathVariable String serviceId){
        return Mono.just("{}");

    }
    @PostMapping(path= "", produces =  MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<String> createAppointment(@RequestBody Mono<Appointment> appointment){
        return Mono.just("{}");
    }
    @PutMapping(path="{serviceId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<String> updatePrice(@PathVariable String serviceId, @RequestBody Mono<Appointment> appointment){
        return Mono.just("{}");

    }

}
