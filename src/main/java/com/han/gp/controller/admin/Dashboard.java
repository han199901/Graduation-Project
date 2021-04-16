package com.han.gp.controller.admin;

import com.han.gp.base.BaseApiController;
import com.han.gp.base.RestResponse;
import com.han.gp.vo.admin.dashboard.Index;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/admin/dashboard")
public class Dashboard extends BaseApiController {

    @PostMapping("/index")
    public RestResponse<Index> index() {
        Index index = new Index();
/*
        index.setDoExamPaperCount();
        index.setDoExamPaperCount();
        index.setExamPaperCount();
        index.setMothDayDoExamQuestionValue();
        index.setMothDayText();
        index.setMothDayUserActionValue();
*/

        return RestResponse.ok(index);
    }

}
