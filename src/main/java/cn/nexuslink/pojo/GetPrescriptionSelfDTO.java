package cn.nexuslink.pojo;

import java.util.ArrayList;

/**
 * Created by xfh on 2018/12/30.
 */
public class GetPrescriptionSelfDTO {
    private String label;
    private boolean flag;
    private ArrayList<PrescriptionDTO> prescription;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public ArrayList<PrescriptionDTO> getPrescription() {
        return prescription;
    }

    public void setPrescription(ArrayList<PrescriptionDTO> prescription) {
        this.prescription = prescription;
    }

    @Override
    public String toString() {
        return "GetPrescriptionSelfDTO{" +
                "label='" + label + '\'' +
                ", flag=" + flag +
                ", prescription=" + prescription +
                '}';
    }
}
