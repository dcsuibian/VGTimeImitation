package com.dcsuibian.util;

import java.util.UUID;

public class UUIDProvider {
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    public static String getShortUUID(int length){
        return getUUID().substring(0,length);
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID());
        System.out.println(UUIDProvider.getUUID());
        System.out.println(getShortUUID(12));
    }
}
