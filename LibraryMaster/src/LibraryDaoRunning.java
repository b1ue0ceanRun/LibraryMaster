import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LibraryDaoRunning implements LibraryInterface {

    //添加图书方法
    @Override
    public void addBook(Book book) {
        Connection con = null;
        PreparedStatement psst = null;

        try {
            con = JDBCUtils.getConnection();

            String sql = "insert into books(book_id, book_name, author, `condition`)\n" +
                    "value (?,?,?,?)";
            psst = con.prepareStatement(sql);

            psst.setString(1, book.getId());
            psst.setString(2, book.getName());
            psst.setString(3, book.getAuthor());
            psst.setInt(4,0 );
            int count = psst.executeUpdate();
            if (count <= 0) {
                System.out.println("添加图书失败！");
            } else {
                System.out.println("添加图书成功！");
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.close(psst, con);
        }


    }

    //删除图书方法
    @Override
    public void deleteBook(String book_id) {
        PreparedStatement psst = null;
        Connection con = null;
        try {
            con = JDBCUtils.getConnection();
            String sql = "delete from books where book_id = ?";
            psst = con.prepareStatement(sql);
            psst.setString(1, book_id);

            int count = psst.executeUpdate();
            if (count <= 0) {
                System.out.println("删除失败！");
            } else {
                System.out.println("删除成功!");
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.close(psst, con);
        }


    }


    //查询图书方法
    @Override
    public void SearchBookByName(String book_name) {
        Connection con = null;
        PreparedStatement psst = null;
        ResultSet result = null;
        try {
            con = JDBCUtils.getConnection();
            String sql = "select * from books  where book_name = ?";  //有可能选出来好几条信息
            psst = con.prepareStatement(sql);
            psst.setString(1, book_name);
            result = psst.executeQuery();
            while (result.next()) {
                System.out.println("亲！您要查找的图书信息如下哦~~\n");
                int id = result.getInt(1);
                String name = result.getString("book_name");
                String author = result.getString("author");
                Boolean condition = result.getBoolean("condition");
                System.out.println("图书编号：" + id + "   书名：" + name + "    作者：" + author + "   出借状态：" + condition);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.close(psst, con, result);

        }
    }

    //查询图书方法 （按作者
    @Override
    public void SearchBookByAuthor(String author) {
        Connection con = null;
        PreparedStatement psst = null;
        ResultSet result = null;
        try {
            con = JDBCUtils.getConnection();
            String sql = "select * from books where author = ?";  //有可能选出来好几条信息
            psst = con.prepareStatement(sql);
            psst.setString(1, author);
            result = psst.executeQuery();
            while (result.next()) {
                System.out.println("亲！您要查找的图书信息如下哦~~\n");
                int id = result.getInt(1);
                String name = result.getString("book_name");
                String auth = result.getString("author");
                Boolean condition = result.getBoolean("condition");
                System.out.println("图书编号：" + id + "书名：" + name + "作者：" + auth + "出借状态：" + condition);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.close(psst, con, result);
        }


    }


    //借书方法！
    @Override
    public void BorrowBook(String book_id,String student_id,String book_name) throws SQLException {        //还得把condition变成0
        Connection con = null;
        PreparedStatement psst = null;
        ResultSet result = null;
        PreparedStatement psst2 = null;
        PreparedStatement psst3 = null;
        try {
            con = JDBCUtils.getConnection();
            String sql = "select * from books where book_id = ? and `condition` = 0";
            psst = con.prepareStatement(sql);
            psst.setString(1, book_id);
            result = psst.executeQuery();
            if (result.next()){
                Date date = new Date(System.currentTimeMillis());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


                psst2 = con.prepareStatement("""
                        insert into records(record_id, student_id,book_id, book_name, `check`, date) value (default ,?,?,?,?,?)""");

                psst2.setString(1,student_id);
                psst2.setString(2,book_id);
                psst2.setString(3,book_name);
                //  1 代表借书
                psst2.setInt(4,1);
                psst2.setString(5,formatter.format(date));

                psst3 = con.prepareStatement("update books\n" +
                        "                        set `condition`=1 where book_id = ?");
                psst3.setString(1,book_id);
                con.setAutoCommit(false);
                psst2.execute();
                psst3.executeUpdate();
                con.commit();
                System.out.println("借书成功！");
            }else {
                System.out.println("服务器异常！无法查询到结果该书可能已出借 不可重复借阅");

            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            con.rollback();
            System.out.println("借书失败!");

        }finally {
            JDBCUtils.close(psst,con,result);
            if(psst2!=null){
                try {
                    psst2.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
            if(psst3!=null){
                try {
                    psst3.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        }


    }


    //还书方法
    @Override
    public void ReturnBook(String book_id ,String student_id,String book_name) throws SQLException {
        Connection con = null;
        PreparedStatement psst = null;
        ResultSet result = null;
        PreparedStatement psst2 = null;
        PreparedStatement psst3 = null;
        try {
            con = JDBCUtils.getConnection();
            String sql = "select * from books where book_id = ? and `condition` = 1";
            psst = con.prepareStatement(sql);
            psst.setString(1, book_id);
            result = psst.executeQuery();
            if (result.next()){
                Date date = new Date(System.currentTimeMillis());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


                psst2 = con.prepareStatement("""
                        insert into records(record_id, student_id,book_id, book_name, `check`, date) value (default ,?,?,?,?,?)""");

                psst2.setString(1,student_id);
                psst2.setString(2,book_id);
                psst2.setString(3,book_name);
                //  1 代表借书
                psst2.setInt(4,1);
                psst2.setString(5,formatter.format(date));

                psst3 = con.prepareStatement("update books\n" +
                        "                        set `condition`= 0 where book_id = ?");
                psst3.setString(1,book_id);
                con.setAutoCommit(false);
                psst2.execute();
                psst3.executeUpdate();
                con.commit();
            }else {
                System.out.println("服务器异常！ 未查询到图书或该图书已被归还！");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            if (con != null) {
                con.rollback();
            }
            System.out.println("还书失败!");

        }finally {
            JDBCUtils.close(psst,con,result);
            if(psst2!=null){
                try {
                    psst2.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
            if(psst3!=null){
                try {
                    psst3.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        }



    }

    //查询图书方法！！！（管理员方法
    @Override
    public List<Book> find_allBook() {
        Connection con = null;
        Statement st = null;
        ResultSet result = null;
        List<Book> bookList = null;

        try {
            con = JDBCUtils.getConnection();
            String sql = "select * from books";
            st = con.createStatement();
            result = st.executeQuery(sql);
            Book book = null;
            bookList = new ArrayList<>();

            while (result.next()) {
                String id = result.getString("book_id");
                String book_name = result.getString("book_name");
                String author = result.getString("author");
                int condition = result.getInt("condition");

                book = new Book();
                book.setId(id);
                book.setAuthor(author);
                book.setName(book_name);
                book.setCondition(condition == 1);

                bookList.add(book);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.close(st, con, result);
        }
        return bookList;
    }
}
