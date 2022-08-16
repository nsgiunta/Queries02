package co.develhope.Queries02.controller;

import co.develhope.Queries02.entities.Flight;
import co.develhope.Queries02.entities.Status;
import co.develhope.Queries02.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.springframework.data.domain.Sort.by;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    public String getRandomString(){
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    @GetMapping("/list")
    public List<Flight> getFlightList(@RequestParam(required = false) Integer n){
        if(n == null) n=100;
        List<Flight> flightList = new ArrayList<>();
        for(int i = 0; i <= n; i++){
        flightList.add(new Flight(i,getRandomString(),getRandomString(),getRandomString(), Status.randomStatus()));
        flightRepository.saveAllAndFlush(flightList);
        }
        return flightList;
    }

    @GetMapping("")
    public Page<Flight> getAllFlights(@RequestParam int page, @RequestParam int size){
        return flightRepository.findAll(PageRequest.of(page, size, Sort.by("fromAirport").ascending()));
    }

    @GetMapping("/status/{status}")
    //a me non compariva findAllByStatus, come faccio a sapere che essiste?
    public Page<Flight> getAllFlightsByStatus(@PathVariable Status status, @RequestParam int page, @RequestParam int size){
        return flightRepository.findAllByStatus(status, (PageRequest.of(page, size)));
    }

    @GetMapping("/custom")
    //cosa significa p1 o p2?
    public List<Flight> getCustomFlight(@RequestParam Status p1, @RequestParam Status p2){
        return flightRepository.getCustomFlight(p1, p2);
    }

}
