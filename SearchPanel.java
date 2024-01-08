package gui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SearchPanel {
    protected JPanel searchPanel=new JPanel();
    private final SearchAndDisplayPanel display=new SearchAndDisplayPanel();

    protected SearchPanel() throws IOException, ClassNotFoundException {
        setSearchPanel();
    }
    protected void setSearchPanel() {
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,5));
        searchPanel.setPreferredSize(new Dimension(500,460));
        searchPanel.setBackground(Color.yellow);
        searchPanel.add(display.filtersPanel);
        searchPanel.add(display.searchAndDisplayPanel);
        searchPanel.setVisible(true);
    }
}
