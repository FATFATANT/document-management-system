package top.catoy.docmanagement.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.catoy.docmanagement.domain.DocInfo;
import top.catoy.docmanagement.domain.DocInfoAndDocLabel;
import top.catoy.docmanagement.domain.DocLabel;

import java.util.List;

@Mapper
public interface DocInfoAndDocLabelMapper {

    int insertDocInfoAndDocLabel(DocInfoAndDocLabel docInfoAndDocLabel);


    List<DocLabel> getAllTagsOfFile(@Param("docId") int docId);


    List<DocInfo> getAllFileOfTags(@Param("tagid") int tagid);


    List<Integer> getDocInfoIdByLabelId(@Param("docLabels") List<DocLabel> docLabels);

}
