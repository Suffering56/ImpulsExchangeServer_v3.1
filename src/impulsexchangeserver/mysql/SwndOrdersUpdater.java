package impulsexchangeserver.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Formatter;
import java.util.List;
import javax.swing.JOptionPane;

public class SwndOrdersUpdater {

    public SwndOrdersUpdater(List<String> removeOrdersList) {
        this.removeOrdersList = removeOrdersList;
    }

    public boolean run() {
        MySqlConnector mySqlInstance = MySqlConnector.getInstance();
        connection = mySqlInstance.connect();
        if (connection != null) {
            try {
                connection.setAutoCommit(false);
                PreparedStatement swndPrepStmt = connection.prepareStatement(
                        "DELETE FROM `exchange_orders` WHERE `dep_id` = ? AND `order_name` = ?");

                connection.prepareStatement("DELETE FROM `exchange_history_last_exchange`").executeUpdate();
                PreparedStatement historyPrepStmt = connection.prepareStatement(
                        "INSERT INTO `exchange_history_last_exchange` (`dep_id`, `order_name`, `time`) VALUES (?, ?, ?)");
                for (String fullOrderName : removeOrdersList) {
                    removeOrder(swndPrepStmt, fullOrderName);
                    historyLastExchangeUpdate(historyPrepStmt, fullOrderName);
                }
                connection.commit();
                return true;
            } catch (SQLException ex) {
                showSqlExceptionDlg(ex);
                rollback();
                return false;
            } finally {
                setTrueAutoCommit();
                mySqlInstance.disconnect();
            }
        } else {
            return false;
        }
    }

    private void removeOrder(PreparedStatement swndPrepStmt, String fullOrderName) throws SQLException {
        String[] tmp = fullOrderName.split("/");
        String depName = tmp[0];
        String orderName = tmp[1];

        swndPrepStmt.clearParameters();
        swndPrepStmt.setString(1, depName);
        swndPrepStmt.setString(2, orderName);
        int result = swndPrepStmt.executeUpdate();
        if (result == 0) {
            JOptionPane.showMessageDialog(null, "Произошла ошибка при обработке заказа <" + fullOrderName + ">.\r\n"
                    + "Пожалуйста загрузите данные и проверьте, был ли он удален.",
                    this.getClass().getName() + " :removeOrder()", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Выполняет запросы на обновление таблицы exchange_history_last_exchange,
     * содержащей список заказов, принятых сервером во время последнего обмена.
     *
     * @throws SQLException
     */
    private void historyLastExchangeUpdate(PreparedStatement historyPrepStmt, String fullOrderName) throws SQLException {
        String[] tmp = fullOrderName.split("/");
        String depName = tmp[0];
        String orderName = tmp[1];

        historyPrepStmt.clearParameters();
        historyPrepStmt.setString(1, depName);
        historyPrepStmt.setString(2, orderName);
        historyPrepStmt.setString(3, getCurrentTimeString());
        historyPrepStmt.executeUpdate();
    }

    private void rollback() {
        try {
            connection.rollback();
        } catch (SQLException exx) {
            JOptionPane.showMessageDialog(null, "Произошла ошибка при откате изменений в MySQL. \r\n"
                    + "ex: " + exx, this.getClass().getName() + " :connection.rollback()", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setTrueAutoCommit() {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Произошла ошибка при установлении параметра AutoCommit(true). \r\n"
                    + "ex: " + ex, this.getClass().getName() + " : AutoCommit(true)", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showSqlExceptionDlg(SQLException ex) {
        JOptionPane.showMessageDialog(null, "Произошла ошибка при обновлении отмеченных заказов на сервере.\r\n"
                + "Пожалуйста загрузите данные и проверьте, все ли корректно сохранилось.\r\n"
                + "ex: " + ex, this.getClass().getName() + " : run()", JOptionPane.ERROR_MESSAGE);
    }

    private String getCurrentTimeString() {
        Calendar calendar = Calendar.getInstance();
        String currentTimeString = new Formatter().format("%1$td-%1$tm-%1$tY %1$tH:%1$tM:%1$tS", calendar).toString();
        return currentTimeString;
    }

    private Connection connection;
    private final List<String> removeOrdersList;
}
