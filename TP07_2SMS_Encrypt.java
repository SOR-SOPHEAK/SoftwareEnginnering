import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Scanner;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class TP07_2SMS_Encrypt {
    ArrayDeque<SMSList> smsList = new ArrayDeque<>();
    String fileName; 

    public ArrayDeque<SMSList> readFile(){
        ArrayDeque<SMSList> listTmp = new ArrayDeque<>();
        try{
            File myObj = new File(fileName);
            Scanner sc = new Scanner(myObj);
            String[] Data = new String[7];
            Data[3] = "";

            int j=0;

            while(sc.hasNextLine()){
                String data = sc.nextLine();

                if(!data.equalsIgnoreCase("end")){
                    if(!data.equalsIgnoreCase("EndMSG") && j==3){
                        Data[j] += data+ "\n";
                    }
                    else if(data.equalsIgnoreCase("EndMSG")){
                        String tmp = "";
                        for(int i=0; i<Data[j].length()-1; i++){
                            tmp += Data[j].charAt(i);
                        }
                        Data[j] = tmp;
                        j++;
                    }
                    else{
                        Data[j] = data;
                        j++;
                    }
                }
                else{
                    boolean isStatus = Data[6].equalsIgnoreCase("new")?false:true;
                    listTmp.add(new SMSList(Data[0], Data[1], Data[2], Data[3], Data[4], Data[5], isStatus));
                    j=0;
                    Data[3] = "";
                }
            }
            sc.close();
        }
        catch(FileNotFoundException excp){
            System.out.println("Error!!");
            excp.printStackTrace();
        }
        return listTmp;
    }

    public TP07_2SMS_Encrypt(){
        this.fileName = "ListOfSMS.txt";
        this.smsList = readFile().clone();
    }

    public TP07_2SMS_Encrypt(String fileName){
        this.fileName = fileName;
        this.smsList = readFile().clone();
    }

    public static int getMax_characters_per_sms(){
        return 160;
    }

    public void ListEachData(SMSList sms, int index){
        AES aes = new AES();
        aes.setSECRET_KEY(sms.getSECRETe_KEY());
        String Status = sms.isStatus()? "Read":"New";

        System.out.print("\t"+aes.decrypt((sms.getSubject())));
        System.out.print("\t"+aes.decrypt((sms.getFromPhoneNumber())));
        System.out.print("\t"+aes.decrypt((sms.getReciever())));
        System.out.print("\t"+sms.getType());
        System.out.print("\t"+Status);
    }

    public void ListEachData(SMSList sms, int index, String reciever){
        AES aes=new AES();
        aes.setSECRET_KEY(sms.getSECRETe_KEY());
        String Status = sms.isStatus()? "Read":"New";
        
        String tmpR = reciever.equals(aes.decrypt(sms.getReciever()))?aes.encrypt("We"):sms.getReciever();
        String tmpS = reciever.equals(aes.decrypt(sms.getFromPhoneNumber()))?aes.encrypt("We"):sms.getReciever();
        
        
        System.out.print("\t"+aes.decrypt((sms.getSubject())));
        System.out.print("\t"+aes.decrypt((sms.getFromPhoneNumber())));  
        System.out.print("\t"+aes.decrypt(tmpR));
        System.out.print("\t"+sms.getType());
        System.out.print("\t"+Status);
    }

    public void ViewSMSDetail(SMSList sms){
        AES aes = new AES();
        aes.setSECRET_KEY(sms.getSECRETe_KEY());
        String Status=sms.isStatus()? "Read":"New";
        System.out.println("\n\tSubject: \t"+ aes.decrypt(sms.getSubject()));
        System.out.println("\tFrom: \t\t"+ aes.decrypt(sms.getFromPhoneNumber()));
        System.out.println("\tTo: \t\t"+ aes.decrypt(sms.getReciever()));
        System.out.println("\tType: \t\t"+ (sms.getType()));
        System.out.println("\tStatus: \t"+(Status));
        System.out.println("\tMessage: \t"+(sms.getContent()));
    }

    public void ViewSMSDetail(SMSList sms,String receiver){
        AES aes = new AES();
        aes.setSECRET_KEY(sms.getSECRETe_KEY());
        String Status = sms.isStatus()? "Read":"New";
        System.out.println("\n\tSubject: \t"+ aes.decrypt(sms.getSubject()));
        System.out.println("\tFrom: \t"+ aes.decrypt(sms.getFromPhoneNumber()));
        System.out.println("\tTo: \t\t"+ aes.decrypt(sms.getReciever()));
        System.out.println("\tType: \t"+ (sms.getType()));
        System.out.println("\tStatus: \t"+(Status));
        if(receiver.equals(aes.decrypt(sms.getReciever()))){
            System.out.println("\tMessage: \t"+aes.encrypt(sms.getContent()));
        }else if(receiver.equals(AES.decrypt(sms.getFromPhoneNumber()))){
            System.out.println("\tMessage: \t"+aes.encrypt(sms.getContent()));
        }else {
            System.out.println("\tMessage: \t"+(sms.getContent()));
        }
    }

    public SMSList FindSMSByIndex(int index){
        var tmp = smsList.clone();
        int l = smsList.toArray().length;

        for(int i=0;i<l;i++) {
            if(index==i){
                return tmp.getFirst();
            }
            tmp.pollFirst();
        }
        return tmp.getFirst();
    }

    public void ListingBySecreteKey(String SECRETE_KEY){
        System.out.print("\n\tSubject ");
        System.out.print("\tFrom \t");
        System.out.print("\tTo \t");
        System.out.print("\tType ");
        System.out.print("\tStatus ");
        System.out.println("\n\t--------------------------------------------------------------\n");

        var tmp = smsList.clone();
        int Len = smsList.size();
        for(int i=0;i<Len;i++) {
            if(tmp.getFirst().getSECRETe_KEY().equals(SECRETE_KEY)){
                ListEachData(tmp.getFirst(), i+1);
            }
            tmp.pollFirst();
            System.out.println(" ");
        }
    }

    public boolean FindSecreteKey(String SECRETE_KEY){
        var tmp = smsList.clone();
        
        for(int i=0; i<smsList.size(); i++){
            if(tmp.getFirst().getSECRETe_KEY().equals(SECRETE_KEY)){
                return true;
            }
            tmp.pollFirst();
            System.out.println("\n");
        }
        return false;
    }

    public int SizeOfArray(){
        return smsList.toArray().length;
    }

    public void ListSMS(){
        System.out.print("\n\tSubject: ");
        System.out.print("\tFrom: \t");
        System.out.print("\tTo: \t");
        System.out.print("\tType: ");
        System.out.print("\tStatus: ");
        System.out.println("\n\t--------------------------------------------------------------\n");
    
        var tmp = smsList.clone();
        int Len = smsList.size();
        for(int i=0; i<Len; i++) {
            ListEachData(tmp.getFirst(), i+1);
            tmp.pollFirst();
            System.err.println(" ");
        }
    }

    public void ListSMS(String UserName){
        System.out.print("\n\tSubject ");
        System.out.print("\tFrom \t");
        System.out.print("\tTo \t");
        System.out.print("\tType ");
        System.out.print("\tStatus ");
        System.out.println("\n\t--------------------------------------------------------------\n");

        var tmp = smsList.clone();
        int Len = smsList.size();
        for(int i=0; i<Len; i++) {
            ListEachData(tmp.getFirst(), i+1,UserName);
            tmp.pollFirst();
            System.out.println("\n");
        }
    }

    public void SaveToFile(){
        var tmp = smsList.clone();
        int l = smsList.size();
        String TextTmp="";
        String Status;
        for(int i=0;i<l;i++) {
            Status=tmp.getFirst().isStatus()? "Read":"New";
            TextTmp+=tmp.getFirst().getSubject()+"\n";
            TextTmp+=tmp.getFirst().getFromPhoneNumber()+"\n";
            TextTmp+=tmp.getFirst().getReciever()+"\n";
            TextTmp+=tmp.getFirst().getContent()+"\nEndMSG\n";
            TextTmp+=tmp.getFirst().getSECRETe_KEY()+"\n";
            TextTmp+=tmp.getFirst().getType()+"\n";
            TextTmp+=Status+"\nEnd\n";
            tmp.pollFirst();
        }
        try {
            FileWriter newFile = new FileWriter(fileName);
            newFile.write(TextTmp);
            newFile.close();
        } catch (IOException e) {
            System.out.println("Error!!");
            e.printStackTrace();
        }  
    }

    public void Remove(SMSList sms){
        smsList.remove(sms);
        SaveToFile();
    }
    public void Add(SMSList sms){
        AES aes = new AES();
        aes.setSECRET_KEY(sms.getSECRETe_KEY());
        
        sms.setSubject(aes.encrypt(sms.getSubject()));
        sms.setFromPhoneNumber(aes.encrypt(sms.getFromPhoneNumber()));
        sms.setReciever(aes.encrypt(sms.getReciever()));
        sms.setContent(aes.encrypt(sms.getContent()));
        smsList.add(sms);
        SaveToFile();
    }
    public void Add(String msg,String SECRET_KEY){
        AES aes = new AES();
        aes.setSECRET_KEY(SECRET_KEY);
        var sms = new SMSList("No", "Unknown", "Unknown",msg,SECRET_KEY);
       
        sms.setSubject(aes.encrypt(sms.getSubject()));
        sms.setFromPhoneNumber(aes.encrypt(sms.getFromPhoneNumber()));
        sms.setReciever(aes.encrypt(sms.getReciever()));
        sms.setContent(aes.encrypt(sms.getContent()));
        smsList.add(sms);
        SaveToFile();
        
    }
    public void Add(String msg,String Title,String SECRET_KEY){
        AES aes = new AES();
        AES.setSECRET_KEY(SECRET_KEY);
        var sms = new SMSList(Title, "Unknown", "Unknown",msg,SECRET_KEY);
        sms.setSubject(aes.encrypt(sms.getSubject()));
        sms.setFromPhoneNumber(aes.encrypt(sms.getFromPhoneNumber()));
        sms.setReciever(aes.encrypt(sms.getReciever()));
        sms.setContent(aes.encrypt(sms.getContent()));
        smsList.add(sms);
        SaveToFile();
    }

    public void upDateStatus(boolean isStatus,int index){
        Iterator<SMSList> value = smsList.iterator();
        int j=0;
        while (value.hasNext()) {
            value.next();
            if (j==index ){
                value.next().setStatus(isStatus);
                break;
            } 
            
            j++;
        }
        SaveToFile();
    }

    public void readableSMS(){
        
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TP07_2SMS_Encrypt listOfSMS = new TP07_2SMS_Encrypt();

        int option;

        do{
            System.out.println("\n\t1. Send new SMS with Encrypted content using password method."+
            "\n\t2. View SMS detail."+
            "\n\t3. List SMSes"+
            "\n\t4. Remove SMSes by index"+
            "\n\t5. Quit");
            System.out.print("\n\t=> Chose an option: ");
            option = sc.nextInt();

            switch(option){
                case 1:
                    System.out.println("\n\t========Input Message To Send ========");
                    System.out.print("\tSubject: \t");
                    String subject = sc.nextLine();
                    subject = sc.nextLine();
                    System.out.print("\tFrom Phone Number: \t");
                    String sender = sc.next();
                    System.out.print("\tReciever Phone number: \t");
                    String reciever = sc.next();
                    System.out.print("\tType(Text/MMS): \t");
                    String type = sc.next();
                    System.out.print("\tContent: \t");
                    String msg = sc.nextLine();
                    msg = sc.nextLine();
                    System.out.print("\tSecret Key: \t");
                    String sk = sc.nextLine();
                
                listOfSMS.Add(new SMSList(subject,sender,reciever,msg,sk,type));
                    break;
                case 2:
                    System.out.print("\n\tDetail SMS by index: \t");
                    int index = sc.nextInt();
                    if(index>listOfSMS.SizeOfArray() || index<0){
                        System.out.println("\t****No SMS by this index.");
                    }
                    else{
                        listOfSMS.ViewSMSDetail(listOfSMS.FindSMSByIndex(index-1));
                    }
                    break;
                    
                case 3:
                    listOfSMS.ListSMS();
                    break;
                case 4:
                    System.out.print("\n\tInput index to remove : \t");
                    index = sc.nextInt();
                    if(index>listOfSMS.SizeOfArray() || index<0){
                        System.out.println("\t****Index NOT Found!!");
                    }
                    else{
                        listOfSMS.Remove(listOfSMS.FindSMSByIndex(index-1));
                        System.out.println("\t ***Remove Successful!!");
                    }
                    break;
                case 5:
                System.out.println("\tQuit!!!");
                    System.exit(1);
                    break;
            }
        }while(option != 5);
    }
}