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
package com.example.session.domain.repository.goods;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.RowBounds;

import com.example.session.domain.model.Goods;

public interface GoodsRepository {

    List<Goods> findPageByCategoryId(int categoryId, RowBounds rowBounds);

    long countByCategoryId(int categoryId);

    Optional<Goods> findById(String id);
}
