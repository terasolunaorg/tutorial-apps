/*
 * Copyright (C) 2013-2017 NTT DATA Corporation
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
package com.example.session.app.goods;
 
import javax.inject.Inject;
 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
import com.example.session.domain.model.Goods;
import com.example.session.domain.service.goods.GoodsService;
 
@Controller
@RequestMapping("goods")
public class GoodsController {
 
	@Inject
	GoodsService goodsService;
 
	@ModelAttribute(value = "goodViewForm")
	public GoodViewForm setUpCategoryId() {
		return new GoodViewForm();
	}
 
	@RequestMapping(value = "", method = RequestMethod.GET)
	String showGoods(GoodViewForm form, Pageable pageable, Model model) {
 
		Page<Goods> page = goodsService.findByCategoryId(form.getCategoryId(),
				pageable);
		model.addAttribute("page", page);
		return "goods/showGoods";
	}
 
	@RequestMapping(value = "/{goodsId}", method = RequestMethod.GET)
	public String showGoodsDetail(@PathVariable String goodsId, Model model) {
 
		Goods goods = goodsService.findOne(goodsId);
		model.addAttribute(goods);
 
		return "goods/showGoodsDetail";
	}
}