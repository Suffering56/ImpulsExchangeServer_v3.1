package impulsexchangeserver.mysql;

import impulsexchangeserver.common.Service;
import impulsexchangeserver.options.Options;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class SwndDownloader {

    public SwndDownloader(String depName) {
        this.depName = depName;
    }

    public boolean run() {
        MySqlConnector mySqlInstance = MySqlConnector.getInstance();
        connection = mySqlInstance.connect();
        if (connection != null) {
            try {
                return extractSwndFile();
            } catch (SQLException ex) {
                showException(ex.toString(), "", "run");
                return false;
            } finally {
                mySqlInstance.disconnect();
            }
        } else {
            return false;
        }
    }

    private boolean extractSwndFile() throws SQLException {
        PreparedStatement swndPrepStmt;
        swndPrepStmt = connection.prepareStatement("SELECT `swnd` FROM `exchange_swnd` WHERE `dep_id` = ?");
        swndPrepStmt.setString(1, depName);
        ResultSet rs = swndPrepStmt.executeQuery();
        int counter = 0;
        while (rs.next()) {
            counter++;
            streamProcessing(rs.getBinaryStream("swnd"));
        }
        if (counter == 0) {
            showException("", "Файл обмена не найден в базе данных (т.е не был выгружен дилером)", "extractSwndFile");
            return false;
        }
        return true;
    }

    private boolean streamProcessing(InputStream in) {
        FileOutputStream out = null;
        try {
            File swndFile = new File(Options.getExchangePlacePath() + File.separator + Options.getSwndFileName());
            out = new FileOutputStream(swndFile);
            byte[] buffer = new byte[1];
            while (in.read(buffer) > 0) {
                out.write(buffer);
            }
            return true;
        } catch (IOException ex) {
            String errorMsg = "";
            if (ex.toString().contains("Отказано в доступе")) {
                errorMsg = "Недостаточно прав доступа. Пожалуйста измените путь к месту обмена, либо настройте права.";
            }
            showException(ex.toString(), errorMsg, "streamProcessing");
            return false;
        } finally {
            Service.streamClose(out);
            Service.streamClose(in);
        }
    }

    private void showException(String ex, String errorMsg, String methodName) {
        if (!ex.equals("")) {
            ex = "ex: " + ex;
        }
        if (!errorMsg.equals("")) {
            errorMsg = errorMsg + "\r\n";
        }
        JOptionPane.showMessageDialog(null, "Произошла ошибка при загрузке файла обмена <" + Options.getSwndFileName() + ">\r\n"
                + errorMsg + ex, this.getClass().getName() + ": " + methodName + "()", JOptionPane.ERROR_MESSAGE);
    }

    private Connection connection;
    private final String depName;

}
