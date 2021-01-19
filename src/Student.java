public class Student {
    private String id;
    private String name;
    private String password;

    public Student(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "学生信息 ——" +
                "  学号'" + id + '\'' +
                "  姓名'" + name + '\'' +
                "  密码'" + password + '\''
                ;
    }
}
