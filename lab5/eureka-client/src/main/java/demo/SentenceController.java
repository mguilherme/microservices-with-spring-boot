package demo;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;


@RestController
public class SentenceController {

    private DiscoveryClient client;

    public SentenceController(DiscoveryClient client) {
        this.client = client;
    }

    @GetMapping("/sentence")
    public String getSentence() {
        return String.format("%s %s %s %s %s.",
                getWord("LAB-5-SUBJECT"),
                getWord("LAB-5-VERB"),
                getWord("LAB-5-ARTICLE"),
                getWord("LAB-5-ADJECTIVE"),
                getWord("LAB-5-NOUN"));
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
