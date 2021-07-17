package in.rmgx.assetmanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.rmgx.assetmanagementapi.entity.Category;

/**
 * 
 * This interface is used to persist and modify Category objects in the database.
 * <br>
 * <p>It extends JpaRepository and provides the basic crud operations to be 
 * performed on the Category objects.</p>
 * 
 * @author Shivam
 * @version 1.0
 * @since 2021
 * 
 * 
 */
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
