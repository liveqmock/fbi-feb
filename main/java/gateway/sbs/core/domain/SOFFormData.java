package gateway.sbs.core.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * SBS Form 数据部分
 */
public final class SOFFormData {

    private List<SOFFormBean> beans;
    public int length;

    public SOFFormData(int length) {
        this.length = length;
    }

    public void assembleBeans(int offset, byte[] buffer, String formCode) {
        beans = new ArrayList<>();
        int bufAssembleLength = offset + length;
        try {
            int beanLength = 0;
            do {
                Class clazz = Class.forName("gateway.sbs.txn.model.form." + formCode);
                SOFFormBean formBean = (SOFFormBean) clazz.newInstance();
                formBean.offset = offset;
                if (beanLength == 0) {
                    for (int i : formBean.fieldLengths) {
                        beanLength += i;
                    }
                }
                formBean.assembleFields(buffer);
                beans.add(formBean);
                offset += beanLength;
            } while (bufAssembleLength > offset);
        } catch (Exception e) {
            throw new RuntimeException("没有定义该Form：" + formCode);
        }
    }

    public List<SOFFormBean> getBeans() {
        return beans;
    }
}
