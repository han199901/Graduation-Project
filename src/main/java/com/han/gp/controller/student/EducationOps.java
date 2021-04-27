package com.han.gp.controller.student;

import com.han.gp.base.BaseApiController;
import com.han.gp.base.RestResponse;
import com.han.gp.domain.Class;
import com.han.gp.domain.User;
import com.han.gp.service.ClassService;
import com.han.gp.service.RelationService;
import com.han.gp.vo.student.education.ClassEditRequest;
import com.han.gp.vo.student.education.ClassVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/student/education")
public class EducationOps extends BaseApiController {

    private final ClassService classService;

    private final RelationService relationService;

    @Autowired
    public EducationOps(ClassService classService, RelationService relationService) {
        this.classService = classService;
        this.relationService = relationService;
    }

    @RequestMapping(value = "/subject/list", method = RequestMethod.POST)
    public RestResponse<List<ClassVO>> list() {
        User user = getCurrentUser();
        int cid = relationService.getClassIdByUid(user.getId());
        List<Class> subjects = new ArrayList<>();
        Class t = classService.selectById(cid);
        subjects.add(t);
        List<ClassVO> subjectVMS = subjects.stream().map(d -> {
            ClassVO subjectVM = modelMapper.map(d, ClassVO.class);
            subjectVM.setId(String.valueOf(d.getId()));
            return subjectVM;
        }).collect(Collectors.toList());
        return RestResponse.ok(subjectVMS);
    }

    @RequestMapping(value = "/subject/select/{id}", method = RequestMethod.POST)
    public RestResponse<ClassEditRequest> select(@PathVariable Integer id) {
        Class aClass = classService.selectById(id);
        ClassEditRequest vm = modelMapper.map(aClass, ClassEditRequest.class);
        return RestResponse.ok(vm);
    }

}
