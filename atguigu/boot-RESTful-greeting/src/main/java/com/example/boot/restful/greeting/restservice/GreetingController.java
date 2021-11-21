package com.example.boot.restful.greeting.restservice;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "hello, %s!";
    private final AtomicLong counter = new AtomicLong();


    /**
     * A key difference between a traditional MVC controller and the RESTful web service controller shown earlier
     * is the way that the HTTP response body is created.
     * Rather than relying on a view technology to
     * perform server-side rendering of the greeting data to HTML,
     * this RESTful web service controller populates and returns a Greeting object.
     * The object data will be written directly to the HTTP response as JSON.
     * @param name
     * @return
     */
    // @GetMapping annotation ensures that HTTP GET requests to /greeting are mapped to the greeting() method.
    @GetMapping("/greeting")
    // @RequestMapping(method = RequestMethod.GET)
    public Greetings greetings(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greetings(counter.incrementAndGet(), String.format(template, name));
    }

}
