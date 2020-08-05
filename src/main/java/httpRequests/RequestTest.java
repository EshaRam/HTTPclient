package httpRequests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class RequestTest {

    @Test
    public void getRequest() throws IOException {
        String URL = "https://reqres.in/api/users?page=2";//initializing
        HttpGet httpGet = new HttpGet(URL); // parsing URL
        HttpClient httpClient = HttpClientBuilder.create().build();//creating http instance
        HttpResponse httpResponse = httpClient.execute(httpGet);// hitting URL
       int statuscode = httpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statuscode,200);
        System.out.println(statuscode);

       String responseBody = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
      // System.out.println();
       System.out.println(responseBody);
        Header[] allHeader = httpResponse.getAllHeaders();
        HashMap<String, String> headers = new HashMap<String, String>();
        for (Header h : allHeader) {
          headers.put( h.getName(),h.getValue());
        }
       System.out.println();
        System.out.println(headers);

        ObjectMapper objmap = new ObjectMapper();
        JsonNode jsonNode = objmap.readTree(responseBody);


        String email = jsonNode.get("data").get(1).get("email").asText();
        Assert.assertEquals(email, "lindsay.ferguson@reqres.in");
        System.out.println(email);

    }
}
