/*
 * Copyright(c) 2013 NTT DATA Corporation. Copyright(c) 2013 NTT Corporation.
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
package com.example.session.app.cart;

import java.util.Set;

import org.hibernate.validator.constraints.NotEmpty;

public class CartForm {

    @NotEmpty
    private Set<String> removedItemsIds;

    public Set<String> getRemovedItemsIds() {
        return removedItemsIds;
    }

    public void setRemovedItemsIds(Set<String> removedItemsIds) {
        this.removedItemsIds = removedItemsIds;
    }
}