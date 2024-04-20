package utils.dataReaders;

public class UserData{
    private String userName;
    private String password;
    private String col3;
    public UserData(String userName, String password,String col3) {
        this.userName = userName;
        this.password = password;
        this.col3 = col3;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
    public String getCol3() {
        return col3;
    }

}
