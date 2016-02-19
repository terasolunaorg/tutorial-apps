package com.example.session.domain.repository.goods;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.example.session.domain.model.Goods;

public interface GoodsRepository {

	List<Goods> findPageByCategoryId(int categoryId, RowBounds rowBounds);
	
	long countByCategoryId(int categoryId);

	Goods findOne(String id);
}
