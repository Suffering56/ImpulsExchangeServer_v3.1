package impulsexchangeserver.menu;

import impulsexchangeserver.FrameMain;
import impulsexchangeserver.mysql.MySqlConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class FrameHistoryLastExchange extends javax.swing.JFrame {

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jOrdersList = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Предыдущий обмен");
        setMinimumSize(new java.awt.Dimension(207, 300));
        setResizable(false);
        setSize(new java.awt.Dimension(207, 300));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Предыдущий обмен:");
        jLabel1.setFocusable(false);

        jScrollPane1.setMinimumSize(new java.awt.Dimension(207, 300));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(207, 300));

        jOrdersList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                generalKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jOrdersList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public FrameHistoryLastExchange(FrameMain mainFrame) {
        this.mainFrame = mainFrame;
        initComponents();

        boolean success = readHistory();
        if (success) {
            mainFrame.setEnabled(false);
        }

        setLocationRelativeTo(null);
        jOrdersList.setModel(ordersList);
    }

    private boolean readHistory() {
        MySqlConnector mySqlInstance = MySqlConnector.getInstance();
        Connection connection = mySqlInstance.connect();
        try {
            ordersList.clear();
            PreparedStatement prepStmt = connection.prepareStatement("SELECT * FROM `exchange_history_last_exchange`");

            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                ordersList.addElement(rs.getInt("dep_id") + "/" + rs.getInt("order_name")
                        + "                         " + rs.getString("time"));
            }
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Произошла ошибка при чтении архива. \r\n"
                    + "ex: " + ex, this.getClass().getName() + " : readHistory()", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            mySqlInstance.disconnect();
        }
    }

    private void generalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_generalKeyPressed
        if (evt.getKeyCode() == 27) {
            mainFrame.setEnabled(true);
            this.dispose();
        }
    }//GEN-LAST:event_generalKeyPressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        mainFrame.setEnabled(true);
    }//GEN-LAST:event_formWindowClosing

    private final DefaultListModel ordersList = new DefaultListModel();
    private final FrameMain mainFrame;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jOrdersList;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
