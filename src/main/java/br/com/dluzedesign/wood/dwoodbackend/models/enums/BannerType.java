package br.com.dluzedesign.wood.dwoodbackend.models.enums;

public enum BannerType {
    SUPERBANNER(1),
    BANNER1(2),
    BANNER2(3),
    CARROUSEL(4);

    private int code;

    private BannerType(int code) {
        this.code = code;
    }
    public int getCode(){
        return code;
    }
    public static BannerType valueOf(int code){
        for (BannerType value: BannerType.values()){
            if(value.getCode() == code){
                return value;

            }
        }
        throw new IllegalArgumentException("Invalid BannerType Code");
    }
}
