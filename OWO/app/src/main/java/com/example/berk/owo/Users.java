package com.example.berk.owo;

public class Users
{

   private String user_mail;
   private String user_name;
   private String password;
   private int user_level;
   public Users(String user_mail, String user_name, String password, int user_level)
   {
       this.user_level = user_level;
       this.user_mail = user_mail;
       this.password = password;
       this.user_name = user_name;
   }

}
