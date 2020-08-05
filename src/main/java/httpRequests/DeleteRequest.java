package httpRequests;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteRequest {
    @Test

    public void deleteRequest() throws IOException {

        String url = "https://reqres.in/api/users/2";
        HttpDelete httpDelete = new HttpDelete(url);
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpResponse httpResponse = httpClient.execute(httpDelete);
        int statuscode = httpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statuscode,204);
        System.out.println(statuscode);




    }


}
