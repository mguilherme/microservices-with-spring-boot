package demo;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.format;


@RestController
public class SentenceController {

    private LoadBalancerClient client;

    public SentenceController(LoadBalancerClient client) {
        this.client = client;
    }

    @GetMapping("/sentence")
    public String getSentence() {
        return format("%s %s %s %s %s.",
                getWord("LAB-5-SUBJECT"),
                getWord("LAB-5-VERB"),
                getWord("LAB-5-ARTICLE"),
                getWord("LAB-5-ADJECTIVE"),
                getWord("LAB-5-NOUN"));
    }

    public String getWord(String service) {
        ServiceInstance serviceInstance = client.choose(service);
        if (serviceInstance != null) {
            return (new RestTemplate()).getForObject(serviceInstance.getUri(), String.class);
        }
        return null;
    }

}
