package com.khai.accountingpc.ui;


public class PCmodel {
    private String Name;
    private String OldId;
    private String Id;
    private String Mac;
    private String Ip;
    private String Notes;

    public PCmodel(String qrCode) {
        String data[] = qrCode.split(";");
        Name = data[0];
        OldId = data[1];
        Id = data[2];
        Mac = data[3];
        Ip = data[4];
        Notes = data[5].replace("_", "");

    }

    public String getName() {
        return Name;
    }

    public String getOldId() {
        return OldId;
    }

    public String getId() {
        return Id;
    }

    public String getMac() {
        return Mac;
    }

    public String getIp() {
        return Ip;
    }

    public String getNotes() {
        return Notes;
    }
}
