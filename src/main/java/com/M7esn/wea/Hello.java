package com.M7esn.wea;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.IIOException;

@RestController
public class Hello {

    @GetMapping
    public String doHttpGet() {

        String city = "Riyadh";
        String url = "http://api.weatherstack.com/current?access_key=64b724807a28bc6765baa78709c36de9&query=" + city;

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = client.execute(get);
            HttpEntity entity = response.getEntity();
            String JSONResult = EntityUtils.toString(entity);
            JSONObject TXTResult = new JSONObject(JSONResult);

            String replace = TXTResult.getJSONObject("current").getString("weather_descriptions");
            replace = replace.replace("[", "").replace("]", "");

            return "Weather Application Sample, Data fetched from: http://api.weatherstack.com/current?access_key=64b724807a28bc6765baa78709c36de9&query=Riyadh" +
                    "\nCountry: " + TXTResult.getJSONObject("location").getString("country") +
                    "\nCity: " + TXTResult.getJSONObject("location").getString("name") +
                    "\nTime Zone: " + TXTResult.getJSONObject("location").getString("timezone_id") +
                    "\nLocal Time: " + TXTResult.getJSONObject("location").getString("localtime") +
                    "\nTemperature: " + TXTResult.getJSONObject("current").getInt("temperature") + "℃" +
                    " " + replace +
                    "\nWind: " + TXTResult.getJSONObject("current").getInt("wind_speed") + "kmph";
        }
        catch (IIOException ioe) {
            System.err.println("Something went wrong: ");
            ioe.printStackTrace();
        }
        catch (Exception e) {
            System.err.println("Unknown error: ");
            e.printStackTrace();
        }
        return "NULL";
    }
}
