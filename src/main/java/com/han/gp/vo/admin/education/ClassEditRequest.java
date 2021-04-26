package com.han.gp.vo.admin.education;


import com.han.gp.vo.admin.Base;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ClassEditRequest extends Base {

    private Integer id;

    @NotBlank
    private String name;

    @NotNull
    private Integer level;

    @NotBlank
    private String levelName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
}
