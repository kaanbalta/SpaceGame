package com.anaekran;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import com.database.Database;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Records extends JFrame {
    private Database database = new Database();
    private JPanel panel;
    private JTable table;
    private JScrollPane scrollPane;

    public Records() {
        add(panel);
        setSize(600,400);
        setResizable(false);

        DefaultTableModel model = new DefaultTableModel(new Object[]{"Kullanıcı", "Puan"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("Serif", Font.BOLD, 14));
        tableHeader.setForeground(Color.red);
        table.setRowHeight(20);

        table.setModel(model);
        scrollPane.setViewportView(table);

        LinkedHashMap<String,Integer> kullanıcılar = database.kullaniciPuanlariniGetir();

        for(Map.Entry<String,Integer> entry : kullanıcılar.entrySet()){
            model.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }




    }
}
