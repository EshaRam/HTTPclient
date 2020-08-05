package httpRequests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.Charset;

public class ContentValidation {

    private static Logger log = LogManager.getLogger(ContentValidation.class);

    @Test

    public void contentValidation() throws IOException {


        String URL = "https://reqres.in/api/users?page=2";
        HttpGet httpGet = new HttpGet(URL);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse httpResponse = httpClient.execute(httpGet);

        String responseBody = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        System.out.println(responseBody);


        String email = jsonNode.get("data").get(1).get("email").asText();
        Assert.assertEquals(email, "lindsay.ferguson@reqres.in");
        log.info(email);

        String name = jsonNode.get("data").get(3).get("first_name").asText();
        Assert.assertEquals(name,"Byron");
        log.info(name);
    }

}
