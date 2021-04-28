
package com.han.gp.controller.teacher;


import com.github.pagehelper.PageInfo;
import com.han.gp.base.BaseApiController;
import com.han.gp.base.RestResponse;
import com.han.gp.domain.Class;
import com.han.gp.service.ClassService;
import com.han.gp.utility.PageInfoHelper;
import com.han.gp.vo.admin.education.ClassPageRequest;
import com.han.gp.vo.admin.education.ClassResponse;
import com.han.gp.vo.student.education.ClassEditRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController("TeacherEducationController")
@RequestMapping(value = "/api/teacher/education")
public class EducationOps extends BaseApiController {

    private final ClassService classService;

    @Autowired
    public EducationOps(ClassService classService) {
        this.classService = classService;
    }

    @RequestMapping(value = "/subject/list", method = RequestMethod.POST)
    public RestResponse<List<Class>> list() {
        List<Class> subjects = classService.allClasses();
        return RestResponse.ok(subjects);
    }

    @RequestMapping(value = "/subject/page", method = RequestMethod.POST)
    public RestResponse<PageInfo<ClassResponse>> pageList(@RequestBody ClassPageRequest model) {
        PageInfo<Class> pageInfo = classService.page(model);
        PageInfo<ClassResponse> page = PageInfoHelper.copyMap(pageInfo, e -> modelMapper.map(e, ClassResponse.class));
        return RestResponse.ok(page);
    }

    @RequestMapping(value = "/subject/edit", method = RequestMethod.POST)
    public RestResponse edit(@RequestBody @Valid ClassEditRequest model) {
        Class aClass = modelMapper.map(model, Class.class);
        if (model.getId() == null) {
            classService.insertByFilter(aClass);
        } else {
            classService.updateByIdFilter(aClass);
        }
        return RestResponse.ok();
    }

    @RequestMapping(value = "/subject/select/{id}", method = RequestMethod.POST)
    public RestResponse<ClassEditRequest> select(@PathVariable Integer id) {
        Class subject = classService.selectById(id);
        ClassEditRequest vm = modelMapper.map(subject, ClassEditRequest.class);
        return RestResponse.ok(vm);
    }

    @RequestMapping(value = "/subject/delete/{id}", method = RequestMethod.POST)
    public RestResponse delete(@PathVariable Integer id) {
        Class subject = classService.selectById(id);
        classService.updateByIdFilter(subject);
        return RestResponse.ok();
    }
}

