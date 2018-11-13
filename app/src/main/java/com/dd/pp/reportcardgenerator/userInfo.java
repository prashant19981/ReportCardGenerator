package com.dd.pp.reportcardgenerator;

/**
 * Created by MUJ on 11/10/2018.
 */

public class userInfo {
    private String userName;
    private Long regNumber;

    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setRegNumber(Long regNumber){
        this.regNumber = regNumber;
    }
    public String getUserName(){
        return this.userName;
    }
    public Long getRegNumber(){
        return this.regNumber;
    }


}
