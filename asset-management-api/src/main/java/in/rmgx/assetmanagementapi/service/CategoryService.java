package in.rmgx.assetmanagementapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.rmgx.assetmanagementapi.entity.Category;
import in.rmgx.assetmanagementapi.repository.CategoryRepository;

/**
 * 
 * This class used provide services to controller layer and gets data database using repositories 
 * and perform desired operations on objects fetched or going to be persisted in the database.
 * 
 * @author Shivam
 * @version 1.0
 * @since 2021
 * 
 * 
 */
@Service
public class CategoryService {
	
	/**
	 * CategoryRepository is used by this service class to interact with the data 
	 * layer for getting and manipulating the data related to Category.
	 *  
	 */
	CategoryRepository categoryRepository;
	
	/**
	 * 
	 * sets the value for categoryRepository property of current object, 
	 * provides support for setter injection
	 * @param categoryRepository value of categoryRepository property for current object
	 * 
	 */
	@Autowired
	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	/**
	 * Gets all the categories present in the database.
	 * @return returns a list of all categories
	 */
	public List<Category> getAllCategories(){
		return categoryRepository.findAll();
	}
	
	/**
	 * Updates the data of a category present in the database with the new category object passed in.
	 * @param category object with new data.
	 * @return updated category data
	 */
	public Category updateCategory(Category category) {
		Category updatedCategory = categoryRepository.save(category);
		return updatedCategory;
	}
	
	/**
	 * It adds new category in the database based on data of object passed as arguments,
	 * fills the object with the auto-generated id for that object.
	 * @param category new category entry to be persisted in the database
	 * @return a category object with new id.
	 */
	public Category addCategory(Category category) {
		category.setId(0);
		Category addedCategory = categoryRepository.save(category);
		return addedCategory;
	}
	
}