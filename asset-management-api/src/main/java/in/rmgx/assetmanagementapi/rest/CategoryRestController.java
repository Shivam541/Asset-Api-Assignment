package in.rmgx.assetmanagementapi.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.MethodNotAllowedException;

import in.rmgx.assetmanagementapi.entity.Category;
import in.rmgx.assetmanagementapi.service.CategoryService;

/**
 * 
 * This class uses service layer to get and modify the data as per the requests.
 * It exposes several rest end points for the categories resource through resource URI's.
 * 
 * @author Shivam
 * @version 1.0
 * @since 2021
 * 
 * 
 */
@RestController
@RequestMapping("/categories")
public class CategoryRestController {
	
	/**
	 * CategoryService is used by this service class to interact with the data 
	 * layer for getting and manipulating the data related to Category.
	 *  
	 */
	CategoryService categoryService;
	
	/**
	 * 
	 * sets the value for categoryService property of current object, 
	 * provides support for setter injection
	 * @param categoryService value of categoryService property for current object
	 * 
	 */
	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}


	/**
	 * 
	 * @return list of all categories fetched from the service layer.
	 */
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Category> getAllCategories(){
		return categoryService.getAllCategories();
	}
	
	/**
	 * method to be used by request to create new category.
	 * @param category new category to be persisted.
	 * @return new category data
	 */
	@PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public Category addCategory(@RequestBody Category category) {
		Category addedCategory = categoryService.addCategory(category);
		return addedCategory;
	}
	
	/**
	 * updates an existing category
	 * @param category new category data
	 * @param id id of the category to be updated.
	 * @return updated category data
	 */
	@PutMapping(path = {"/{id}"},
			produces = {MediaType.APPLICATION_JSON_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public Category updateCategory(@RequestBody Category category,@PathVariable int id) {
		category.setId(id);
		Category updatedCategory = categoryService.updateCategory(category);
		return updatedCategory;
	}
	
	/**
	 * this method will catch exceptions of an http method a called 
	 * on a resource which is not allowed.
	 * @param mna takes in the MethodNotAllowedException
	 * @return response entity with bad request
	 */
	@ExceptionHandler
	public ResponseEntity<String> methodNotSupported(MethodNotAllowedException mna){
		return ResponseEntity.badRequest().body("this method is not allowed on this resource");
	}
	
	/**
	 * can catch any exception and sends bad request response.
	 * @param e Exception object
	 * @return bad request response
	 */
	@ExceptionHandler
	public ResponseEntity<String> genericHandler(Exception e) {
		return ResponseEntity.badRequest().body("check the request, it should be in proper format");
	}
	
}
