package gui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AdminFrame {
     JFrame adminFrame=new JFrame("Authflix");
    JPanel mainAdminsPanel=new JPanel();
    JButton addContentButton=new JButton();
    JButton searchContentButton=new JButton("Search movies/series");
    JButton logOutButton=new JButton("Log out");
    protected  AdminFrame(){}

    protected AdminFrame(boolean visible) throws IOException, ClassNotFoundException {
        setAdminFrame();
        adminFrame.setVisible(visible);
    }
    private void setMainAdminPanel() {
        mainAdminsPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        mainAdminsPanel.setPreferredSize(new Dimension(500,30));
        mainAdminsPanel.setBackground(Color.CYAN);
        setAdminsButtons();
        mainAdminsPanel.add(addContentButton);
        mainAdminsPanel.add(searchContentButton);
        mainAdminsPanel.add(logOutButton);



    }
    private void setAdminsButtons(){
            setAddContentButton();
            setSearchContentButton();
            setLogOutButton();
    }
    private void setAddContentButton(){
        addContentButton.setText("Add movies/series");
        addContentButton.setPreferredSize(new Dimension(175,30));
        addContentButton.addActionListener(e ->{
            AddMovieAndSeriesPanel add;
            try {
                add = new AddMovieAndSeriesPanel();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            showPanelADMIN(add.addPanel);});
    }
    private void setSearchContentButton(){
        searchContentButton.setPreferredSize(new Dimension(175,30));
        searchContentButton.addActionListener(e -> {

            SearchPanel search;
            try {
                search = new SearchPanel();
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

            showPanelADMIN(search.searchPanel);
        });
    }
    private void setLogOutButton(){
        logOutButton.setPreferredSize(new Dimension(150,30));
        logOutButton.addActionListener(e -> {
            adminFrame.dispose();
            try {
                new LoginFrame(true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
      //  logOutButton.addActionListener();

    }
    private void showPanelADMIN(JPanel panelToShow) {
        adminFrame.getContentPane().removeAll();
        adminFrame.add(mainAdminsPanel);
        adminFrame.getContentPane().add(panelToShow);
        adminFrame.revalidate();
        adminFrame.repaint();
    }
    private void setAdminFrame() throws IOException, ClassNotFoundException {
        adminFrame.setSize(500,500);
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminFrame.setResizable(false);
        adminFrame.setLocationRelativeTo(null);
        adminFrame.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        setMainAdminPanel();
        SearchPanel search=new SearchPanel();
        adminFrame.add(mainAdminsPanel);
        adminFrame.add(search.searchPanel);
    }
}
