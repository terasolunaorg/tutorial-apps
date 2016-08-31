package com.example.session.app.goods;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component // (1)
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS) // (2)
public class GoodsSearchCriteria implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int categoryId = 1;

    private int page = 0;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void clear() {
        categoryId = 1;
        page = 0;
    }

}