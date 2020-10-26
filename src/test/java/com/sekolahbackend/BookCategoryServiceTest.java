package com.sekolahbackend;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sekolahbackend.config.JpaConfigTest;
import com.sekolahbackend.model.BookCategoryModel;
import com.sekolahbackend.service.BookCategoryService;
import com.sekolahbackend.service.BookCategoryServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaConfigTest.class, BookCategoryServiceImpl.class })
@ComponentScan(basePackages = { "com.sekolahbackend.service" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookCategoryServiceTest {

	private static BookCategoryModel bookCategoryModel;
	
	private static Integer bookCategoryId = null;
	
	@Autowired
	private BookCategoryService bookCategoryService;
	
	@BeforeClass
	public static void init() throws Exception {
		bookCategoryModel = new BookCategoryModel();
		bookCategoryModel.setCode("KDBK001");
		bookCategoryModel.setName("Sejarah");
	}
	
	@Test
	public void test1_InsertBookCategory() {
		try {
			BookCategoryModel bookCategory = bookCategoryService.saveOrUpdate(bookCategoryModel);
			
			assertNotNull(bookCategory);
			assertNotNull(bookCategory.getId());
			assertEquals(1, bookCategoryService.countAll());
			assertEquals(bookCategoryModel.getCode(), bookCategory.getCode());
			assertEquals(bookCategoryModel.getName(), bookCategory.getName());
			
			bookCategoryId = bookCategory.getId();
			
		} catch (Exception e) {
			assertFalse(ExceptionUtils.getRootCauseMessage(e), true);
		}
	}
	
	@Test
	public void test2_EditBookCategory() {
		try {
			assertNotNull(bookCategoryId);
			
			BookCategoryModel bookCategory = bookCategoryService.findById(bookCategoryId);
			
			assertNotNull(bookCategory);
			assertNotNull(bookCategory.getId());
			
			bookCategory.setCode("KDBK002");
			bookCategory.setName("Novel");
			bookCategory = bookCategoryService.saveOrUpdate(bookCategory);

			assertEquals(bookCategoryId, bookCategory.getId());
			assertNotEquals(bookCategoryModel.getCode(), bookCategory.getCode());
			assertNotEquals(bookCategoryModel.getName(), bookCategory.getName());
			
		} catch (Exception e) {
			assertFalse(ExceptionUtils.getRootCauseMessage(e), true);
		}
	}
}
