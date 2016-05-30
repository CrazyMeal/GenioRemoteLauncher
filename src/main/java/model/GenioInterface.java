package model;

/**
 * Created by KVN on 28/05/2016.
 */
public class GenioInterface {
    private int id;
    private String name;
    private String version;

    public GenioInterface(){
        this.id = -1;
        this.name = "Default";
        this.version = "G0R0C0";
    }

    public GenioInterface(int id, String name, String version){
        this.id = id;
        this.name = name;
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
