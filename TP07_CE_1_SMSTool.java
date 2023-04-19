import java.util.HashMap; 
import java.io.FileWriter;
import java.util.Scanner;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
public class TP07_CE_1_SMSTool {


    public static void main(String[] args) {
        
        Users users = new Users();
        boolean isLogin=false;
        String UserName="sopheak";
        String Password="1066";

        Scanner sc = new Scanner(System.in);
        
        TP07_2SMS_Encrypt sendSMS=new TP07_2SMS_Encrypt("SMSTool.txt");

        while(true){
            if(!isLogin){
                System.out.println("\n\t---------- Welcome to private SMS app -----------");
                System.out.println("\t1. Login"+
                                    "\n\t2. Register"+
                                    "\n\t3. Quit");
                int option;
    
                System.out.print("\n\t=> Choose an option: ");
                option = sc.nextInt();
    
                if(option==1){
                    System.out.print("\n\tUsername: \t");
                    UserName = sc.nextLine();
                    UserName = sc.nextLine();
                    System.out.print("\tPassword: \t");
                    Password = sc.nextLine();
    
                    if(users.Login(UserName, Password)){
                        isLogin = true;
                        System.out.println("\n\tLogin Successful!!");
                    }
                    else{
                        System.out.println("\t***Incorrect User name or Password");
                    }
                }
                else if(option==2){
                    System.out.print("\tUsername: \t");
                    UserName = sc.nextLine();
                    UserName = sc.nextLine();

                    while(!users.isUnique(UserName)){
                        System.out.println("\tUser name Already Exist");
                        System.out.print("\tInput username again: \t");
                        UserName = sc.nextLine();
                    }

                    System.out.print("\tPassword: \t");
                    Password = sc.nextLine();
                    if(users.Register(UserName, Password)){
                        isLogin=true;
                        System.out.println("\t***Register Succesful");
                    }
                    else System.out.println("\t***Something when worng!!!");
                }
                else if(option==3)
                    System.exit(1);   
            }
            else{
                System.out.println("\n\t---------- Welcome to private SMS app -----------");
                System.out.println("\n\t\t1. List all SMS\n"+
                            "\t\t2. View SMS Detail\n"+
                            "\t\t3. Send SMS\n"+
                            "\t\t4. Remove SMS by index\n"+
                            "\t\t5. Quit\n");
                int option;
                System.out.print("\t=> Choose an option: ");
                option = sc.nextInt();

                if(option==1){
                    if(sendSMS.SizeOfArray()>0){
                        sendSMS.ListSMS(UserName); 
                    }
                    else{
                        System.out.println("\t***No data!!!");
                    }   
                }
                else if(option==2){
                    int index;
                    
                    System.out.println("\t***Only Onwer or Reciever Could See message");
                    sendSMS.ListSMS(UserName);
                    System.out.print("\n\tInput an index:\t");
                    index = sc.nextInt();

                    if(index<sendSMS.SizeOfArray() && index>0){
                        sendSMS.ViewSMSDetail(sendSMS.FindSMSByIndex(index-1),UserName);
                        sendSMS.upDateStatus(true, index);
                    }else{
                        System.out.println("\t\t -- There no data here --");
                    }
                }
                else if(option==3){
                    System.out.print("\n\tTitle: \t");
                    String Subject = sc.nextLine();
                    Subject = sc.nextLine();
                    System.out.print("\tFrome : \t");
                    String Sender = sc.nextLine();

                    while(Sender.length()>10){
                        System.out.println("\t***Invalid Phone Number!!");
                        System.out.print("\tSend to: \t");
                        Sender = sc.nextLine();
                    }

                    System.out.print("\tSend to: \t");
                    String Reciever = sc.nextLine();

                    while(Reciever.length()>10){
                        System.out.println("\t***Invalid Phone Number!!");
                        System.out.print("\tSend to: \t");
                        Reciever = sc.nextLine();
                    }
                    System.out.print("\tType: \t\t");
                    String Type = sc.nextLine();
                    while(!(Type.equalsIgnoreCase("Text") || Type.equalsIgnoreCase("MMS"))){
                        System.out.println("\t***Invalid Type!!!");
                        System.out.print("\tType: \t\t");
                        Type = sc.nextLine();
                    }
                    System.out.print("\tContent: \t");
                    String msg = sc.nextLine();

                    while(Reciever.length()>sendSMS.getMax_characters_per_sms()){
                        System.out.println("\t\t -- Invalid Message -- ");
                        System.out.print("\tContent: \t");
                        msg = sc.nextLine();
                    }
                    System.out.print("\tSECRETE KEY: \t");
                    String SECRETE_KEY = sc.nextLine();  
                    
                    sendSMS.Add(new SMSList(Subject,Sender,Reciever,msg,SECRETE_KEY,Type));
                }
                else if(option==4){
                    sendSMS.ListSMS();
                    System.out.print("\n\tInput index to remove: \t");
                    int index = sc.nextInt(); 

                    if(index>sendSMS.SizeOfArray() || index<0){
                        System.out.println("\t***No SMS by this index");
                    }
                    else{
                        sendSMS.Remove(sendSMS.FindSMSByIndex(-1));
                        System.out.println("\t***Remove successful!!!");
                    }
                }
                else if(option==5) {
                    System.exit(1);
                    break;
                }
            }
        }
    }
}
class Users{
    HashMap<String, String> User = new HashMap<String, String>();
    final String  SECRETE_KEY="lkjasfjkhiasjfdahfweaoiruroiwreqi";
    public boolean Login(String UserName,String Password){
        AES AES =new AES();
        AES.setSECRET_KEY(SECRETE_KEY);
        for(var i:  User.keySet()){
            if(i.equals(UserName) && AES.decrypt(User.get(i)).equals(Password)){
                return true;
            }
        }
        return false;
    }

    public boolean isUnique(String UserName){
        for(var i:  User.keySet()){
            if(i.equalsIgnoreCase(UserName)){
                return false;
            }
        }
        return true;
    }
    public Users(){
        Reader();
    }
    public void Reader(){
        try {
            File myObj = new File("User.txt");
            Scanner myReader = new Scanner(myObj);
            String[] DATA=new String[2];
            int k=0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(!data.equalsIgnoreCase("end") ){
                    DATA[k]=data;
                    k++;
                }else {
                    User.put(DATA[0], DATA[1]);
                    k=0;
                }
            }
            myReader.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("\tAn error occurred.");
            e.printStackTrace();
        }
    }
    
    public void Save(){
        String TempText="";
        for(String i: User.keySet()){
            TempText+=i+"\n";
            TempText+=User.get(i)+"\n";
            TempText+="End\n";
        }
        try {
            FileWriter newFile = new FileWriter("User.txt");
            newFile.write(TempText);
            newFile.close();
        } catch (IOException e) {
            System.out.println("\tAn error occurred.");
            e.printStackTrace();
        }
    }
    public boolean Register(String UserName,String Password){
        AES AES =new AES();
        AES.setSECRET_KEY(SECRETE_KEY);
        if(isUnique(UserName)){
            User.put(UserName, AES.encrypt(Password));
            Save();
            return true;
        }
        return false;
    }
    public boolean ChangePassword(String UserName,String OldPassword,String NewPassword){
        if(!isUnique(UserName)){
            if(Login(UserName, OldPassword)){
                User.put(UserName,NewPassword);
                return true;
            };
        }
        return false;
    }
    
    public void Listing(){
        System.out.println("\n\tIndex"+"\tUserName"+"\tPassword");
        
        if(User.size()>0){
            int k=0;
            for(String i: User.keySet()) {
                ShowEachData(i,User.get(i), k+1);
                k++;
            }
        }else {
            System.out.println("\t-- There are No Data here --");
        }
    }
    
    public void ShowEachData(String User,String Password,int index){
        System.out.println("\n\t"+index+"\t"+User+"\t"+Password);
    }

    public void RemoveByIndex(SMSList sms){
        User.remove(sms);
        Save();
    }
}

