package httpRequests;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class putRequest {
    @Test
    public void putRequest() throws IOException {
        String URL = "https://reqres.in/api/users/2";
        HttpPut httpPut = new HttpPut(URL);
        HttpClient httpClient = HttpClientBuilder.create().build();

        HashMap<String,String> hashMap = new HashMap<String, String>();
        hashMap.put("Content-Type","application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","Tony Stark");
        jsonObject.put("job","Ironman");


        String jsonString = jsonObject.toJSONString();
        StringEntity stringEntity = new StringEntity(jsonString);

        // this is the response body(payload)
        httpPut.setEntity(stringEntity);

        // for all header
        for (Map.Entry<String,String>entrys : hashMap.entrySet()){
            httpPut.addHeader(entrys.getKey(),entrys.getValue());
        }

        HttpResponse httpResponse = httpClient.execute(httpPut);//send post request

        int statuscode = httpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statuscode,200);
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

