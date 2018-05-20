/**
 * 
 */
package csci4380.finalp.dogs.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import csci4380.finalp.dogs.mongodb.model.Dog;
import csci4380.finalp.dogs.mongodb.repo.DogRepository;



/**
 * @author User
 *
 */
@RestController
@RequestMapping("rest/v1/dogs")
public class DogRestController {
	@Autowired DogRepository dogRepository;
	
	@RequestMapping(value="/echoMessage", method=RequestMethod.GET)
	@GetMapping("/echoMessage")		
	/**
	 * http://localhost:8888/rest/v1/students/echoMessage?msg=Hi
	 */
	public String echoMessage(@RequestParam(value="msg", defaultValue="Hello Jose") String message) {
		return "echoMessage echoed: " + message;
	}
	
	@GetMapping("")
	public Page<Dog>findAll(@RequestParam(defaultValue="0") int page, @RequestParam(value="rowsPerPage", defaultValue="3") int size) {
		Page<Dog> dogsPage = dogRepository.findAll(new PageRequest(page, size));
		return dogsPage;
	}
	
	@GetMapping("/all")
	public  List<Dog> findAll() {
		List<Dog> dogs = dogRepository.findAll();
		return dogs;
	}
	
	@PostMapping("")
	public  Optional<List<Dog>> save(@RequestBody final Dog dog) {
		Dog savedDog = dogRepository.save(dog);
		return dogRepository.findByPetId(savedDog.getPetId());
	}
	
	@PutMapping("")
	public  Dog insert(@RequestBody final Dog dog) {
		Dog insertedDog = dogRepository.save(dog);
		return insertedDog;
	}
	
	@GetMapping("/{id}")
	public  Optional<Dog> findById(@PathVariable String id) {
		Optional<Dog> dog = dogRepository.findById(id);
		return dog;
	}
	
	@GetMapping("/petId/{petId}")
	public  Optional<List<Dog>> findByPetId(@PathVariable Integer petId) {
		Optional<List<Dog>> dogs = dogRepository.findByPetId(petId);
		return dogs;
	}
	
	@DeleteMapping("petId/{petId}")
	public  void delete(@PathVariable("petId") Integer petId) {
		dogRepository.deleteByPetId(petId);
	}
	
	@DeleteMapping("/{id}")
	public  void delete(@PathVariable("Id") String id) {
		dogRepository.deleteById(id);
	}
	
	@GetMapping("/byNameorOwnerName/{name}/{ownerName}")
	public  List<Dog> findByNameOrOwnerName(@PathVariable String name, @PathVariable String ownerName) {
		List<Dog> dogs = dogRepository.findByNameOrOwnerName(name, ownerName);
		return dogs;
	}
	@GetMapping("/byTypeIgnoreCase/{type}")
	public  Optional<List<Dog>> findByTypeIgnoreCase(@PathVariable String type) {
		Optional<List<Dog>> dogs = dogRepository.findByTypeIgnoreCase(type);
		return dogs;
	}

}
