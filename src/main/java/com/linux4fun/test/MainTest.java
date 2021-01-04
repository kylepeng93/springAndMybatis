package com.linux4fun.test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linux4fun.pojo.HelloMessage;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.linux4fun.mapper.CategoryMapper;
import com.linux4fun.pojo.Category;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
		Assert.assertEquals(5, pageInfo.getPageSize());
		Assert.assertEquals(6, pageInfo.getStartRow());
		Assert.assertEquals(10, pageInfo.getEndRow());
		Assert.assertEquals(11, pageInfo.getTotal());
	}

	@Test
	@Transactional
//	@Rollback(value = false) 阻止事务回滚
	public void batchInsert() {
		List<String> names = new ArrayList<>();
		names.add("pengkai");
		names.add("kai");
		names.add("sky");
		for (String name : names) {
			categoryMapper.insertCategory(name);
			// 添加事务注解的情况下会自动回滚，否则不会回滚
//			throw new RuntimeException("中断事务，测试是否回滚！");
		}
	}

	@Test
	public void getHelloMessage() {
		ApplicationContext ctx = new GenericXmlApplicationContext("applicationContext.xml");

		HelloMessage messgae = ctx.getBean("helloMessage", HelloMessage.class);
		System.out.println(messgae.getMessage());

	}

}
