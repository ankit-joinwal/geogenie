package com.geogenie.geo.service.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.geogenie.data.model.Category;

@Repository("categoryDAO")
public class CategoryDAOImpl extends AbstractDAO implements CategoryDAO{

	@Override
	public Category create(Category category) {
		save(category);
		return category;
	}

	@Override
	public List<Category> getAll() {
		Criteria criteria = getSession().createCriteria(Category.class);
		return (List<Category>) criteria.list();
		
	}

}
