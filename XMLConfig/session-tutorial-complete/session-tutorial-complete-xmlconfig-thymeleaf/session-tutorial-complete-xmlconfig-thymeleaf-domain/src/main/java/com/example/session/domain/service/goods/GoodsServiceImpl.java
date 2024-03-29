/*
 * Copyright(c) 2024 NTT DATA Group Corporation. Copyright(c) 2013 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package com.example.session.domain.service.goods;

import java.util.Collections;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.session.domain.model.Goods;
import com.example.session.domain.repository.goods.GoodsRepository;

import jakarta.inject.Inject;

@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    @Inject
    GoodsRepository goodsRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Goods> findByCategoryId(Integer categoryId, Pageable pageable) {

        long total = goodsRepository.countByCategoryId(categoryId);

        List<Goods> goodsList = Collections.emptyList();
        if (total > 0) {
            RowBounds rowBounds = new RowBounds((int) pageable
                    .getOffset(), pageable.getPageSize());
            goodsList = goodsRepository.findPageByCategoryId(categoryId,
                    rowBounds);
        }

        return new PageImpl<Goods>(goodsList, pageable, total);
    }

    @Override
    @Transactional(readOnly = true)
    public Goods findOne(String goodsId) {
        return goodsRepository.findById(goodsId).orElse(null);
    }

}
