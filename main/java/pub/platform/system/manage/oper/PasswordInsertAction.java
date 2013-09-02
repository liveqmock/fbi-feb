package pub.platform.system.manage.oper;

import gateway.sbs.core.CtgManager;
import gateway.sbs.core.SBSRequest;
import gateway.sbs.core.SBSResponse;
import pub.platform.form.control.*;
import pub.platform.system.manage.dao.*;

import java.util.ArrayList;
import java.util.List;

public class PasswordInsertAction extends Action {
    PtOperBean operbean = new PtOperBean();

    public int doBusiness() {

        operbean.setOperpasswd(this.req.getFieldValue("newpwd"));

        this.res.setType(0);

        if (!changeSbsPwd(this.req.getFieldValue("operid"), this.req.getFieldValue("operid"))) {
            this.res.setResult(false);
            this.res.setMessage("更新密码失败！");
            this.dc.close();
            return -1;
        }

        if (operbean.updateByWhere(" where (deptid='" + this.req.getFieldValue("deptid") + "')and(operid='" + this.req.getFieldValue("operid") + "')") < 0) {

            this.res.setResult(false);
            this.res.setMessage("更新密码失败！");
            this.dc.close();
            return -1;
        }

        this.res.setResult(true);
        this.res.setMessage("更新密码成功！");
        this.dc.close();
        return 0;
    }

    public boolean changeSbsPwd(String tellerId, String termId) {
        List<String> paramList = new ArrayList<>();
        String oldpwd = this.req.getFieldValue("oldpwd");
        String newpwd = this.req.getFieldValue("newpwd");
        String checkpwd = this.req.getFieldValue("checkpwd");
        paramList.add(oldpwd);
        paramList.add(newpwd);
        paramList.add(checkpwd);
        CtgManager ctgManager = new CtgManager();
        SBSRequest sbsRequest = new SBSRequest(tellerId, termId, "0005", paramList);
        SBSResponse sbsResponse = new SBSResponse();
        ctgManager.processSingleResponsePkg(sbsRequest, sbsResponse);
        return sbsResponse.getFormCodes().contains("T924");
    }

}
