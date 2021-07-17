package in.rmgx.assetmanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.rmgx.assetmanagementapi.entity.Employee;

/**
 * 
 * This interface is used to persist and modify Employee objects in the database.
 * <br>
 * <p>It extends JpaRepository and provides the basic crud operations to be 
 * performed on the Employee objects.</p>
 * 
 * @author Shivam
 * @version 1.0
 * @since 2021
 * 
 * 
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
