package cn.nexuslink.pojo;

/**
 * Created by xfh on 2019/2/21.
 */
public class Medicine {
    private String medicineName;
    private String medicinePic;
    private String intro;
    private String notice;

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

    @Override
    public String toString() {
        return "Medicine{" +
                "medicineName='" + medicineName + '\'' +
                ", medicinePic='" + medicinePic + '\'' +
                ", intro='" + intro + '\'' +
                ", notice='" + notice + '\'' +
                '}';
    }
}
