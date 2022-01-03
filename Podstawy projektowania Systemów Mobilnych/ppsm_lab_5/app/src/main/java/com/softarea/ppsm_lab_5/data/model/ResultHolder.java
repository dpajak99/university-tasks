package com.softarea.ppsm_lab_5.data.model;

public class ResultHolder {
  private String result;
  private int resultLength;

  public ResultHolder(String result, int resultLength) {
    this.result = result;
    this.resultLength = resultLength;
  }

  public String getResult() {
    return result;
  }

  public int getResultLength() {
    return resultLength;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public void setResultLength(int resultLength) {
    this.resultLength = resultLength;
  }
}
