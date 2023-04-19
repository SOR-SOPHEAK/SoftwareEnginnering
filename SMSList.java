import java.util.ArrayDeque;

public class SMSList {
    private String Subject;
    private String FromPhoneNumber;
    private String Reciever;
    private String Type;    //Text, MMs
    private String Content;
    private boolean Status; //new, read
    private String SECRETe_KEY;
    
    public SMSList(String subject, String fromPhoneNumber, String reciever, String content, String sECRETe_KEY) {
        Subject = subject;
        FromPhoneNumber = fromPhoneNumber;
        Reciever = reciever;
        Content = content;
        SECRETe_KEY = sECRETe_KEY;
    }

    public SMSList(String subject, String fromPhoneNumber, String reciever, String content, String sECRETe_KEY, String type) {
        Subject = subject;
        FromPhoneNumber = fromPhoneNumber;
        Reciever = reciever;
        Type = type;
        Content = content;
        SECRETe_KEY = sECRETe_KEY;
    }

    public SMSList(String subject, String fromPhoneNumber, String reciever, String content, String sECRETe_KEY, String type, boolean status) {
        Subject = subject;
        FromPhoneNumber = fromPhoneNumber;
        Reciever = reciever;
        Type = type;
        Content = content;
        Status = status;
        SECRETe_KEY = sECRETe_KEY;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getFromPhoneNumber() {
        return FromPhoneNumber;
    }

    public void setFromPhoneNumber(String fromPhoneNumber) {
        FromPhoneNumber = fromPhoneNumber;
    }

    public String getReciever() {
        return Reciever;
    }

    public void setReciever(String reciever) {
        Reciever = reciever;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public String getSECRETe_KEY() {
        return SECRETe_KEY;
    }

    public void setSECRETe_KEY(String sECRETe_KEY) {
        SECRETe_KEY = sECRETe_KEY;
    }
    
    
}
