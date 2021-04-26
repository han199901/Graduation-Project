package com.han.gp.vo.admin.education;


import com.han.gp.base.BasePage;

public class ClassPageRequest extends BasePage {
    private Integer id;
    private Integer level;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
