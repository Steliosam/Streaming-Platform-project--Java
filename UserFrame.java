package gui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class UserFrame extends AdminFrame {
    private final JFrame userFrame = new JFrame("Authflix");
    private final JPanel mainUsersPanel = new JPanel();
    private final JButton showFavoritesButton=new JButton();

    protected UserFrame() throws IOException, ClassNotFoundException {
        setUserFrame();
        userFrame.setVisible(true);
    }
    private void setMainUserPanel() {
        mainUsersPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        mainUsersPanel.setPreferredSize(new Dimension(500, 30));
        mainUsersPanel.setBackground(Color.CYAN);
        setUsersButtons();
        mainUsersPanel.add(searchContentButton);
        mainUsersPanel.add(showFavoritesButton);
        mainUsersPanel.add(logOutButton);

    }
    private void setUsersButtons(){
        searchContentButton.setPreferredSize(new Dimension(190,30));
        searchContentButton.addActionListener(e -> {

            SearchPanel search;
            try {
                search = new SearchPanel();
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

            showPanelUSER(search.searchPanel);
        });
        setShowFavoritesButton();
        logOutButton.setPreferredSize(new Dimension(155,30));
        logOutButton.addActionListener(e -> {
            userFrame.dispose();
            try {
                new LoginFrame(true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
    private void setShowFavoritesButton(){
        showFavoritesButton.setText("Favorites");
        showFavoritesButton.setPreferredSize(new Dimension(155,30));
    }
    private void setUserFrame() throws IOException, ClassNotFoundException {
        userFrame.setSize(500, 500);
        userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userFrame.setResizable(false);
        userFrame.setLocationRelativeTo(null);
        userFrame.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setMainUserPanel();
        SearchPanel search=new SearchPanel();
        userFrame.add(mainUsersPanel);
        userFrame.add(search.searchPanel);

    }
    private void showPanelUSER(JPanel panelToShow) {
        userFrame.getContentPane().removeAll();
        userFrame.add(mainUsersPanel);
        userFrame.getContentPane().add(panelToShow);
        userFrame.revalidate();
        userFrame.repaint();
    }
}
