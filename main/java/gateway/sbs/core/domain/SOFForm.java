package gateway.sbs.core.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * sbs��Ӧ����form
 */
public class SOFForm {
    private static Logger logger = LoggerFactory.getLogger(SOFForm.class);

    private SOFFormHeader formHeader;
    public int formHeaderLength = 27;
    public short formDataLength;
    private SOFFormData formData;
    public int length;

    public void assembleFields(int offset, byte[] buffer) {
        // ��ͷ����
        formHeader = new SOFFormHeader(offset);
        formHeader.assembleFields(buffer);
        // ���峤��
        byte[] dataLengthBytes = new byte[2];
        System.arraycopy(buffer, offset + formHeaderLength, dataLengthBytes, 0, dataLengthBytes.length);
        short s1 = (short) (dataLengthBytes[1] & 0xff);
        short s2 = (short) ((dataLengthBytes[0] << 8) & 0xff00);
        formDataLength = (short) (s2 | s1);
        // ���ܳ���
        length = formHeaderLength + 2 + formDataLength;
        // ����
        logger.info("FormCode:" + formHeader.getFormCode() + "���峤�ȣ�" + formDataLength);
        if (formDataLength != 0) {
            formData = new SOFFormData(formDataLength);
            formData.assembleBeans(offset + formHeaderLength + 2, buffer, formHeader.getFormCode());
        }
    }

    public SOFFormHeader getFormHeader() {
        return formHeader;
    }

    public SOFFormData getFormData() {
        return formData;
    }
}
