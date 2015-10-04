package controller;

import lib.Status;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by panda on 02/10/2015
 */
public class MainModel extends AbstractTableModel {
    private String[] title = {"Trang Chính"};
    public static int Id;
    FromServer server = new FromServer();

    List<Status> list = server.getAllFriendStatus(Id);

    public void print(){
        for (int i = 0; i < list.size(); i++) {

//            System.out.println(list.get(i).);
        }
    }

    public List<Status> getList() {
        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getIdUser());
            System.out.println(server.getFirstNameFromId(list.get(i).getIdUser()));
            System.out.println(server.getLastNameFromId(list.get(i).getIdUser()));
        }
        return list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return title.length;
    }
    @Override
    public String getColumnName(int i) {
        return title[i];
    }

    @Override
    public Object getValueAt(int row, int col) {
        int subrow = getRowCount() - row;
        Status status = list.get(row);
        switch (col) {
            case 0:
                String str =  server.getFirstNameFromId(list.get(row).getIdUser()) + " " + server.getLastNameFromId(list.get(row).getIdUser()) +" : "+status.getContent();
                            //server.getLastNameFromId(status.getId()) +" "+server.getFirstNameFromId(status.getId())+": "+status.getContent();
                System.out.println(str);
                return str;
            default:
                return null;
        }
    }

    public void refesh(){
        this.fireTableDataChanged();
    }

    public static void main(String[] args) {

        MainModel.Id = 1;
        MainModel mainModel = new MainModel();
//        mainModel.getValueAt(1,1);

        List list = mainModel.getList();

//        for(Object obj: list){
//            Status status =(Status) obj;
//            System.out.println(status.getId() + "  " + status.getIdUser() + "  " + status.getContent());
//        }

    }
}
