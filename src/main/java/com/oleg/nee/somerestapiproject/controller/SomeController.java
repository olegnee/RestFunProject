package com.oleg.nee.somerestapiproject.controller;

import com.oleg.nee.somerestapiproject.kafka.KafkaProducer;
import com.oleg.nee.somerestapiproject.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class SomeController {

    private final KafkaProducer kafkaProducer;
    private final UserRepo userRepo;


    @PostMapping("/kafka/send")
    public String send (@RequestParam int id) {
        var user = userRepo.findById(id);

        kafkaProducer.sendMessage(user.toString());
        return "Success";
    }
}
