package com.yangqihang.entity;


public class Account {
    private Integer id;
    private String loginName;
    private String password;
    private String nickName;
    private Integer age;
    private String location;

    public Account() {
    }

    public Account(Integer id, String loginName, String password, String nickName, Integer age, String location) {
        this.id = id;
        this.loginName = loginName;
        this.password = password;
        this.nickName = nickName;
        this.age = age;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", age=" + age +
                ", location='" + location + '\'' +
                '}';
    }
}
