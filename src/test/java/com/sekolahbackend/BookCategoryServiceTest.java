package com.sekolahbackend;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

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
import com.sekolahbackend.entity.Persistence.Status;
import com.sekolahbackend.model.BookCategoryModel;
import com.sekolahbackend.service.BookCategoryService;
import com.sekolahbackend.service.BookCategoryServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaConfigTest.class, BookCategoryServiceImpl.class })
@ComponentScan(basePackages = { "com.sekolahbackend.service" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookCategoryServiceTest {

	private static BookCategoryModel bookCategoryDummy1;
	private static BookCategoryModel bookCategoryDummy2;
	
	private static Integer bookCategoryId = null;
	private static BookCategoryModel bookCategoryData = null;
	
	@Autowired
	private BookCategoryService bookCategoryService;
	
	@BeforeClass
	public static void init() throws Exception {
		bookCategoryDummy1 = new BookCategoryModel();
		bookCategoryDummy1.setCode("KDBK001");
		bookCategoryDummy1.setName("Sejarah");
		
		bookCategoryDummy2 = new BookCategoryModel();
		bookCategoryDummy2.setCode("KDBK005");
		bookCategoryDummy2.setName("Kesehatan");
	}
	
	@Test
	public void test1_InsertBookCategory() {
		try {
			BookCategoryModel bookCategory = bookCategoryService.saveOrUpdate(bookCategoryDummy1);
			
			assertNotNull(bookCategory);
			assertNotNull(bookCategory.getId());
			assertEquals(1, bookCategoryService.countAll());
			assertEquals(bookCategoryDummy1.getCode(), bookCategory.getCode());
			assertEquals(bookCategoryDummy1.getName(), bookCategory.getName());
			
			bookCategoryId = bookCategory.getId();
			
		} catch (Exception e) {
			log.error("Error test1_InsertBookCategory: {}", ExceptionUtils.getStackTrace(e));
			assertFalse(ExceptionUtils.getRootCauseMessage(e), true);
		}
	}
	
	@Test
	public void test2_FindBookCategoryById() {
		try {
			BookCategoryModel bookCategory = bookCategoryService.findById(bookCategoryId);
			
			assertNotNull(bookCategory);
			assertNotNull(bookCategory.getId());
			assertEquals(bookCategoryId, bookCategory.getId());
			
			bookCategoryData = bookCategory;
		} catch (Exception e) {
			assertFalse(ExceptionUtils.getRootCauseMessage(e), true);
		}
	}
	
	@Test
	public void test3_EditBookCategory() {
		try {
			assertNotNull(bookCategoryData);
			
			assertNotNull(bookCategoryData);
			assertNotNull(bookCategoryData.getId());
			
			bookCategoryData.setCode("KDBK002");
			bookCategoryData.setName("Novel");
			bookCategoryData = bookCategoryService.saveOrUpdate(bookCategoryData);

			assertEquals(bookCategoryId, bookCategoryData.getId());
			assertNotEquals(bookCategoryDummy1.getCode(), bookCategoryData.getCode());
			assertNotEquals(bookCategoryDummy1.getName(), bookCategoryData.getName());
			
		} catch (Exception e) {
			assertFalse(ExceptionUtils.getRootCauseMessage(e), true);
		}
	}
	
	@Test
	public void test4_FindAllBookCategory() {
		try {
			List<BookCategoryModel> bookCategoryModels =  bookCategoryService.findAll();
			
			assertNotNull(bookCategoryModels);
			assertEquals(1, bookCategoryModels.size());
		} catch (Exception e) {
			assertFalse(ExceptionUtils.getRootCauseMessage(e), true);
		}
	}
	
	@Test
	public void test5_DeleteBookCategory() {
		try {
			assertNotNull(bookCategoryDummy1.getStatus());
			assertEquals(bookCategoryDummy1.getStatus(), Status.ACTIVE);
			
			BookCategoryModel bookCategoryData = bookCategoryService.findById(bookCategoryId);
			BookCategoryModel bookCategory = bookCategoryService.delete(bookCategoryData);
			
			log.info("Book Category Table Status: {}", bookCategory.getStatus());
			
			assertNotEquals(bookCategoryDummy1.getStatus(), bookCategory.getStatus());
			assertEquals(bookCategory.getStatus(), Status.NOT_ACTIVE);
		} catch (Exception e) {
			assertFalse(ExceptionUtils.getRootCauseMessage(e), true);
		}
	}
	
	@Test
	public void test6_FindNotActiveBookCategory() {
		assertEquals(1, bookCategoryService.findByStatusNotActive().size());
	}
	
	@Test
	public void test7_InsertBookCategory() {
		try {
			BookCategoryModel bookCategory = bookCategoryService.saveOrUpdate(bookCategoryDummy2);
			
			assertNotNull(bookCategory);
			assertNotNull(bookCategory.getId());
			assertEquals(1, bookCategoryService.countAll());
			assertEquals(bookCategoryDummy2.getCode(), bookCategory.getCode());
			assertEquals(bookCategoryDummy2.getName(), bookCategory.getName());
			
		} catch (Exception e) {
			assertFalse(ExceptionUtils.getRootCauseMessage(e), true);
		}
	}
	
}
