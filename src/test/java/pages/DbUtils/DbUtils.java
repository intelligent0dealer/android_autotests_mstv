package pages.DbUtils;

import java.sql.*;

public class DbUtils {
    private static final String URL = "jdbc:postgresql://psql-cluster.us.testing.motorsport.tv:5433/motorsport_user";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "GfhjkmGjEvjkxfyb.123qwe";


    public static String getUserConfirmationToken(String email) {
        Connection connection = null;
        String token = null;

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                token = resultSet.getString("email_confirmation_token");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return token;
    }

    public static void deleteSubscribeRoku(String email) {
        Connection connection = null;
        String URL_CONTENT = "jdbc:postgresql://psql-cluster.us.testing.motorsport.tv:5433/motorsport_subscription";

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String SQL = "SELECT id FROM users WHERE email like '%" + email + "%'";
            ResultSet resultSet;
            resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                int idUser = resultSet.getInt("id");

                connection = DriverManager.getConnection(URL_CONTENT, USERNAME, PASSWORD);
                PreparedStatement deleteFromTransactionStatement = connection.prepareStatement("DELETE FROM transactions WHERE user_id = ?");
                deleteFromTransactionStatement.setInt(1, idUser);
                deleteFromTransactionStatement.executeUpdate();

                PreparedStatement deleteReceipt = connection.prepareStatement("DELETE FROM provider_receipts WHERE subscription_id in (SELECT id from subscriptions WHERE user_id = ?) ");
                deleteReceipt.setInt(1, idUser);
                deleteReceipt.executeUpdate();

                PreparedStatement deleteSnapshotStmt = connection.prepareStatement("DELETE FROM subscription_snapshot WHERE subscription_id in (SELECT id from subscriptions WHERE user_id = ?) ");
                deleteSnapshotStmt.setInt(1, idUser);
                deleteSnapshotStmt.executeUpdate();

                PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM subscriptions WHERE user_id = ? ");
                deleteStatement.setInt(1, idUser);
                deleteStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteSubscribe(String email) {
        Connection connection = null;
        String URL_CONTENT = "jdbc:postgresql://psql-cluster.us.testing.motorsport.tv:5433/motorsport_subscription";

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String SQL = "SELECT id FROM users WHERE email like '%" + email + "%'";
            ResultSet resultSet;
            resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                int idUser = resultSet.getInt("id");

                connection = DriverManager.getConnection(URL_CONTENT, USERNAME, PASSWORD);
                PreparedStatement deleteFromTransactionStatement = connection.prepareStatement("DELETE FROM transactions WHERE user_id = ?");
                deleteFromTransactionStatement.setInt(1, idUser);
                deleteFromTransactionStatement.executeUpdate();

                PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM subscriptions WHERE user_id = ? ");
                deleteStatement.setInt(1, idUser);
                deleteStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteUser(String email) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE email = ?");
            statement.setString(1, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}