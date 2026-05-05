package com.taskservice.multitenanttaskservice.dto;

import com.google.gson.Gson;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
public class ResponseData {

    private String status;
    private String message;
    private Object data;
    private String errmsg;
    private Integer totalRecords;

   public static Gson gson =  new Gson();

    public ResponseData(String status, String message, List<String> emptyList, String errmsg) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.errmsg = errmsg;
    }

    public ResponseData(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static String generateFailedRes(String message,Object data,String errmsg)
   {
      ResponseData responseData = new ResponseData("0",message,AppConstant.EMPTY_LIST,errmsg);
      String finalJson = gson.toJson(responseData);
      return  finalJson;
   }
   public static String generateSuccessRes(String message,Object data,Integer totalRecords)
   {
       ResponseData responseData = new ResponseData("1",message,data == null ? AppConstant.EMPTY_LIST :data);
       responseData.setTotalRecords(totalRecords);
       String finalJson = gson.toJson(responseData);
       return finalJson;

   }
   public static String generateSuccessRes(String message,Object data)
   {
       ResponseData responseData = new ResponseData("1",message,data == null ?AppConstant.EMPTY_LIST
                                                                                    :data);
       String finalJson = gson.toJson(responseData);
       return finalJson;
   }

   public static String generateFailedValidationRes(String errmsg)
   {
       ResponseData responseData = new ResponseData("0","Failed",AppConstant.EMPTY_LIST,errmsg);
       String finalJson = gson.toJson(responseData);
       return finalJson;
   }



}
