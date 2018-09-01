package xyz.stackoverflow.blog.pojo;

import java.io.Serializable;

public class ValidateResult implements Serializable {

    private Integer status;
    private String emailErrorInfo;
    private String passwordErrorInfo;
    private String nicknameErrorInfo;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEmailErrorInfo() {
        return emailErrorInfo;
    }

    public void setEmailErrorInfo(String emailErrorInfo) {
        this.emailErrorInfo = emailErrorInfo;
    }

    public String getPasswordErrorInfo() {
        return passwordErrorInfo;
    }

    public void setPasswordErrorInfo(String passwordErrorInfo) {
        this.passwordErrorInfo = passwordErrorInfo;
    }

    public String getNicknameErrorInfo() {
        return nicknameErrorInfo;
    }

    public void setNicknameErrorInfo(String nicknameErrorInfo) {
        this.nicknameErrorInfo = nicknameErrorInfo;
    }
}