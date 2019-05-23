package top.catoy.docmanagement.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.catoy.docmanagement.domain.DocInfo;
import top.catoy.docmanagement.domain.DocLabel;
import top.catoy.docmanagement.domain.Tag;

import java.util.List;

@Mapper
public interface DocInfoMapper {

    int insertDocInfo(DocInfo docInfo);

    List<DocInfo> getAllDocInfo();

    List<DocInfo> getDocByDepartmentId(@Param("departmentId") int departmentId);

    List<DocInfo> getDocByDepartmentIdAndSearchParam(@Param("departmentId") int departmentId,
                                                     @Param("docName") String docName,
                                                     @Param("docPostTime") String docPostTime,
                                                     @Param("docLabels") List<Integer> docLabels,
                                                     @Param("tags") List<Integer> tags);

    public int getDocId(DocInfo docInfo);

    public int getDocIdByName(@Param("name") String name);
}
