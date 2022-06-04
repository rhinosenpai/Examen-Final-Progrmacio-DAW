package daw.programacio.thenewmisterquestion;

import daw.programacio.thenewmisterquestion.data.DBFacade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryModel {
    private int id;
    private String name;

    public CategoryModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryModel() {
    }

    public ArrayList<CategoryModel> getCategoryModel(){
        ArrayList<CategoryModel> ret = new ArrayList();
        try {
            ResultSet rs = DBFacade.select("category");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                CategoryModel ct = new CategoryModel(id,name);
                System.out.println(rs);
                ret.add(ct);
            }
            return ret;
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
