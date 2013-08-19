package gateway.sbs.core.domain;

import java.lang.reflect.Field;

/**
 * Form ����
 */
public abstract class AssembleForm implements Assemble {
    protected int offset = 0;

    //�ֶ�����  1���ַ��� 2��������
    protected int[] fieldTypes;

    //�ֶγ���  �ֽ���
    protected int[] fieldLengths;

    public void assembleFields(byte[] buffer) {
        Class clazz = this.getClass();
        try {
            Field[] fields = clazz.getDeclaredFields();
            int pos = this.offset;
            for (int i = 0; i < this.fieldLengths.length; i++) {
                byte[] bytes = new byte[fieldLengths[i]];
                System.arraycopy(buffer, pos, bytes, 0, bytes.length);
                fields[i].setAccessible(true);
                if (this.fieldTypes[i] == 1) {
                    String s = new String(bytes);
                    fields[i].set(this, s.trim());
                } else if (this.fieldTypes[i] == 2) {
                    short len = 0;
                    len = (short) ((bytes[0] << 8 & 0xFF00) | (bytes[1] & 0x00FF));
                    fields[i].set(this, len);
                } else {
                    throw new RuntimeException("�ֶ�����");
                }
                pos += bytes.length;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("���Ĵ�������", e);
        }
    }
}
