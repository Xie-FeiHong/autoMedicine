package cn.nexuslink.pojo;

import java.sql.Timestamp;

/**
 * Created by xfh on 2018/12/27.
 */
public class MedicineDO {
    private Long id;
    private String medicineName;
    private String medicinePic;
    private String intro;
    private String notice;
    private Integer price;

    private Timestamp gmtCreate;
    private Timestamp gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicinePic() {
        return medicinePic;
    }

    public void setMedicinePic(String medicinePic) {
        this.medicinePic = medicinePic;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
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
        return "MedicineDO{" +
                "id=" + id +
                ", medicineName='" + medicineName + '\'' +
                ", medicinePic='" + medicinePic + '\'' +
                ", intro='" + intro + '\'' +
                ", notice='" + notice + '\'' +
                ", price=" + price +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
