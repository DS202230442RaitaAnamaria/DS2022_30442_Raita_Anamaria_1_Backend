package com.example.assignment1;

import com.example.assignment1.controller.WebSocketTextController;
import com.example.assignment1.entity.Devices;
import com.example.assignment1.entity.Measurements;
import com.example.assignment1.entity.Message;
import com.example.assignment1.entity.TextMessageDTO;
import com.example.assignment1.service.DeviceService;
import com.example.assignment1.service.MeasurementsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class Assignment1Application {

    public static void main(String[] args)  {
        SpringApplication.run(Assignment1Application.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    MeasurementsService measurementsService;
    @Autowired
    DeviceService deviceService;

    @Autowired
    WebSocketTextController webSocketTextController;

    @RabbitListener(queues = "csvqueue1")
    public void run(String msg1) throws Exception {
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
            Message msg = new ObjectMapper().readValue(msg1, Message.class);
            Measurements measurement=new Measurements();
            measurement.setEncons(msg.getValue());
            measurement.setTimest(msg.getReadingDate());
            measurementsService.saveMeasurements(measurement,msg.getIdDevice());
            System.out.println(msg);

            Devices devices=deviceService.getById(msg.getIdDevice());
            List<Measurements> measurementsList=devices.getMeasurementsList();
            Double actualMHEC= 0.0;
            System.out.println("actual mhec"+actualMHEC+"\n");
            for (Measurements meas:measurementsList) {
                if(
                        meas.getTimest().getYear()==measurement.getTimest().getYear() &&
                                meas.getTimest().getMonth()==measurement.getTimest().getMonth() &&
                                meas.getTimest().getDay()==measurement.getTimest().getDay() &&
                                meas.getTimest().getHours()==measurement.getTimest().getHours()
                )

                    actualMHEC+=meas.getEncons();
            }
            System.out.println(actualMHEC);
            if(actualMHEC > devices.getMhec()) {
                TextMessageDTO textMessageDTO=new TextMessageDTO();
                textMessageDTO.setMessage(msg.getIdDevice().toString());
                webSocketTextController.sendMessage(textMessageDTO);
                System.out.println("nasol bre");
            }else
                System.out.println("gj");

  }
}
