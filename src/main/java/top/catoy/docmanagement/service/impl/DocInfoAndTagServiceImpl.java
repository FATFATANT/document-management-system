package top.catoy.docmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.catoy.docmanagement.domain.DocInfoAndTag;
import top.catoy.docmanagement.mapper.DocInfoAndTagMapper;
import top.catoy.docmanagement.service.DocInfoAndTagService;

import java.util.List;


@Service
public class DocInfoAndTagServiceImpl implements DocInfoAndTagService {


    @Autowired
    private DocInfoAndTagMapper docInfoAndTagMapper;


    @Override
    public int insertDocInfoAndTag(DocInfoAndTag docInfoAndTag){
        return docInfoAndTagMapper.insertDocInfoAndTag(docInfoAndTag);
    }

    @Override
    public List<DocInfoAndTag> getTagsByFileId(int id) {
        return docInfoAndTagMapper.getTagsByFileId(id);
    }
}
