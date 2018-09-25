package xyz.stackoverflow.blog.util;

public enum ResponseStatusEnum {

    SUCCESS(0,"成功"),
    EMAILERROR(1,"邮箱格式错误"),
    NICKNAMEERROR(2,"昵称长度要大于0"),
    PASSWORDERROR(3,"密码长度要大于等于6,且为0-9,a-z,A-Z之间"),
    VCODEERROR(4,"验证码错误"),
    OLDPASSWORDERROR(5,"旧密码不匹配"),
    EMAILEXISTERROR(6,"邮箱已经存在"),
    HEADERROR(7,"头像上传失败"),
    SIGNATRUEERROR(8,"个性签名长度要大于0");

    private Integer status;
    private String message;

    ResponseStatusEnum(Integer status,String message){
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
