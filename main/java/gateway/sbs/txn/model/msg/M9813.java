package gateway.sbs.txn.model.msg;

/**
 * �������ʲ�ѯ
 */
public class M9813 extends MTia {
    private String GLCODE	;          // ������
    private String GLCNAM	;          // ����������

    public M9813(String GLCODE, String GLCNAM) {
        this.GLCODE = GLCODE;
        this.GLCNAM = GLCNAM;
    }

    public String getGLCODE() {
        return GLCODE;
    }

    public String getGLCNAM() {
        return GLCNAM;
    }
}
