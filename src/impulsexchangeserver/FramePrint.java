package impulsexchangeserver;

import impulsexchangeserver.common.CurrentDepartment;
import impulsexchangeserver.entities.ParentEntity;
import impulsexchangeserver.mysql.SwndOrdersUpdater;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

public class FramePrint extends JFrame {

    public FramePrint(FrameMain mainFrame, List<CurrentDepartment> doPrintList) {
        this.doPrintList = doPrintList;
        this.mainFrame = mainFrame;

        this.removeOrdersList = new ArrayList<>();
        this.parentEntityList = new ArrayList<>();
        initComponents();
        setLocationRelativeTo(null);
        mainFrame.setEnabled(false);
    }

    private void initComponents() {
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                mainFrame.setEnabled(true);
            }
        });
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("Подтверждение обмена");
        this.setResizable(false);
        this.setLayout(null);

        globalPanel = new JPanel();
        globalPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, 10));
        globalPanel.setLayout(null);
        this.add(globalPanel);

        yGlobal = 0;
        parentEntityList.clear();
        //инциализация элементов parentBox и childBox
        for (int i = 0; i < doPrintList.size(); i++) {
            initializeDepartmentPanel(i);
        }
        //инициализация остальных элементов: scrollPane, exitBtn, completeBtn
        initOtherComponents(yGlobal);
        this.add(exitBtn);
        this.add(completeBtn);
        this.repaint();
        mainFrame.setEnabled(false);
    }

    private void initializeDepartmentPanel(int i) {
        JPanel localPanel = new JPanel();
        localPanel.setLayout(null);
        localPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        int yLocal = ELEMENT_PADDING;

        //====================добавляем элемент parentBox====================
        JCheckBox parentBox = new JCheckBox(doPrintList.get(i).getDepartmentName());
        parentBox.setFocusPainted(false);
        parentBox.setSize(85, ELEMENT_HEIGHT);
        parentBox.setLocation(5, yLocal);
        parentBox.setActionCommand(String.valueOf(i));
        parentBox.addActionListener(this::parentBoxActionPerformed);
        yLocal += ELEMENT_HEIGHT + ELEMENT_PADDING;
        localPanel.add(parentBox);

        ParentEntity parentEntity = new ParentEntity();
        parentEntity.setParentBox(parentBox);
        //====================добавляем элементы childBox====================
        for (int j = 0; j < doPrintList.get(i).getOrdersList().size(); j++) {
            JCheckBox childBox = new JCheckBox(doPrintList.get(i).getDepartmentName() + "/" + doPrintList.get(i).getOrdersList().get(j));
            childBox.setFocusPainted(false);
            childBox.setSize(260, ELEMENT_HEIGHT);
            childBox.setLocation(25, yLocal);
            yLocal += ELEMENT_HEIGHT + ELEMENT_PADDING;
            localPanel.add(childBox);
            parentEntity.getChildBoxList().add(childBox);
        }
        parentEntityList.add(parentEntity);
        //===================================================================

        localPanel.setSize(WINDOW_WIDTH, yLocal);
        localPanel.setLocation(0, yGlobal);
        yGlobal += yLocal + ELEMENT_PADDING;
        globalPanel.add(localPanel);
    }

    private void initOtherComponents(int yGlobal) {
        exitBtn = new JButton("Отмена");
        exitBtn.setFocusPainted(false);
        exitBtn.setFont(new Font("Times New Roman", 0, 14));
        exitBtn.setSize(115, ELEMENT_HEIGHT);
        exitBtn.addActionListener(this::exitBtnActionPerformed);

        completeBtn = new JButton("Завершить обмен");
        completeBtn.setFocusPainted(false);
        completeBtn.setFont(new Font("Times New Roman", 1, 14));
        completeBtn.setSize(155, ELEMENT_HEIGHT);
        completeBtn.addActionListener(this::completeBtnActionPerformed);

        if (yGlobal > WINDOW_MAX_HEIGHT) {
            scrollPane = new JScrollPane(globalPanel);
            scrollPane.setVerticalScrollBar(new JScrollBar() {
                @Override
                public int getUnitIncrement(int direction) {
                    return 25;
                }
            });
            this.add(scrollPane, null);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            globalPanel.setSize(new Dimension(WINDOW_WIDTH, yGlobal - ELEMENT_PADDING));
            globalPanel.setPreferredSize(new Dimension(globalPanel.getWidth(), globalPanel.getHeight()));
            scrollPane.setSize(globalPanel.getWidth() + 19, WINDOW_MAX_HEIGHT);
            this.setSize(scrollPane.getWidth() + 16, scrollPane.getHeight() + 60);

            exitBtn.setLocation(BUTTONS_PADDING, WINDOW_MAX_HEIGHT + ELEMENT_PADDING);
            completeBtn.setLocation(BUTTONS_PADDING * 2 + exitBtn.getWidth(), WINDOW_MAX_HEIGHT + ELEMENT_PADDING);
        } else {
            if (yGlobal == 0) {
                yGlobal = ELEMENT_PADDING;
            }
            globalPanel.setSize(new Dimension(WINDOW_WIDTH, yGlobal - ELEMENT_PADDING));
            this.setSize(globalPanel.getWidth() + 16, globalPanel.getHeight() + 60);
            exitBtn.setLocation(BUTTONS_PADDING, yGlobal);
            completeBtn.setLocation(BUTTONS_PADDING * 2 + exitBtn.getWidth(), yGlobal);
        }
    }

    private void completeBtnActionPerformed(ActionEvent evt) {
        checkSelectedItems();
        if (!removeOrdersList.isEmpty()) {
            SwndOrdersUpdater updater = new SwndOrdersUpdater(removeOrdersList);
            boolean result = updater.run();
            if (result) {
                printListModify();
            }
        }
        mainFrame.endNotification(); //Убираем оповещение о новых заказах
        mainFrame.setEnabled(true);
        this.dispose();
    }

    private void checkSelectedItems() {
        for (ParentEntity parent : parentEntityList) {
            for (JCheckBox child : parent.getChildBoxList()) {
                if (child.isSelected()) {
                    removeOrdersList.add(child.getText());
                }
            }
        }
    }

    private void printListModify() {
        //сюда добавляем отделы целиком, в которых не осталось активных заказов
        List<CurrentDepartment> removalDepList = new ArrayList<>();
        for (int i = 0; i < doPrintList.size(); i++) {
            CurrentDepartment currentDep = doPrintList.get(i);
            //сюда добавляем заказы, помеченные галочками
            List<String> removalOrdersList = new ArrayList<>();
            for (String printOrder : currentDep.getOrdersList()) {
                String fullPrintOrderName = currentDep.getDepartmentName() + "/" + printOrder;

                for (String removeOrder : removeOrdersList) {
                    if (fullPrintOrderName.equals(removeOrder)) {
                        removalOrdersList.add(printOrder);
                    }
                }
            }

            if (removalOrdersList.size() == currentDep.getOrdersList().size()) {
                removalDepList.add(currentDep);
            } else {
                currentDep.getOrdersList().removeAll(removalOrdersList);
            }
        }
        doPrintList.removeAll(removalDepList);
    }

    /**
     * Событие вызываемое нажатием на родительский элемент parentBox.
     * Устанавливает во все дочерние элементы (childBox) значения равные
     * значению родителя.
     *
     * @param evt
     */
    private void parentBoxActionPerformed(ActionEvent evt) {
        int i = Integer.valueOf(evt.getActionCommand());
        for (JCheckBox child : parentEntityList.get(i).getChildBoxList()) {
            child.setSelected(parentEntityList.get(i).getParentBox().isSelected());
        }
    }

    private void exitBtnActionPerformed(ActionEvent evt) {
        mainFrame.setEnabled(true);
        this.dispose();
    }

    private final FrameMain mainFrame;
    private JButton completeBtn, exitBtn;
    private JScrollPane scrollPane;
    private JPanel globalPanel;
    private int yGlobal;

    // Объявление констант    
    private static final int WINDOW_MAX_HEIGHT = 600;                           //Максимальная высота окна PrintFrame (на случай если заказов будет слишком много)
    private static final int WINDOW_WIDTH = 290;                                //Стандартная ширина окна PrintFrame
    private static final int ELEMENT_HEIGHT = 23;                               //Стандартная высота элементов (JButton, JCheckBox)
    private static final int ELEMENT_PADDING = 3;                               //Стандартный отступ между элементами
    private static final int BUTTONS_PADDING = 10;

    // Объявление остальных переменных
    private final List<CurrentDepartment> doPrintList;
    private final List<ParentEntity> parentEntityList;
    private final List<String> removeOrdersList;
}
