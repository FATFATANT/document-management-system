package top.catoy.docmanagement.service.impl;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.catoy.docmanagement.domain.*;
import top.catoy.docmanagement.mapper.*;
import top.catoy.docmanagement.service.DepartmentService;
import top.catoy.docmanagement.service.DocInfoService;
import top.catoy.docmanagement.utils.JWTUtil;

import java.util.ArrayList;
import java.util.List;


@Service
public class DocInfoServiceImpl implements DocInfoService {


    @Autowired
    private DocInfoMapper docInfoMapper;

    @Autowired
    private AnnexMapper annexMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DocInfoAndDocLabelMapper docInfoAndDocLabelMapper;

    @Autowired
    private DocInfoAndTagMapper docInfoAndTagMapper;

    @Override
    public int insertDocInfo(DocInfo docInfo) {
        return docInfoMapper.insertDocInfo(docInfo);
    }

    @Override
    public int getDocId(DocInfo docInfo) {
        return docInfoMapper.getDocId(docInfo);
    }
//    @Override
//    public ResponseBean getAllDocInfo(int currentPage, int pageSize) {
//        try {
//            List<DocInfo> docInfos = new ArrayList<>();
//            List<Department> departmentList = departmentMapper.getAllDepartments();
//            int departmentId = JWTUtil.getUserInfo((String) SecurityUtils.getSubject().getPrincipal()).getDepartmentId();
//
//
//            if(docInfos!=null){
//                docInfos.forEach((docInfo) -> {
//                    List<Annex> annexes = annexMapper.getAnnexsByDocId(docInfo.getDocId());
//                    String departmentName = departmentMapper.getDepartmentNameById(docInfo.getDepartmentId());
//                    if(annexes !=null && annexes.size()>0){
//                        docInfo.setAnnexes(annexes);
//                    }
//                    if(departmentName !=null && departmentName.length()>0){
//                        docInfo.setDepartmentName(departmentName);
//                    }
//                });
//                PageInfo pageInfo = pageData(docInfos,pageSize,currentPage);
//                return new ResponseBean(ResponseBean.SUCCESS,"查询成功",pageInfo);
//            }else {
//                return new ResponseBean(ResponseBean.FAILURE,"查询失败",null);
//            }
//        }catch (RuntimeException r){
//            r.printStackTrace();
//            return new ResponseBean(ResponseBean.ERROR,"错误",null);
//        }
//    }

    @Override
    public ResponseBean getDocsBySearchParam(DocInfoSearchParams docInfoSearchParams) {
        try {
            List<DocInfo> docInfos = new ArrayList<>();
            List<DocInfo> docs;
            List<Department> departmentList = departmentMapper.getAllDepartments();
            List<Integer> docLabels = docInfoAndDocLabelMapper.getDocInfoIdByLabelId(docInfoSearchParams.getDocLabels());
            List<Integer> tags = docInfoAndTagMapper.getDocIdByTagId(docInfoSearchParams.getTags());

            int pageSize = docInfoSearchParams.getPageInfo().getPageSize();
            int currentPage = docInfoSearchParams.getPageInfo().getCurrentPage();
            int departmentId = -1;
            String docPostTime;

            if("Invalid date".equals(docInfoSearchParams.getSelectYear())){
                docPostTime = "";
            }else{
                docPostTime = docInfoSearchParams.getSelectYear();
            }
            if(docInfoSearchParams.getDepartmentId() != -1){
                departmentId = docInfoSearchParams.getDepartmentId();
            }else if(docInfoSearchParams.getDepartmentId() == -1) {
                departmentId = JWTUtil.getUserInfo((String) SecurityUtils.getSubject().getPrincipal()).getDepartmentId();
            }

            docs = docInfoMapper.getDocByDepartmentIdAndSearchParam(departmentId,docInfoSearchParams.getDocName(),docPostTime,docLabels,tags);
            docInfos.addAll(docs);//加入父部门所拥有的文档
            getChildDocInfo(departmentId,departmentList,docInfos,docInfoSearchParams.getDocName(),docPostTime,docLabels,tags);

            //获得文件的附件信息和部门信息
            if(docInfos!=null){
                docInfos.forEach((docInfo) -> {
                    List<Annex> annexes = annexMapper.getAnnexsByDocId(docInfo.getDocId());
                    String departmentName = departmentMapper.getDepartmentNameById(docInfo.getDepartmentId());
                    if(annexes !=null && annexes.size()>0){
                        docInfo.setAnnexes(annexes);
                    }
                    if(departmentName !=null && departmentName.length()>0){
                        docInfo.setDepartmentName(departmentName);
                    }
                });
                PageInfo pageInfo = pageData(docInfos,pageSize,currentPage);
                return new ResponseBean(ResponseBean.SUCCESS,"查询成功",pageInfo);
            }else {
                return new ResponseBean(ResponseBean.FAILURE,"查询失败",null);
            }
        }catch (RuntimeException r){
            r.printStackTrace();
            return new ResponseBean(ResponseBean.ERROR,"错误",null);
        }
    }

    @Override
    public List<Department> getChildDocInfo(int id,
                                            List<Department> fatherList,
                                            List<DocInfo> docInfos,
                                            String docName,
                                            String docPostTime,
                                            List<Integer> docLabels,
                                            List<Integer> tags) {
        List<Department> childList = new ArrayList<>();
        for (Department department : fatherList) {
            // 遍历所有节点，将父id与传过来的id比较
            if (department.getParent_id()== id) {
                List<DocInfo> list = docInfoMapper.getDocByDepartmentIdAndSearchParam(department.getId(),docName,docPostTime,docLabels,tags);
                if(list != null && list.size()>0){
                    docInfos.addAll(list);
                }
                childList.add(department);
            }
        }
        for (Department department : childList) {
            department.setChildren(getChildDocInfo(department.getId(), fatherList,docInfos,docName,docPostTime,docLabels,tags));
        } // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

    /**
     * 分页数据
     * @param list
     * @param pagesize
     * @param pageno
     * @return
     */
    @Override
    public PageInfo pageData(List<DocInfo> list, Integer pagesize, Integer pageno){
        int totalcount = 0;
        int pagecount = 0;
        int m = 0;
        List<DocInfo> subList = new ArrayList<>();
        if(list != null && list.size()>0){
            totalcount = list.size();
            m = totalcount%pagesize;
            if  (m>0)
            {
                pagecount=totalcount/pagesize+1;
            }
            else
            {
                pagecount=totalcount/pagesize;
            }
            if (m==0)
            {
                subList= list.subList((pageno-1)*pagesize,pagesize*(pageno));
            }
            else
            {
                if (pageno==pagecount)
                {
                    subList= list.subList((pageno-1)*pagesize,totalcount);
                }
                else
                {
                    subList= list.subList((pageno-1)*pagesize,pagesize*(pageno));
                }
            }
        }else {
            totalcount = 0;
        }
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal(totalcount);
        pageInfo.setCurrentPage(pageno);
        pageInfo.setPageSize(pagesize);
        pageInfo.setList(subList);
        return pageInfo;
    }
}
