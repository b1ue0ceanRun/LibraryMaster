public class Book {
    private String name;          //书名
    private String author;        //作者
    private boolean condition;     //状态（是否被借出）
    private String id;            //图书编号


    public Book(String name, String author, boolean condition, String id) {
        this.name = name;
        this.author = author;
        this.condition = condition;
        this.id = id;

    }

    public Book() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isCondition() {
        return condition;
    }

    public void setCondition(boolean condition) {
        this.condition = condition;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return
                "   书名：" + name +
                        "   图书编号: " + id  +
                        "   作者: " + author +

                        "   是否出借: " + condition

                ;
    }
}
