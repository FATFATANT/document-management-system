package top.catoy.docmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.catoy.docmanagement.domain.ResponseBean;
import top.catoy.docmanagement.service.DocLabelService;

/**
 * @description:
 * @author: xjn
 * @create: 2019-05-18 17:16
 **/
@RestController
public class DocLabelController {
    @Autowired
    private DocLabelService docLabelService;

    @GetMapping("/public/getDocLabelsTree")
    public ResponseBean getAllDocLabels(){
        ResponseBean result = docLabelService.getDocLabelsTree();
        return result;
    }
}
