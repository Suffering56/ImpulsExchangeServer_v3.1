package impulsexchangeserver;

import impulsexchangeserver.common.FlashingTray;
import impulsexchangeserver.menu.*;
import impulsexchangeserver.mysql.SwndOrdersChecker;
import impulsexchangeserver.common.CurrentDepartment;
import impulsexchangeserver.mysql.SwndDownloader;
import impulsexchangeserver.options.FrameOptionsCommon;
import impulsexchangeserver.options.FrameOptionsMySQL;
import impulsexchangeserver.options.Options;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JToggleButton;
import javax.swing.Timer;

public class FrameMain extends javax.swing.JFrame {

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        exchangePanel = new javax.swing.JPanel();
        mainDownloadBtn = new javax.swing.JButton();
        doPrintBtn = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        commonOptionsCallBtn = new javax.swing.JMenuItem();
        mySqlOptionsCallBtn = new javax.swing.JMenuItem();
        exitCallBtn = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        monitorMainCallBtn = new javax.swing.JMenuItem();
        monitorAsssemblersCallBtn = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        last25CallBtn = new javax.swing.JMenuItem();
        searchCallBtn = new javax.swing.JMenuItem();
        lastExchangeCallBtn = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Impuls Exchange Server");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        exchangePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout exchangePanelLayout = new javax.swing.GroupLayout(exchangePanel);
        exchangePanel.setLayout(exchangePanelLayout);
        exchangePanelLayout.setHorizontalGroup(
            exchangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        exchangePanelLayout.setVerticalGroup(
            exchangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 238, Short.MAX_VALUE)
        );

        mainDownloadBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mainDownloadBtn.setText("Загрузить данные");
        mainDownloadBtn.setFocusPainted(false);
        mainDownloadBtn.setMaximumSize(new java.awt.Dimension(161, 23));
        mainDownloadBtn.setMinimumSize(new java.awt.Dimension(161, 23));
        mainDownloadBtn.setPreferredSize(new java.awt.Dimension(170, 23));
        mainDownloadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainDownloadBtnActionPerformed(evt);
            }
        });

        doPrintBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        doPrintBtn.setText("На печать");
        doPrintBtn.setFocusPainted(false);
        doPrintBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doPrintBtnActionPerformed(evt);
            }
        });

        jMenu1.setText("Меню");

        commonOptionsCallBtn.setText("Общие настройки");
        commonOptionsCallBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commonOptionsCallBtnActionPerformed(evt);
            }
        });
        jMenu1.add(commonOptionsCallBtn);

        mySqlOptionsCallBtn.setText("Настройки MySQL");
        mySqlOptionsCallBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mySqlOptionsCallBtnActionPerformed(evt);
            }
        });
        jMenu1.add(mySqlOptionsCallBtn);

        exitCallBtn.setText("Выход");
        exitCallBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitCallBtnActionPerformed(evt);
            }
        });
        jMenu1.add(exitCallBtn);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Отгрузка");

        monitorMainCallBtn.setText("Заказы");
        monitorMainCallBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monitorMainCallBtnActionPerformed(evt);
            }
        });
        jMenu3.add(monitorMainCallBtn);

        monitorAsssemblersCallBtn.setText("Сборщики");
        monitorAsssemblersCallBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monitorAsssemblersCallBtnActionPerformed(evt);
            }
        });
        jMenu3.add(monitorAsssemblersCallBtn);

        jMenuBar1.add(jMenu3);

        jMenu2.setText("Архив");

        last25CallBtn.setText("Последние 25 заказов");
        last25CallBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                last25CallBtnActionPerformed(evt);
            }
        });
        jMenu2.add(last25CallBtn);

        searchCallBtn.setText("Поиск по всем заказам");
        searchCallBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCallBtnActionPerformed(evt);
            }
        });
        jMenu2.add(searchCallBtn);

        lastExchangeCallBtn.setText("Последний обмен (сервер)");
        lastExchangeCallBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastExchangeCallBtnActionPerformed(evt);
            }
        });
        jMenu2.add(lastExchangeCallBtn);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(exchangePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainDownloadBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(doPrintBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(exchangePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(doPrintBtn)
                    .addComponent(mainDownloadBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {doPrintBtn, mainDownloadBtn});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public FrameMain() {
        departmentsList = new ArrayList<>();
        newOrdersList = new ArrayList<>();
        printList = new ArrayList<>();
        departmentsList.addAll(Options.getDepartmentsList());

        initComponents();
        int count = Options.getDepartmentsList().size();
        initDynamicComponents(count);
        trayInit();
        setSize(this.getWidth(), (26 + 7) * count + 20 + 65 + 7);
        setLocationRelativeTo(null);
    }

    private void trayInit() {
        flashingTray = new FlashingTray(this);
        createUpdateCheckTimer();
        createFlashingTrayTimer();
        updateCheckTimer.start();
    }

    public void startNotification() {
        updateCheckTimer.stop();
        flashingTray.showTray();
        flashingTrayTimer.start();
    }

    public void endNotification() {
        flashingTrayTimer.stop();
        flashingTray.hideTray();
        updateCheckTimer.start();
    }

    private void createUpdateCheckTimer() {
        updateCheckTimer = new Timer(30 * 60 * 1000, (ActionEvent e) -> {   //60*1000 = минута
            mainDownloadBtn.doClick();
        });
    }

    private void createFlashingTrayTimer() {
        flashingTrayTimer = new Timer(500, (ActionEvent e) -> {
            switcher = !switcher;
            if (switcher) {
                flashingTray.getTray().setImage(flashingTray.getEmptyIcon());
            } else {
                flashingTray.getTray().setImage(flashingTray.getNormalIcon());
            }
        });
    }

    private void initDynamicComponents(int count) {
        //устанавливаем компоновку (rows, cols, отступ, отступ)
        exchangePanel.setLayout(new GridLayout(0, 3, 7, 7));
        //инициализируем массивы динамических компонентов
        progressBarArray = new JProgressBar[count];
        departmentLabelArray = new JLabel[count];
        doExchangeBtnArray = new JToggleButton[count];

        //инициализируем сами копоненты
        for (int i = 0; i < count; i++) {
            initializeRow(i);
        }
    }

    private void initializeRow(int i) {
        //инициализация DepartmentLabel (номер отдела)
        departmentLabelArray[i] = new JLabel("Отдел №" + departmentsList.get(i));
        departmentLabelArray[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exchangePanel.add(departmentLabelArray[i]);

        //инициализация ProgressBar
        progressBarArray[i] = new JProgressBar();
        progressBarArray[i].setStringPainted(true);
        exchangePanel.add(progressBarArray[i]);

        //инициализация кнопок "на обмен"
        doExchangeBtnArray[i] = new JToggleButton("На обмен");
        doExchangeBtnArray[i].setActionCommand(String.valueOf(i));                          //передаем в качестве параметра индекс отдела
        doExchangeBtnArray[i].addActionListener(this::doExchangeBtnActionPerformed);
        doExchangeBtnArray[i].setFocusPainted(false);
        doExchangeBtnArray[i].setEnabled(false);
        exchangePanel.add(doExchangeBtnArray[i]);
    }

    private void doExchangeBtnActionPerformed(ActionEvent evt) {
        int i = Integer.valueOf(evt.getActionCommand());
        doExchangeBtnArray[i].setSelected(!doExchangeBtnArray[i].isSelected());

        setLoadingState();
        SwndDownloader downloader = new SwndDownloader(departmentsList.get(i));
        boolean result = downloader.run();
        setNormalState();
        if (result) {
            if (!doExchangeBtnArray[i].isSelected()) {                          //Если - это первое нажатие на кнопку
                printList.add(newOrdersList.get(i));                            //добавляем заказы в printList
                doExchangeBtnArray[i].setSelected(true);                        //и зажимаем кнопку
            }
        } else {
            doExchangeBtnArray[i].setSelected(false);
        }
    }

    private void mainDownloadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainDownloadBtnActionPerformed
        printList.clear();

        setLoadingState();
        SwndOrdersChecker checker = new SwndOrdersChecker();
        newOrdersList = checker.run();
        setNormalState();

        if (newOrdersList != null) {
            updateInterface();
        }
    }//GEN-LAST:event_mainDownloadBtnActionPerformed

    private void updateInterface() {
        boolean globalUpdateStatus = false;
        for (int i = 0; i < progressBarArray.length; i++) {
            boolean isUpdate = false;
            if (!newOrdersList.get(i).getOrdersList().isEmpty()) {
                isUpdate = true;
                globalUpdateStatus = true;
            }
            if (isUpdate) {
                progressBarArray[i].setValue(100);
                progressBarArray[i].setString("Новые заказы");
                doExchangeBtnArray[i].setEnabled(true);
            } else {
                progressBarArray[i].setValue(0);
                progressBarArray[i].setString("Нет новых данных");
                doExchangeBtnArray[i].setEnabled(false);

            }
            doExchangeBtnArray[i].setSelected(false);
        }
        if (globalUpdateStatus) {
            startNotification();
        }
    }

    private void setLoadingState() {
        this.setEnabled(false);
        this.setTitle("Загрузка данных. Подождите...");
    }

    private void setNormalState() {
        this.setEnabled(true);
        this.setTitle("Impuls Exchange Server");
        this.setVisible(true);
    }

    private void doPrintBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doPrintBtnActionPerformed
        new FramePrint(this, printList).setVisible(true);
    }//GEN-LAST:event_doPrintBtnActionPerformed

    private void searchCallBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCallBtnActionPerformed
        new FrameHistorySearch(this).setVisible(true);
    }//GEN-LAST:event_searchCallBtnActionPerformed

    private void last25CallBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_last25CallBtnActionPerformed
        new FrameHistoryLast25(this).setVisible(true);
    }//GEN-LAST:event_last25CallBtnActionPerformed

    private void monitorMainCallBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monitorMainCallBtnActionPerformed
        new FrameMonitorOrders(this).setVisible(true);
    }//GEN-LAST:event_monitorMainCallBtnActionPerformed

    private void monitorAsssemblersCallBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monitorAsssemblersCallBtnActionPerformed
        new FrameMonitorAssemblers(this).setVisible(true);
    }//GEN-LAST:event_monitorAsssemblersCallBtnActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        flashingTray.showTray();
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosing

    private void exitCallBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitCallBtnActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitCallBtnActionPerformed

    private void mySqlOptionsCallBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mySqlOptionsCallBtnActionPerformed
        new FrameOptionsMySQL(this).setVisible(true);
    }//GEN-LAST:event_mySqlOptionsCallBtnActionPerformed

    private void commonOptionsCallBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commonOptionsCallBtnActionPerformed
        new FrameOptionsCommon(this).setVisible(true);
    }//GEN-LAST:event_commonOptionsCallBtnActionPerformed

    private void lastExchangeCallBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastExchangeCallBtnActionPerformed
        new FrameHistoryLastExchange(this).setVisible(true);
    }//GEN-LAST:event_lastExchangeCallBtnActionPerformed

    private final List<String> departmentsList;
    private List<CurrentDepartment> newOrdersList;
    private final List<CurrentDepartment> printList;

    private JProgressBar[] progressBarArray;
    private JLabel[] departmentLabelArray;
    private JToggleButton[] doExchangeBtnArray;

    private FlashingTray flashingTray;
    private boolean switcher = false;
    public Timer updateCheckTimer;
    public Timer flashingTrayTimer;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem commonOptionsCallBtn;
    private javax.swing.JButton doPrintBtn;
    private javax.swing.JPanel exchangePanel;
    private javax.swing.JMenuItem exitCallBtn;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem last25CallBtn;
    private javax.swing.JMenuItem lastExchangeCallBtn;
    private javax.swing.JButton mainDownloadBtn;
    private javax.swing.JMenuItem monitorAsssemblersCallBtn;
    private javax.swing.JMenuItem monitorMainCallBtn;
    private javax.swing.JMenuItem mySqlOptionsCallBtn;
    private javax.swing.JMenuItem searchCallBtn;
    // End of variables declaration//GEN-END:variables
}
