package cn.nexuslink.pojo;

import java.util.ArrayList;

/**
 * Created by xfh on 2019/2/25.
 */
public class PrescriptionHistoryDTO {
    private ArrayList<PrescriptionDTO> prescription;
    private Double price;
    private String time;

    public ArrayList<PrescriptionDTO> getPrescription() {
        return prescription;
    }

    public void setPrescription(ArrayList<PrescriptionDTO> prescription) {
        this.prescription = prescription;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "PrescriptionHistoryDTO{" +
                "prescription=" + prescription +
                ", price=" + price +
                ", time='" + time + '\'' +
                '}';
    }
}
