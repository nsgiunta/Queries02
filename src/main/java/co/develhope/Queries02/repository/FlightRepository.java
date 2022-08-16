package co.develhope.Queries02.repository;

import co.develhope.Queries02.entities.Flight;
import co.develhope.Queries02.entities.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long>{
    Page<Flight> findAllByStatus(Status status, PageRequest of);

    List<Flight> getCustomFlight(Status p1, Status p2);
}
