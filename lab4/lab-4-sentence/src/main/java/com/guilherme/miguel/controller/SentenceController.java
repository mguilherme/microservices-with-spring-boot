package com.guilherme.miguel.controller;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * @author Miguel Guilherme
 */
@RestController
public class SentenceController {

    private DiscoveryClient client;

    public SentenceController(DiscoveryClient client) {
        this.client = client;
    }

    @RequestMapping("/sentence")
    public String getSentence() {
        return
                getWord("LAB-4-SUBJECT") + " "
                        + getWord("LAB-4-VERB") + " "
                        + getWord("LAB-4-ARTICLE") + " "
                        + getWord("LAB-4-ADJECTIVE") + " "
                        + getWord("LAB-4-NOUN") + "."
                ;
    }

    public String getWord(String service) {
        List<ServiceInstance> list = client.getInstances(service);
        if (list != null && list.size() > 0) {
            URI uri = list.get(0).getUri();
            if (uri != null) {
                return (new RestTemplate()).getForObject(uri, String.class);
            }
        }
        return null;
    }

}
