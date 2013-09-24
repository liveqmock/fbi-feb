package gateway.sbs.txn.model.form;

import gateway.sbs.core.domain.SOFFormBody;
import gateway.sbs.core.domain.AssembleModel;


import java.util.ArrayList;
import java.util.List;

/**
 * 9813总账码单笔查询、增删改-响应 T861
 */

public class T861 extends SOFFormBody {

   /* private List<Bean> beanList = new ArrayList<Bean>();
    @Override
    public void assembleFields(int offset, byte[] buffer) {
        int index = offset;
        int beanLength = 94;
        do {
            Bean bean = new Bean();
            bean.assembleFields(index, buffer);
            beanList.add(bean);
            index += beanLength;
        } while (index < buffer.length);
    }

    public List<Bean> getBeanList() {
        return beanList;
    }
    public class Bean extends AssembleModel {
    {
        fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        fieldLengths = new int[]{4, 34, 1, 4, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 8, 8, 4, 8, 6};
    }*/
   {
    fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    fieldLengths = new int[]{4, 34, 1, 4, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 8, 8, 4, 8, 6};
   }

    private String GLCODE;          // 总账码
    private String GLCNAM;          // 总账码名称
    private String GLCCLS;          // 科目级别
    private String GLCGRP;          // 并入科目
    private String GLCTYP;          // 科目属性
    private String GLCCAT;          // 科目类别
    private String GLCCCY;          // 允许货币标志
    private String GLCBAL;          // 余额方向标志
    private String GLCOCC;          // 发生额方向标志
    private String GLCINT;          // 记息种类
    private String GLCRAT;          // 利率种类
    private String GLCOPN;          // 自动开户允许标志
    private String GLCRVS;          // 冲帐允许标志
    private String RSVFG1;          // 备用标志1
    private String RSVFG2;          // 备用标志2
    private String RSVFG3;          // 备用标志3
    private String RSVFG4;          // 备用标志4
    private String RSVFG5;          // 备用标志5
    private String GLCBEL;          // 科目所属
    private String EFFDAT;          // 科目启用日期
    private String EXPDAT;          // 科目使用到期日
    private String AMDTLR;          // 建立柜员
    private String UPDDAT;          // 修改日期
    private String GLCORD;          // 引用号


    public String getGLCORD() {
        return GLCORD;
    }

    public void setGLCORD(String GLCORD) {
        this.GLCORD = GLCORD;
    }

    public String getUPDDAT() {
        return UPDDAT;
    }

    public void setUPDDAT(String UPDDAT) {
        this.UPDDAT = UPDDAT;
    }

    public String getGLCODE() {
        return GLCODE;
    }

    public void setGLCODE(String GLCODE) {
        this.GLCODE = GLCODE;
    }

    public String getGLCNAM() {
        return GLCNAM;
    }

    public void setGLCNAM(String GLCNAM) {
        this.GLCNAM = GLCNAM;
    }

    public String getGLCCLS() {
        return GLCCLS;
    }

    public void setGLCCLS(String GLCCLS) {
        this.GLCCLS = GLCCLS;
    }

    public String getGLCGRP() {
        return GLCGRP;
    }

    public void setGLCGRP(String GLCGRP) {
        this.GLCGRP = GLCGRP;
    }

    public String getGLCTYP() {
        return GLCTYP;
    }

    public void setGLCTYP(String GLCTYP) {
        this.GLCTYP = GLCTYP;
    }

    public String getGLCCAT() {
        return GLCCAT;
    }

    public void setGLCCAT(String GLCCAT) {
        this.GLCCAT = GLCCAT;
    }

    public String getGLCCCY() {
        return GLCCCY;
    }

    public void setGLCCCY(String GLCCCY) {
        this.GLCCCY = GLCCCY;
    }

    public String getGLCBAL() {
        return GLCBAL;
    }

    public void setGLCBAL(String GLCBAL) {
        this.GLCBAL = GLCBAL;
    }

    public String getGLCOCC() {
        return GLCOCC;
    }

    public void setGLCOCC(String GLCOCC) {
        this.GLCOCC = GLCOCC;
    }

    public String getGLCINT() {
        return GLCINT;
    }

    public void setGLCINT(String GLCINT) {
        this.GLCINT = GLCINT;
    }

    public String getGLCRAT() {
        return GLCRAT;
    }

    public void setGLCRAT(String GLCRAT) {
        this.GLCRAT = GLCRAT;
    }

    public String getGLCOPN() {
        return GLCOPN;
    }

    public void setGLCOPN(String GLCOPN) {
        this.GLCOPN = GLCOPN;
    }

    public String getGLCRVS() {
        return GLCRVS;
    }

    public void setGLCRVS(String GLCRVS) {
        this.GLCRVS = GLCRVS;
    }

    public String getRSVFG1() {
        return RSVFG1;
    }

    public void setRSVFG1(String RSVFG1) {
        this.RSVFG1 = RSVFG1;
    }

    public String getRSVFG2() {
        return RSVFG2;
    }

    public void setRSVFG2(String RSVFG2) {
        this.RSVFG2 = RSVFG2;
    }

    public String getRSVFG3() {
        return RSVFG3;
    }

    public void setRSVFG3(String RSVFG3) {
        this.RSVFG3 = RSVFG3;
    }

    public String getRSVFG4() {
        return RSVFG4;
    }

    public void setRSVFG4(String RSVFG4) {
        this.RSVFG4 = RSVFG4;
    }

    public String getRSVFG5() {
        return RSVFG5;
    }

    public void setRSVFG5(String RSVFG5) {
        this.RSVFG5 = RSVFG5;
    }

    public String getGLCBEL() {
        return GLCBEL;
    }

    public void setGLCBEL(String GLCBEL) {
        this.GLCBEL = GLCBEL;
    }

    public String getEFFDAT() {
        return EFFDAT;
    }

    public void setEFFDAT(String EFFDAT) {
        this.EFFDAT = EFFDAT;
    }

    public String getEXPDAT() {
        return EXPDAT;
    }

    public void setEXPDAT(String EXPDAT) {
        this.EXPDAT = EXPDAT;
    }

    public String getAMDTLR() {
        return AMDTLR;
    }

    public void setAMDTLR(String AMDTLR) {
        this.AMDTLR = AMDTLR;
    }
//    }
}
