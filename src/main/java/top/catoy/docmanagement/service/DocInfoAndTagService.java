package top.catoy.docmanagement.service;

import org.apache.ibatis.annotations.Param;
import top.catoy.docmanagement.domain.DocInfoAndTag;
import top.catoy.docmanagement.domain.Tag;

import java.util.List;

public interface DocInfoAndTagService {

    public int insertDocInfoAndTag(DocInfoAndTag docInfoAndTag);


    public List<DocInfoAndTag> getTagsByFileId(@Param("id") int id);
}
