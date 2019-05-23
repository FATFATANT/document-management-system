package top.catoy.docmanagement.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.catoy.docmanagement.domain.DocInfoAndDocLabel;
import top.catoy.docmanagement.domain.DocInfoAndTag;
import top.catoy.docmanagement.domain.Tag;

import java.util.List;

@Mapper
public interface DocInfoAndTagMapper {

    int insertDocInfoAndTag(DocInfoAndTag docInfoAndTag);


    List<DocInfoAndTag> getTagsByFileId(@Param("fileId") int fileId);


    int insertDocInfoAndTag(@Param("doclabels") List<DocInfoAndDocLabel> docInfoAndDocLabels);



    List<Integer> getDocIdByTagId(@Param("tags") List<Tag> tags);
}
