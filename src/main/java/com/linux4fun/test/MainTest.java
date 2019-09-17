package com.linux4fun.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.linux4fun.mapper.CategoryMapper;
import com.linux4fun.pojo.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MainTest {
	@Autowired
	private CategoryMapper mapper;
	
	@Test
	public void testAdd() {
		Category category = new Category();
		category.setId(1);
		category.setName("pengkai");
		mapper.add(category);
	}

}
