package top.catoy.docmanagement.service;

import top.catoy.docmanagement.domain.*;

import javax.print.Doc;
import java.util.List;

public interface DocInfoService {


    int getDocId(DocInfo docInfo);
    int insertDocInfo(DocInfo docInfo);

//    ResponseBean getAllDocInfo(int currentPage, int pageSize);

    ResponseBean getDocsBySearchParam(DocInfoSearchParams docInfoSearchParams);

    List<Department> getChildDocInfo(int id,
                                     List<Department> fatherList,
                                     List<DocInfo> docInfos,
                                     String docName,
                                     String docPostTime,
                                     List<Integer> docLabels,
                                     List<Integer> tags);

    PageInfo pageData(List<DocInfo> list, Integer pagesize, Integer pageno);
}
