package sample.plugin;

import java.io.Serializable;

/**
 * @author stepanm
 */
public class RegexPattern implements Serializable{

    private String regexName;
    private int ocurrance;

    public RegexPattern(String regexName, int ocurrance) {
        this.regexName = regexName;
        this.ocurrance = ocurrance;
    }

    public RegexPattern(String regexName) {
        this.regexName = regexName;
    }

    public RegexPattern() {
    }

    public String getRegexName() {
        return regexName;
    }

    public void setRegexName(String regexName) {
        this.regexName = regexName;
    }

    public int getOcurrance() {
        return ocurrance;
    }

    public void setOcurrance(int ocurrance) {
        this.ocurrance = ocurrance;
    }
}
