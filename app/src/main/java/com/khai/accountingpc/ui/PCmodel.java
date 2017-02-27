package com.khai.accountingpc.ui;

/** Class parse string data in QR code
 *@author Pilipenko Ihor
*/
public class PCmodel {
    private String Name;
    private String OldId;
    private String Id;
    private String Mac;
    private String Ip;
    private String Notes;

    /** Parse string data to fields
     * @param qrCode - string from QR
    */
    public PCmodel(String qrCode) {
        String data[] = qrCode.split(";");
        Name = data[0];
        OldId = data[1];
        Id = data[2];
        Mac = data[3];
        Ip = data[4];
        Notes = data[5].replace("_", "");

    }

    /** Gets the name of PC
     * @return PC name
    */
    public String getName() {
        return Name;
    }

    /** Gets the old ID of PC
     * @return PC old ID
    */
    public String getOldId() {
        return OldId;
    }

    /** Gets the ID of PC
     * @return PC ID
    */
    public String getId() {
        return Id;
    }

    /** Gets the MAC adress of PC
     * @return PC MAC adress
    */
    public String getMac() {
        return Mac;
    }

    /** Gets the IP adress of PC
     * @return PC IP adress
    */
    public String getIp() {
        return Ip;
    }

    /** Gets the notes of PC
     * @return PC notes
    */
    public String getNotes() {
        return Notes;
    }
}
