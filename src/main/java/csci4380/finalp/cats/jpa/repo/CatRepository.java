/**
 * 
 */
package csci4380.finalp.cats.jpa.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import csci4380.finalp.cats.jpa.model.Cat;

/**
 * 
 * @author Jose Velez
 *
 */
public interface CatRepository extends JpaRepository<Cat, Integer> {


	public Optional<Cat> findByPetId(Integer petId);
	

	public List<Cat> findByNameOrOwnerName(String name, String ownerName);


	public Optional<List<Cat>> findByTypeIgnoreCase(String type);
	
	@Query("select c from CAT c where lower(c.type) = lower(:type)")
	public Optional<List<Cat>> findByTypeIgnoreCaseQuery(@Param("type") String type);


	public void deleteByPetId(Integer petId);

}
