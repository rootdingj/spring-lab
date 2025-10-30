package com.dj.cloud.ribbon;


import com.dj.cloud.ribbon.model.Coffee;
import com.dj.cloud.ribbon.model.CoffeeOrder;
import com.dj.cloud.ribbon.model.NewOrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class CustomerRunner implements ApplicationRunner {

    private final static String SERVER_ID = "register-eureka-provider";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        showServiceInstances();
        readMenu();
        Long id = orderCoffee();
        queryOrder(id);
    }

    private void showServiceInstances() {
        log.info("DiscoveryClient: {}", discoveryClient.getClass().getName());
        discoveryClient.getInstances(SERVER_ID).forEach(s -> {
            log.info("Host: {}, Port: {}", s.getHost(), s.getPort());
        });
    }

    private void readMenu() {
        ParameterizedTypeReference<List<Coffee>> ptr =
                new ParameterizedTypeReference<List<Coffee>>() {};
        ResponseEntity<List<Coffee>> list = restTemplate
                .exchange("http://"+SERVER_ID+"/coffee/", HttpMethod.GET, null, ptr);
        list.getBody().forEach(c -> log.info("Coffee: {}", c));
    }

    private Long orderCoffee() {
        NewOrderRequest orderRequest = NewOrderRequest.builder()
                .customer("Li Lei")
                .items(Arrays.asList("capuccino"))
                .build();
        RequestEntity<NewOrderRequest> request = RequestEntity
                .post(UriComponentsBuilder.fromUriString("http://"+SERVER_ID+"/order/").build().toUri())
                .body(orderRequest);
        ResponseEntity<CoffeeOrder> response = restTemplate.exchange(request, CoffeeOrder.class);
        log.info("Order Request Status Code: {}", response.getStatusCode());
        Long id = response.getBody().getId();
        log.info("Order ID: {}", id);
        return id;
    }

    private void queryOrder(Long id) {
        CoffeeOrder order = restTemplate
                .getForObject("http://"+SERVER_ID+"/order/{id}", CoffeeOrder.class, id);
        log.info("Order: {}", order);
    }
}
