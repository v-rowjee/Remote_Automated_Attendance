package Components;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AASTable extends JTable {
    public AASTable(DefaultTableModel defaultTableModel){
        super(defaultTableModel);
        setShowGrid(false);
        setShowVerticalLines(false);
        setAutoCreateRowSorter(true);
        setBorder(null);
        setAllCellCenter();
        setFillsViewportHeight(true);
        setRowHeight(40);
        getTableHeader().setPreferredSize(new Dimension(autoResizeMode,40));
        getTableHeader().setBorder(null);

    }

    private void setAllCellCenter() {
        ((DefaultTableCellRenderer)getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        setDefaultRenderer(String.class, centerRenderer);
    }
}
