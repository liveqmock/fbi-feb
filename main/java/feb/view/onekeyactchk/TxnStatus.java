package feb.view.onekeyactchk;

import java.util.Hashtable;

/**
 * User: zhanrui
 */
public enum TxnStatus implements EnumApp {
    INIT("00", "��ʼ"),
    INFORM_STARTED("01", "��֪ͨ����"),
    INFORM_SUCC("10", "�������˳ɹ�"),
    INFORM_FAIL("11", "��������ʧ��"),
    ACCT_RESULT_QRY_STARTED("20", "�ѿ�ʼ��ѯ���˽��"),
    ACCT_UNDERWAY("21", "���˽�����"),
    ACCT_FAIL_EXCEPTION("22", "���˹����쳣"),
    ACCT_SUCC_BANLANCE("30", "���˽��:ƽ��"),
    ACCT_SUCC_NOTBANLANCE("31", "���˽��:��ƽ");

    private String code = null;
    private String title = null;
    private static Hashtable<String, TxnStatus> aliasEnums;

    TxnStatus(String code, String title) {
        this.init(code, title);
    }

    @SuppressWarnings("unchecked")
    private void init(String code, String title) {
        this.code = code;
        this.title = title;
        synchronized (this.getClass()) {
            if (aliasEnums == null) {
                aliasEnums = new Hashtable();
            }
        }
        aliasEnums.put(code, this);
        aliasEnums.put(title, this);
    }

    public static TxnStatus valueOfAlias(String alias) {
        return aliasEnums.get(alias);
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }
}