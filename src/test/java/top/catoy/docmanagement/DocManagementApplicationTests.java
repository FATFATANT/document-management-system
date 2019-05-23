package top.catoy.docmanagement;

import javafx.concurrent.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.junit4.SpringRunner;
import top.catoy.docmanagement.domain.*;
import top.catoy.docmanagement.mapper.*;
import top.catoy.docmanagement.service.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class DocManagementApplicationTests {
    @Autowired
    private LogMapper logMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private LogService logService;

    @Autowired
    private DocLabelMapper docLabelMapper;

    @Autowired
    private DocLabelService docLabelService;

    @Autowired
    private DocInfoMapper docInfoMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserGroupMapper userGroupMapper;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private TagService tagService;

    @Autowired
    private DocInfoAndTagService docInfoAndTagService;

    @Autowired
    private DocInfoAndDocLabelService docInfoAndDocLabelService;

    @Autowired
    private DocInfoService docInfoService;

    @Autowired
    private DocInfoAndDocLabelMapper docInfoAndDocLabelMapper;

    @Autowired
    private  DocInfoAndTagMapper docInfoAndTagMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testLogMapper(){
        Log log = new Log();
        log.setOpLabel("用户操作");
        log.setOpName("用户登入");
        log.setUserId(1);
        List<Log> logs = logMapper.getAllLogs();
        System.out.println("查询Id为1的日志:"+logMapper.selectLogById(1).toString());
        System.out.println("查询所有日志:"+logs.toString());
    }

    @Test
    public void testLogService(){
//        int sun = 0;
//        ResponseBean responseBean = logService.insertLog(1,"删除用户","用户管理");
//        if(sun > 0){
//            System.out.println("日志插入成功");
//        }else {
//            System.out.println("日志插入失败");
//        }
//        ResponseBean responseBean = logService.getLogsBySearchParam("1",1,10);
//        System.out.println(responseBean);
//        System.out.println(responseBean.getData().toString());
    }

    @Test
    public void testDepartmentMapper(){
//        Department department = new Department();
//        Department department1 = new Department();
//        department.setDepartmentName("丽水学院");
//        department1.setDepartmentName("工学院");
//        department1.setSuperId(1);
//        departmentMapper.insertDepartment(department);
//        departmentMapper.insertDepartment(department1);
//        System.out.println("得到所有部门信息"+departmentMapper.getAllDepartments().toString());
    }

    @Test
    public void testDocLabelMapper(){
        DocLabel docLabel = new DocLabel();
        DocLabel docLabelNew = new DocLabel();
        docLabel.setDocLabelName("图片");
        docLabelNew.setDocLabelName("视频");

        int sum = docLabelMapper.insertLabel(docLabel);
        System.out.println(docLabel.toString());
        if(sum > 0){
            System.out.println("----------标签插入成功");
        }else{
            System.out.println("----------标签插入失败");
        }

    }

    @Test
    public void testDocLabelService(){
//        DocLabel docLabel = new DocLabel();
//        DocLabel docLabelNew = new DocLabel();
//        docLabel.setDocLabelName("图片");
//        docLabelNew.setDocLabelName("视频");
//        System.out.println(docLabelService.insertDocLabel(docLabel).toString());
//        System.out.println(docLabelService.editDocLabel(docLabelNew).toString());
//        docLabel = docLabelNew;
//        System.out.println(docLabelService.getAllDocLabels().toString());
//        System.out.println(docLabelService.delDocLabel(docLabel).toString());
//        System.out.println(docLabelService.getSonDocLabels(19));
        ResponseBean responseBean = docLabelService.getDocLabelsTree();
        System.out.println(responseBean.toString());
    }

    @Test
    public void getDepartmentNameById(){
        int num = 1;
        System.out.println(departmentMapper.getDepartmentNameById(num));
    }

    @Test
    public void getdepartmentID(){
        String name = "工学院";
       System.out.print(departmentMapper.getDepartmentIdByName(name)); ;
    }

    @Test
    public void getUserGroupId(){
        String name = "admin";
        System.out.println(userGroupMapper.getUserGroupName(name));
    }


    @Test
    public void testdeleteuser(){
        int id = 5;
        System.out.println(userMapper.deleteUserById(5));
    }


    @Test
    public void insertUser(){
        User user = new User();
        user.setUserName("sda");
        user.setUserPassword("123");
        user.setUserLock(0);
        user.setGroupId(1);
        user.setDepartmentId(1);
        System.out.print( userMapper.insertUser(user));

    }

    @Test
    public void getDepartmentTree(){
        System.out.println(departmentService.getDepartmentsTree());
//        System.out.println(departmentService.getAllDepartments());
    }

    public void insertManyToMany(){
        DocInfoAndDocLabel docInfoAndDocLabel = new DocInfoAndDocLabel();
        docInfoAndDocLabel.setDocId(5);
        docInfoAndDocLabel.setdLabelId(19);
    }


    @Test
    public void getTopDepartment(){
        List list = departmentService.getTopDepartment();
        for(int i = 0;i < list.size();i++){
            System.out.println(list.get(i));
        }
    }


    @Test
    public void getSuffixName(){
        String str = "996.icx.xls";
        int pos = str.lastIndexOf('.');
        String news = str.substring(pos);
        System.out.println(news);
        System.out.println(pos);
    }

    @Test
    public void TestDocInfoMapper(){
        List<Integer> docLabels = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
//        Tag tag = new Tag();
//        Tag tag2 = new Tag();
//        tag.setTagId(1);
//        tag2.setTagId(2);
//        tags.add(tag);
//        tags.add(tag2);
//        DocLabel docLabel = new DocLabel();
//        DocLabel doclabel2 = new DocLabel();
//        docLabel.setDocLabelId(19);
//        doclabel2.setDocLabelId(20);
//        docLabels.add(docLabel);
//        docLabels.add(doclabel2);
//        docLabels.add(19);
//        docLabels.add(20);
//        docInfoMapper.getDocByDepartmentIdAndSearchParam(17,"","2019",docLabels,tags);
    }

    @Test
    public void DocInfoService(){
//        String param = "{docLabels=[], tags=[], departmentId=-1, DocName='123', selectYear='2010', pageInfo=PageInfo{currentPage=1, pageSize=6, total=0, list=null}";
        DocInfoSearchParams docInfoSearchParams = new DocInfoSearchParams();
        List<DocLabel> docLabels = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        Tag tag = new Tag();
        Tag tag1 = new Tag();
        tag.setTagId(1);
        tag1.setTagId(2);
        tags.add(tag);
        tags.add(tag1);
        DocLabel docLabel = new DocLabel();
        DocLabel docLabel2 = new DocLabel();
        docLabel.setDocLabelId(19);
        docLabel2.setDocLabelId(20);
        docLabels.add(docLabel);
        docLabels.add(docLabel2);
        docInfoSearchParams.setDepartmentId(2);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageSize(7);
        pageInfo.setCurrentPage(1);
        docInfoSearchParams.setDocLabels(docLabels);
        docInfoSearchParams.setPageInfo(pageInfo);
        docInfoSearchParams.setTags(tags);
        docInfoSearchParams.setSelectYear("Invalid date");

//        docInfoSearchParams.setSelectYear("3000");
        ResponseBean responseBean = docInfoService.getDocsBySearchParam(docInfoSearchParams);
        System.out.println(responseBean.toString());
    }

    @Test
    public void testDocInfoDocLabelmapper(){
        List<DocLabel> docLabels = new ArrayList<>();
        DocLabel docLabel = new DocLabel();
        DocLabel docLabel2 = new DocLabel();
        docLabel.setDocLabelId(1);
        docLabel2.setDocLabelId(2);
//        docLabels.add(docLabel);
//        docLabels.add(docLabel2);
        List<Integer> integers = docInfoAndDocLabelMapper.getDocInfoIdByLabelId(docLabels);
        System.out.println(integers.toString());
    }

    @Test
    public void testTagAndDocLabelMapper(){
        List<Tag> tags = new ArrayList<>();
        Tag tag = new Tag();
        Tag tag1 = new Tag();
        tag.setTagId(1);
        tag1.setTagId(2);
        tags.add(tag);
        tags.add(tag1);
        System.out.println(docInfoAndTagMapper.getDocIdByTagId(tags).toString());
    }







}
