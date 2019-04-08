package cn.nexuslink.pojo;

/**
 * Created by xfh on 2018/12/28.
 */
public class UserSignDTO {
    private String tele;
    private String password;
    private String code;
    private String name;

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserSignDTO{" +
                "tele='" + tele + '\'' +
                ", password='" + password + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
