package utils.dataReaders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvFileReader {
//"../testData/loginData.csv"
    private static final String csv_file_path=System.getProperty("user.dir")+"\\src\\test\\java\\utils\\testData\\loginData.csv";
    public static List<UserData> getUserData(){
        List<UserData> userData = new ArrayList<>();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(csv_file_path));
            String line ;
           while ((line= bf.readLine())!=null){
               String[] data = line.split(",");
               if(data.length==3){
                   String userName= data[0].trim();
                   String password= data[1].trim();
                   String col3= data[2].trim();

                   userData.add(new UserData(userName,password,col3));
               }
           }


        } catch (FileNotFoundException e) {
            System.out.println(csv_file_path +" is not found " + e.getMessage());
        } catch (IOException e){
            System.out.println("End of file has reached" + e.getMessage());

        }
        return  userData;
    }
}
