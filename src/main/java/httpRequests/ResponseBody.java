package httpRequests;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ResponseBody {
    @Test

    public void postRequest() throws IOException {
        String URL = "https://reqres.in/api/users";
        HttpPost httpPost = new HttpPost(URL);
        HttpClient httpClient = HttpClientBuilder.create().build();

        HashMap<String,String> hashMap = new HashMap<String, String>();
        hashMap.put("Content-Type","application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","Ironman");
        jsonObject.put("job","Avenger");
        jsonObject.put("id","213");

        String jsonString = jsonObject.toJSONString();
        StringEntity stringEntity = new StringEntity(jsonString);
        // this is the response body(payload)
        httpPost.setEntity(stringEntity);

        // for all header
       for (Map.Entry<String,String>entrys : hashMap.entrySet()){
           httpPost.addHeader(entrys.getKey(),entrys.getValue());
       }

        HttpResponse httpResponse = httpClient.execute(httpPost);//send post request

        int statuscode = httpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statuscode,201);
        System.out.println(statuscode);

        String responseBody = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
        System.out.println();
        System.out.println(responseBody);// response in body
        Header[] allHeader = httpResponse.getAllHeaders();
        HashMap<String, String> headers = new HashMap<String, String>();
        for (Header h : allHeader) {
            headers.put( h.getName(),h.getValue());
        }
        System.out.println();
        System.out.println(headers);
    }
}
