package com.art.beans.famous;

import java.io.Serializable;


/**
 * 搜索提示词
 * @author wslixiaoliang
 */
public class SuggestResult implements Serializable {

    private String [] suggestions;
    private String returnCode;
    private String returnMessage;

    public SuggestResult() {
    }

    public SuggestResult(String[] suggestions, String returnCode, String returnMessage) {
        this.suggestions = suggestions;
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
    }

    public String[] getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(String[] suggestions) {
        this.suggestions = suggestions;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }
}
