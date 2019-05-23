package top.catoy.docmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.catoy.docmanagement.domain.DocInfoSearchParams;
import top.catoy.docmanagement.domain.ResponseBean;
import top.catoy.docmanagement.service.DocInfoService;
import top.catoy.docmanagement.service.DocLabelService;

/**
 * @description:
 * @author: xjn
 * @create: 2019-05-15 20:29
 **/
@RestController
public class DocInfoController {

    @Autowired
    private DocInfoService docInfoService;


//    @GetMapping("/public/getAllDocInfo")
//    public ResponseBean getAllDocInfo(@RequestParam String currentPage,
//                                   @RequestParam String pageSize){
//        ResponseBean result = docInfoService.getAllDocInfo(Integer.parseInt(currentPage),Integer.parseInt(pageSize));
//        return result;
//    }

    @PostMapping("/public/getDocsBySearchParam")
    public ResponseBean getDocsBySearchParam(@RequestBody DocInfoSearchParams docInfoSearchParams){
        System.out.println(docInfoSearchParams.toString());
        ResponseBean responseBean = docInfoService.getDocsBySearchParam(docInfoSearchParams);
        return responseBean;
    }
}
