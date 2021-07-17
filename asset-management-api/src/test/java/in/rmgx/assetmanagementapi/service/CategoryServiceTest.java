package in.rmgx.assetmanagementapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import in.rmgx.assetmanagementapi.entity.Category;
import in.rmgx.assetmanagementapi.repository.CategoryRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CategoryService.class})
class CategoryServiceTest {
	
	@Autowired
	private CategoryService categoryService;
	
	@MockBean
	private CategoryRepository categoryRepository;
	
	// test data list
	List<Category> list=new ArrayList<Category>();
	
	@BeforeEach
	public void init() {
		list.add(new Category(10,"stat","pen pencil"));
		list.add(new Category(11,"electronics","needs electricity and works on circuts"));
	}
	
	@Test
	public void serviceInjectionTest() {
		assertNotNull(categoryService);
	}
	
	@Test
	public void getAllCategoriesTest() {
		
		when(categoryRepository.findAll()).thenReturn(list);
		
		assertEquals(list, categoryService.getAllCategories());
		verify(categoryRepository).findAll();
	}
	
	@Test
	public void updateCategoryTest() {
		when(categoryRepository.save(list.get(0))).thenReturn(list.get(0));
		
		assertEquals(list.get(0), categoryService.updateCategory(list.get(0)));
	}
	
}
