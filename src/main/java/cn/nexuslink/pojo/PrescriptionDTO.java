package cn.nexuslink.pojo;

/**
 * Created by xfh on 2018/12/30.
 */
public class PrescriptionDTO {
    private String medicineName;
    private int weight;

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "PrescriptionDTO{" +
                "medicineName='" + medicineName + '\'' +
                ", weight=" + weight +
                '}';
    }
}
