package com.xadmin.feestype.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.feestype.bean.Type;

public class TypeDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/esbuser?serverTimezone=UTC&useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "oladunjoye99";
	private String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	
	private static final String INSERT_TYP_SQL = "INSERT INTO ibt_fee_types" + "  (fee_type_id, fee_type_desc) VALUES "
			+ " (?, ?);";

	private static final String SELECT_TYP_BY_ID = "select fee_type_id, fee_type_desc from ibt_fee_types where fee_type_id =?";
	private static final String SELECT_ALL_TYP = "select * from ibt_fee_types";
	private static final String DELETE_TYP_SQL = "delete from ibt_fee_types where fee_type_id =?;";
	private static final String UPDATE_TYP_SQL = "update ibt_fee_types set fee_type_desc = ? where fee_type_id = ?;";
	public TypeDao() {
	
	}
	
	protected Connection getConnection()  {
		Connection connection = null;
		try {
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public void insertType(Type type)throws SQLException {
		System.out.println(INSERT_TYP_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TYP_SQL)) {
			preparedStatement.setString(1, type.getFee_type_id());
			preparedStatement.setString(2, type.getFee_type_desc());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
}
	public Type selectType(String feeTypeId){
		 Type type = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TYP_BY_ID);) {
			preparedStatement.setString(1, feeTypeId);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String feeTypeDesc = rs.getString("fee_type_desc");
				type = new Type(feeTypeId, feeTypeDesc);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return type;
	}
	
public List<Type> selectAllTypes() {
		
		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Type> types = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TYP);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String feeTypeId = rs.getString("fee_type_id");
				String feeTypeDesc = rs.getString("fee_type_desc");
				types.add(new Type(feeTypeId, feeTypeDesc));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return types;
	}
public boolean updateType(Type type) throws SQLException {
	boolean rowUpdated;
	try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_TYP_SQL);) {
	statement.setString(1, type.getFee_type_desc());
	statement.setString(2, type.getFee_type_id());
	System.out.println("updated Type:"+statement);
	rowUpdated = statement.executeUpdate() > 0;
		
	}
	return rowUpdated;
}

public boolean deleteType(String fee_type_id) throws SQLException {
	boolean rowDeleted;
	try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_TYP_SQL);) {
		statement.setString(1, fee_type_id);
		rowDeleted = statement.executeUpdate() > 0;
	}
	return rowDeleted;
}
	private void printSQLException(SQLException e) {
		// TODO Auto-generated method stub
		
	}
	
}

	
