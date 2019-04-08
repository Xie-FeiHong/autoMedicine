package cn.nexuslink.pojo;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by xfh on 2019/4/2.
 */
public class HtmlPerson {
    private String name;
    private String code;
    private String pwd;
    private String sex;
    private MultipartFile photo;
    private String school;
    private String xueYuan;
    private String zhaunye;
    private String hobby;
    private String date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getXueYuan() {
        return xueYuan;
    }

    public void setXueYuan(String xueYuan) {
        this.xueYuan = xueYuan;
    }

    public String getZhaunye() {
        return zhaunye;
    }

    public void setZhaunye(String zhaunye) {
        this.zhaunye = zhaunye;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "HtmlPerson{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", pwd='" + pwd + '\'' +
                ", sex='" + sex + '\'' +
                ", photo=" + photo +
                ", school='" + school + '\'' +
                ", xueYuan='" + xueYuan + '\'' +
                ", zhaunye='" + zhaunye + '\'' +
                ", hobby='" + hobby + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
