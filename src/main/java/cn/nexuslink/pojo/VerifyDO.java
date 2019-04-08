package cn.nexuslink.pojo;



import java.sql.Timestamp;

/**
 * Created by xfh on 2018/12/23.
 */

public class VerifyDO {

    private Long id;
    private String tele;
    private String code;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;

    @Override
    public String toString() {
        return "VerifyDO{" +
                "id=" + id +
                ", tele='" + tele + '\'' +
                ", code='" + code + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
}
