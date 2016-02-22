package com.example.session.domain.service.goods;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.session.domain.model.Goods;
import com.example.session.domain.repository.goods.GoodsRepository;

@Service
public class GoodsService {

	@Inject
	GoodsRepository goodsRepository;

	/**
	 * 指定したカテゴリの全商品を取得する
	 * 
	 * @param categoryId
	 * @param pageable
	 * @return
	 */
	public Page<Goods> findByCategoryId(Integer categoryId, Pageable pageable) {

		long total = goodsRepository.countByCategoryId(categoryId);

		List<Goods> goodsList = Collections.emptyList();
		if (total > 0) {
			RowBounds rowBounds = new RowBounds(pageable.getOffset(),
					pageable.getPageSize());
			goodsList = goodsRepository.findPageByCategoryId(categoryId,
					rowBounds);
		}

		return new PageImpl<Goods>(goodsList, pageable, total);
	}

	public Goods findOne(String goodsId) {
		return goodsRepository.findOne(goodsId);
	}

}
