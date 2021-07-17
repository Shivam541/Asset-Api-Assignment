package in.rmgx.assetmanagementapi.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.MethodNotAllowedException;

import in.rmgx.assetmanagementapi.entity.Asset;
import in.rmgx.assetmanagementapi.service.AssetService;

/**
 * 
 * This class uses service layer to get and modify the data as per the requests.
 * It exposes several rest end points for the assets resource through resource URI's.
 * 
 * @author Shivam
 * @version 1.0
 * @since 2021
 * 
 * 
 */
@RestController
@RequestMapping("assets")
public class AssetRestController {

	/**
	 * AssetService is used by this service class to interact with the data 
	 * layer for getting and manipulating the data related to Category.
	 *  
	 */
	AssetService assetService;
	
	/**
	 * 
	 * sets the value for assetService property of current object, 
	 * provides support for setter injection
	 * @param assetService value of assetService property for current object
	 * 
	 */
	@Autowired
	public void setAssetService(AssetService assetService) {
		this.assetService = assetService;
	}

	/**
	 * 
	 * @return list of all assets
	 */
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Asset> getAllAssets(){
		return assetService.getAllAssets();
	}
	
	/**
	 * 
	 * @param name name of the asset to search
	 * @return request asset details as an asset object
	 */
	@GetMapping(path = {"/{name}"})
	public Asset getByName(@PathVariable String name) {
		return assetService.getAssetByName(name);
	}
	
	/**
	 * creates a new asset object
	 * @param asset new asset data
	 * @return updated asset object
	 */
	@PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public Asset addAsset(@RequestBody Asset asset) {
		return assetService.addAsset(asset);
	}
	
	/**
	 * updates an existing asset data
	 * @param asset new data for existing asset
	 * @param name name of the asset to be updated
	 * @return updated asset data
	 */
	@PutMapping(path = {"/{name}"},
			produces = {MediaType.APPLICATION_JSON_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public Asset updateAsset(@RequestBody Asset asset,@PathVariable String name) {
		
		asset.setName(name);
		return assetService.updateAsset(asset);
	}
	
	/**
	 * deletes the asset with same name as parameter passed in
	 * @param name name of asset to be deleted
	 * @return message about the deletion request
	 */
	@DeleteMapping(path = {"/{name}"},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public String deleteAsset(@PathVariable String name) {
		return assetService.deleteAsset(name);
	}
	
	/**
	 * assigns an asset to an employee 
	 * @param name of the asset to be assigned
	 * @param employeeId id of the employee to whom the asset will be assign
	 * @return string message about the asset assignment request
	 */
	@PutMapping(path = {"/{name}/assign/{employeeId}"})
	public String assignAsset(@PathVariable String name,@PathVariable int employeeId) {
		return assetService.assignAsset(name, employeeId);
	}
	
	/**
	 * recovers an asset, 
	 * it does not check about the employee to whom the asset is assigned
	 * @param name name of the asset to recover
	 * @return string message about the asset recovery request.
	 */
	@DeleteMapping(path = {"/{name}/assign"})
	public String recoverAsset(@PathVariable String name) {
		return assetService.recoverAsset(name);
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
