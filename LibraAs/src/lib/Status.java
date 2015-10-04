package lib;

/**
 * Created by panda on 02/10/2015.
 */
public class Status implements java.io.Serializable{
    private int Id;
    private int IdUser;
    private String Content;

    public Status(int id, int idUser, String content) {
        Id = id;
        IdUser = idUser;
        Content = content;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int idUser) {
        IdUser = idUser;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
