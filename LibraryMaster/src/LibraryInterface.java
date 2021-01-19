import java.sql.SQLException;
import java.util.List;

//包含了所有要用到的方法

public interface LibraryInterface {
    public abstract void addBook(Book book);

    public abstract void deleteBook(String book_id);

    public abstract void SearchBookByName(String book_name);

    public abstract void SearchBookByAuthor(String author);

    //还未添加事务管理
    public abstract void BorrowBook(String book_id, String student_id , String book_name) throws SQLException;

    public abstract void ReturnBook(String book_id, String student_id , String book_name) throws SQLException;

    public abstract List<Book> find_allBook();
}
