package top.catoy.docmanagement.service.impl;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.catoy.docmanagement.domain.DocInfoAndDocLabel;
import top.catoy.docmanagement.mapper.DocInfoAndDocLabelMapper;
import top.catoy.docmanagement.service.DocInfoAndDocLabelService;

@Service
public class DocInfoAndDocLabelServiceImpl implements DocInfoAndDocLabelService {


    @Autowired
    private DocInfoAndDocLabelMapper docInfoAndDocLabelMapper;

    @Override
    public int insertDocInfoAndDocLabel(DocInfoAndDocLabel docInfoAndDocLabel) {
        return docInfoAndDocLabelMapper.insertDocInfoAndDocLabel(docInfoAndDocLabel);
    }
}
