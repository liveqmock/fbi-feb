package gateway.sbs.txn.model.msg;

/**
 * 总账码多笔查询
 */
public class M9813 extends MTia {
    private String GLCODE	;          // 总账码
    private String GLCNAM	;          // 总账码名称

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
