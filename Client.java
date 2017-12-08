package marlon.com.libiki;

/**
 * Created by marlon on 04/12/17.
 */

public class Client {
    private String name;
    private String num;

    public Client(String name, String num) {
       setName(name);
       setNum(num);
    }

    public Client() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(!name.isEmpty()){
        this.name = name;}
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        if(!num.isEmpty()){
        this.num = num;}
    }
}
