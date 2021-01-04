package com.linux4fun.test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linux4fun.pojo.HelloMessage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.linux4fun.mapper.CategoryMapper;
import com.linux4fun.pojo.Category;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MainTest {
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Test
	public void testSelect() {
		PageHelper.startPage(2, 5);
		List<Category> categoryList = categoryMapper.list();
		PageInfo pageInfo = new PageInfo(categoryList);
		Assert.assertEquals(2, pageInfo.getPageNum());
		Assert.assertEquals(1, pageInfo.getPageSize());
//		System.out.println(((Page)categoryList).getTotal());
//		categoryList.forEach(category -> {
//			System.out.println(category.toString());
//		});
	}

	@Test
	public void getHelloMessage() {
		ApplicationContext ctx = new GenericXmlApplicationContext("applicationContext.xml");

		HelloMessage messgae = ctx.getBean("helloMessage", HelloMessage.class);
		System.out.println(messgae.getMessage());

	}

}
