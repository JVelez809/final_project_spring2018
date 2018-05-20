/**
 * 
 */
package csci4380.finalp.dogs.mongodb.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import csci4380.finalp.dogs.mongodb.model.Dog;

/**
 * @author User
 *
 */
public interface DogRepository extends MongoRepository<Dog, String> {

	public Optional<List<Dog>> findByPetId(Integer petId);

	public void deleteByPetId(Integer petId);
	

	public List<Dog> findByNameOrOwnerName(String name, String ownerName);

	public Optional<List<Dog>> findByTypeIgnoreCase(String type);

	@Query("{'type': ?0}")
	public List<Dog> findMyByType(String type);
	
	
}
