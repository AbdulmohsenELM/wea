package com.M7esn.wea;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    private FetchWeatherApi fetchWeatherApi = new FetchWeatherApi();

    @RequestMapping(value = "/Weather")
    public String printWeatherInfo() {
        return fetchWeatherApi.doHttpGet("Jeddah");
    }
}
