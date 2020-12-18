package sg.edu.iss.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.model.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {

}
