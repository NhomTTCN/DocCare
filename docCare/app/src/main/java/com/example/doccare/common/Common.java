package com.example.doccare.common;


import java.text.SimpleDateFormat;
import java.util.Locale;

//    object DateUtils{
//            fun fromMillisToTimeString(millis:Long):String{
//            val format=SimpleDateFormat("hh:mm a",Locale.getDefault())
//            return format.format(millis)
//            }
//            }
class DateUtils{
    public String fromMillistoTimeString(Long millis){
        SimpleDateFormat format = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        return format.format(millis);
    }
}