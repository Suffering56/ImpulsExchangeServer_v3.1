package impulsexchangeserver.mysql;

import impulsexchangeserver.common.CurrentDepartment;
import impulsexchangeserver.options.Options;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class SwndOrdersChecker {

    public List<CurrentDepartment> run() {
        MySqlConnector mySqlInstance = MySqlConnector.getInstance();
        connection = mySqlInstance.connect();
        if (connection != null) {
            try {
                List<CurrentDepartment> newOrdersDepList = new ArrayList<>();
                for (String depName : Options.getDepartmentsList()) {
                    CurrentDepartment currentDep = checkDepartment(depName);
                    newOrdersDepList.add(currentDep);
                }
                return newOrdersDepList;
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Произошла ошибка при поиске новых заказов.\r\n"
                        + ex, this.getClass().getName() + ": run()", JOptionPane.ERROR_MESSAGE);
                return null;
            } finally {
                mySqlInstance.disconnect();
            }
        } else {
            return null;
        }
    }

    private CurrentDepartment checkDepartment(String depName) throws SQLException {
        PreparedStatement swndPrepStmt;
        swndPrepStmt = connection.prepareStatement("SELECT `order_name` FROM `exchange_orders` WHERE `dep_id` = ?");
        swndPrepStmt.setString(1, depName);
        ResultSet rs = swndPrepStmt.executeQuery();
        List<String> ordersList = new ArrayList<>();
        while (rs.next()) {
            ordersList.add(rs.getString("order_name"));
        }
        CurrentDepartment dep = new CurrentDepartment(depName);
        dep.setOrdersList(ordersList);
        return dep;
    }

    private Connection connection;
}
