package in.rmgx.assetmanagementapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.rmgx.assetmanagementapi.entity.Asset;
import in.rmgx.assetmanagementapi.entity.Employee;
import in.rmgx.assetmanagementapi.repository.AssetRepository;
import in.rmgx.assetmanagementapi.repository.EmployeeRepository;

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
public class AssetService {

	/**
	 * AssetRepository is used by this service class to interact with the data 
	 * layer for getting and manipulating the data related to Assets.
	 *  
	 */
	AssetRepository assetRepository;
	/**
	 * EmployeeRepository is used by this service class to interact with the data 
	 * layer for getting and manipulating the data related to Employee.
	 *  
	 */
	EmployeeRepository employeeRepository;

	/**
	 * 
	 * sets the value for employeeRepository property of current object, 
	 * provides support for setter injection
	 * @param employeeRepository value of employeeRepository property for current object
	 * 
	 */
	@Autowired
	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	/**
	 * 
	 * sets the value for assetRepository property of current object, 
	 * provides support for setter injection
	 * @param assetRepository value of assetRepository property for current object
	 * 
	 */
	@Autowired
	public void setAssetRepository(AssetRepository assetRepository) {
		this.assetRepository = assetRepository;
	}

	/**
	 * fetches all assets from the database.
	 * @return return the list of all assets
	 */
	public List<Asset> getAllAssets() {
		return assetRepository.findAll();
	}

	/**
	 * Fetches an asset from the database based on the name.
	 * @param name name of asset to be searched for
	 * @return requested asset or null if asset not found
	 */
	public Asset getAssetByName(String name) {
		Optional<Asset> optionalAsset = assetRepository.findById(name);
		return optionalAsset.orElse(null);
	}

	/**
	 * Updates the existing assets data based on asset name with the new asset object data passed in
	 * @param asset new data for existing asset
	 * @return updated asset object
	 */
	public Asset updateAsset(Asset asset) {
		Asset savedAsset = assetRepository.save(asset);
		return savedAsset;
	}

	/**
	 * adds a new asset to the database
	 * @param asset new asset to be persisted
	 * @return same asset data as passed in.
	 */
	public Asset addAsset(Asset asset) {
		return assetRepository.save(asset);
	}

	/**
	 * removes an asset from the database with same name as parameter passed in
	 * @param name name of asset to be deleted
	 * @return message about the deletion request
	 */
	public String deleteAsset(String name) {
		Asset assetToDelete = getAssetByName(name);
		if (assetToDelete == null)
			return "asset does not exist";
		if (assetToDelete.getAssignmentStatus().equals("assigned")) {
			return "asset cannot be deleted because it is assigned";
		} else {
			assetRepository.deleteById(name);
		}
		return "asset deleted successfylly";
	}

	/**
	 * assigns an asset to an employee
	 * @param assetName name of the asset to be assigned
	 * @param employeeId id of the employee to whom the asset will be assign
	 * @return string message about the asset assignment request
	 */
	public String assignAsset(String assetName, int employeeId) {
		Employee assignedTo=employeeRepository.findById(employeeId).orElse(null);
		if(assignedTo==null) return "employee id does not exist";
		Asset asset=getAssetByName(assetName);
		if(asset==null) 
			return "asset does not exist";
		else if(asset.getAssignmentStatus().equals("assigned")) 
			return "asset already assigned to employee having id :"+asset.getAssignedTo().getId();
		else {
			asset.setAssignmentStatus("assigned");
			asset.setAssignedTo(assignedTo);
			assetRepository.save(asset);
		}
		return "asset successfully assigned";
	}
	
	/**
	 * recovers an asset assigned to an employee
	 * and change its state to recovered
	 * @param assetName name of asset to be recovered
	 * @return string message about the asset recovery request.
	 */
	public String recoverAsset(String assetName) {
		Asset asset=getAssetByName(assetName);
		if(asset==null) 
			return "asset does not exist";
		else if(!asset.getAssignmentStatus().equals("assigned")) 
			return "asset is not assigned";
		else {
			asset.setAssignmentStatus("recovered");
			asset.setAssignedTo(null);
			assetRepository.save(asset);
		}
		return "asset successfully recovered";
	}

}