package cn.nexuslink.pojo;

import java.sql.Timestamp;

/**
 * Created by xfh on 2018/12/27.
 */
public class PrescriptionDO {
    private Long id;
    private String disease;
    private Short isUserDefined;
    private Double price;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public Short getIsUserDefined() {
        return isUserDefined;
    }

    public void setIsUserDefined(Short isUserDefined) {
        this.isUserDefined = isUserDefined;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Timestamp getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Timestamp gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "PrescriptionDO{" +
                "id=" + id +
                ", disease='" + disease + '\'' +
                ", isUserDefined=" + isUserDefined +
                ", price=" + price +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
