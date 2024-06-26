import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import controller.DAO;

class DAOTest {

	@Test
	public void testDelete() {
		DAO dao = new DAO();
		try {
			dao.delete("1");
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		assertEquals("1", getdel_flag("1"));
	}
	
	
	@Test
	public void testInsert() {
		DAO dao = new DAO();
		try {
			dao.insert("資本市場部", "小早川かんな", "Assistant", "a");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(13, getDataSize());
	}
	
	
	private int getDataSize() {
		DAO dao = new DAO();
		try {
			dao.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String url = "jdbc:mysql://localhost:3306/humanresource";
		String user = "root";
		String password = "root";
		int result = 0;
		String sql = "SELECT COUNT(*) FROM humans";
		try (Connection connection = DriverManager.getConnection(url, user, password);
		        PreparedStatement statement = connection.prepareStatement(sql)) {
		        ResultSet results = statement.executeQuery();
		        if (results.next()) {
		             result = results.getInt(1); 
		         }

		    } catch(SQLException e) {
		        e.printStackTrace();
		    }

		    return result; 
		}
	

	private Object getdel_flag(String id) {
		DAO dao = new DAO();
		try {
			dao.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String url = "jdbc:mysql://localhost:3306/humanresource";
		String user = "root";
		String password = "root";
		String result = null;
		String sql = "SELECT del_flag FROM humans WHERE id = 1";
		try (Connection connection = DriverManager.getConnection(url, user, password);
		        PreparedStatement statement = connection.prepareStatement(sql)) {
		        ResultSet results = statement.executeQuery();

		        // 結果セットからデータを取得
		        if (results.next()) {
		            result = results.getString("del_flag");
		        }
		    } catch(SQLException e) {
		        e.printStackTrace();
		    }

		    return result; 
		}
}
