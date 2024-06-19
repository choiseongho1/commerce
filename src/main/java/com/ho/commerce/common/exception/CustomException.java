package com.ho.commerce.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CustomException extends RuntimeException{
   public CustomException (){

   }

   public CustomException(String message){
       super(message);
   }
}