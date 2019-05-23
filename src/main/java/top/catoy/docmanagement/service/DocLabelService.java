package top.catoy.docmanagement.service;

import top.catoy.docmanagement.domain.DocLabel;
import top.catoy.docmanagement.domain.ResponseBean;

import javax.print.Doc;
import java.util.List;

public interface DocLabelService {
    ResponseBean insertDocLabel(DocLabel docLabel);

//    ResponseBean delDocLabelByName(String docLabelName);

    ResponseBean delDocLabel(DocLabel docLabel);

//    ResponseBean editDocLabelByName(String docLabelName);

    ResponseBean editDocLabel(DocLabel docLabel);

    ResponseBean getAllDocLabels();

    ResponseBean getDocLabelsTree();

    ResponseBean getSonDocLabels(int superId);

    ResponseBean getDocLabelById(int id);

    public List<DocLabel> getLabelByName(String []labels);

    List<DocLabel> getChild(int id, List<DocLabel> fatherList);
}
