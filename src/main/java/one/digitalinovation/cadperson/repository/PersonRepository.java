package one.digitalinovation.cadperson.repository;

import one.digitalinovation.cadperson.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
