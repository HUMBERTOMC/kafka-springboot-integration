package com.humbertomattos.producer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.humbertomattos.producer.OrderProducer;

@RestController
@RequestMapping(value = "/orders")
@Slf4j
public class OrderController {

    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void placeOrder(@RequestBody String order) {
    	log.info("============= PRODUCER ======================");
    	log.info("Receive the Order: \n" + order);
    	log.info("Process the payment: OK \n");
    	log.info("Packing: OK \n");
    	log.info("=============================================");
    	orderProducer.send(order);
    	
    }
}
