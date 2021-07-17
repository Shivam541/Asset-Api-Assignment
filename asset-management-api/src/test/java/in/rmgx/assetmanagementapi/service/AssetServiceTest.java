package in.rmgx.assetmanagementapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import in.rmgx.assetmanagementapi.entity.Asset;
import in.rmgx.assetmanagementapi.entity.Category;
import in.rmgx.assetmanagementapi.entity.Employee;
import in.rmgx.assetmanagementapi.repository.AssetRepository;
import in.rmgx.assetmanagementapi.repository.EmployeeRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { AssetService.class })
class AssetServiceTest {

	@Autowired
	private AssetService service;

	@MockBean
	private AssetRepository repository;

	@MockBean
	private EmployeeRepository employeeRepository;

	Category category1 = new Category(10, "stat", "pen pencil");
	Category category2 = new Category(11, "electronics", "needs electricity and works on circuts");

	Asset asset1 = new Asset("markers", "half used up", new Date(System.currentTimeMillis()), category1, "assigned");
	Asset asset2 = new Asset("laptop", "new laptop", new Date(System.currentTimeMillis()), category2, "available");
	Asset asset3 = new Asset("sheets", "90 left", new Date(System.currentTimeMillis()), category1, "recovered");

	Employee employee1 = new Employee(101, "ram", "leader");

	@Test
	public void getAllAssetsTest() {
		List<Asset> list = new ArrayList<Asset>();
		list.add(asset1);
		list.add(asset2);
		list.add(asset3);

		when(repository.findAll()).thenReturn(list);

		assertEquals(list, service.getAllAssets());
	}

	@Test
	public void getAssetByNameTest() {

		when(repository.findById("laptop")).thenReturn(Optional.of(asset1));

		assertEquals(asset1, service.getAssetByName("laptop"));
	}

	@Test
	public void deleteAssetWhichIsAssignedTest() {

		asset1.setAssignedTo(employee1);
		when(repository.findById("markers")).thenReturn(Optional.of(asset1));

		assertEquals("asset cannot be deleted because it is assigned", service.deleteAsset("markers"));
	}

	@Test
	public void deleteAssetWhichDoesNotExistTest() {

		when(repository.findById("abc")).thenReturn(Optional.empty());

		assertEquals("asset does not exist", service.deleteAsset("abc"));
	}

	@Test
	public void deleteAssetSuccessfullyTest() {

		when(repository.findById("laptop")).thenReturn(Optional.of(asset2));

		assertEquals("asset deleted successfylly", service.deleteAsset("laptop"));
	}

	@Nested
	@DisplayName("asset assignment tests")
	class AssetAssignmentTest {

		@Test
		public void assignAssetWhichDoesNotExistTest() {

			when(repository.findById("abc")).thenReturn(Optional.empty());
			when(employeeRepository.findById(101)).thenReturn(Optional.of(employee1));

			assertEquals("asset does not exist", service.assignAsset("abc", 101));
		}

		@Test
		public void assignAssetWhereEmployeeDoesNotExistTest() {

			when(repository.findById("laptop")).thenReturn(Optional.of(asset2));
			when(employeeRepository.findById(101)).thenReturn(Optional.empty());

			assertEquals("employee id does not exist", service.assignAsset("laptop", 101));
		}

		@Test
		public void assignAssetWhichIsAlreadyAssignedTest() {
			asset1.setAssignedTo(employee1);
			when(repository.findById("markers")).thenReturn(Optional.of(asset1));
			when(employeeRepository.findById(101)).thenReturn(Optional.of(employee1));

			assertEquals("asset already assigned to employee having id :" + employee1.getId(),
					service.assignAsset("markers", 101));
		}

		@Test
		public void assignAssetSuccessfullyTest() {

			when(repository.findById("laptop")).thenReturn(Optional.of(asset2));
			when(employeeRepository.findById(101)).thenReturn(Optional.of(employee1));

			assertEquals("asset successfully assigned", service.assignAsset("laptop", 101));
			assertEquals("assigned", asset2.getAssignmentStatus());
		}

	}

	@Nested
	@DisplayName("asset recovery tests")
	class AssetRecoveryTest {

		@Test
		public void recoverAssetWhichDoesNotExistTest() {

			when(repository.findById("abc")).thenReturn(Optional.empty());

			assertEquals("asset does not exist", service.recoverAsset("abc"));
		}
		
		@Test
		public void recoverAssetWhichIsNotAssignedTest() {

			when(repository.findById("laptop")).thenReturn(Optional.of(asset2));

			assertEquals("asset is not assigned", service.recoverAsset("laptop"));
		}
		
		@Test
		public void recoverAssetSuccessfullyTest() {

			when(repository.findById("markers")).thenReturn(Optional.of(asset1));

			assertEquals("asset successfully recovered", service.recoverAsset("markers"));
			assertEquals("recovered", asset1.getAssignmentStatus());
		}
		
	}

}