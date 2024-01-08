package gui;

import api.SearchResults;
import api.ShowDetails;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.Objects;

public class SearchAndDisplayPanel {

    protected JPanel filtersPanel = new JPanel();
    private final JPanel typeFilter = new JPanel();
    private final JCheckBox movie = new JCheckBox("Movie");
    private final JCheckBox series = new JCheckBox("Series");

    private final JPanel protagonistFilter = new JPanel();
    private final JTextField actorName = new JTextField("Enter a name");


    private final JPanel contentRatingFilter = new JPanel();
    private final JCheckBox adult = new JCheckBox(">18");
    private final JCheckBox nonAdult = new JCheckBox("<18");

    private final JPanel categoryPanel = new JPanel();
    private final String[] categoryOptions = {"-", "HORROR", "DRAMA", "SCIENCE FICTION", "COMEDY", "ACTION"};
    private final JComboBox<String> categories = new JComboBox<>(categoryOptions);

    private final JPanel ratingPanel = new JPanel();
    private final String[] ratingOptions = {"-", "1", "2", "3", "4", "5"};
    private final JComboBox<String> rating = new JComboBox<>(ratingOptions);

    protected JPanel searchAndDisplayPanel = new JPanel();
    private final JTextField searchBar = new JTextField();
    private final JButton searchButton = new JButton("Search");
    private final JScrollPane displayListPanel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    JList<String> display;
    private final SearchResults results = new SearchResults();

    protected SearchAndDisplayPanel() throws IOException {
        setFiltersPanel();
        setDisplayPanel();
    }

    private void setFiltersPanel() {
        filtersPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        filtersPanel.setPreferredSize(new Dimension(110, 460));
        filtersPanel.setBackground(Color.decode("#568C76"));
        setTypeFilter();
        setProtagonistFilter();
        setContentRatingFilter();
        setCategoryFilter();
        setRatingPanel();
        filtersPanel.setBorder(new EmptyBorder(40, 0, 0, 0));
        filtersPanel.add(typeFilter);
        filtersPanel.add(protagonistFilter);
        filtersPanel.add(contentRatingFilter);
        filtersPanel.add(categoryPanel);
        filtersPanel.add(ratingPanel);
        filtersPanel.setVisible(true);

    }

    private void setTypeFilter() {
        JLabel type = new JLabel("Type");
        typeFilter.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        typeFilter.setPreferredSize(new Dimension(100, 75));
        typeFilter.setBackground(Color.yellow);
        type.setPreferredSize(new Dimension(100, 25));
        movie.setBackground(Color.yellow);
        series.setBackground(Color.yellow);
        movie.addActionListener(e -> {
            series.setSelected(false);
            updateList();
        });
        series.addActionListener(e -> {
            movie.setSelected(false);
            updateList();
        });
        movie.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        series.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        typeFilter.add(type);
        typeFilter.add(movie);
        typeFilter.add(series);
    }

    private void setProtagonistFilter() {
        JLabel protagonist = new JLabel("Protagonist");
        protagonistFilter.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        protagonistFilter.setPreferredSize(new Dimension(100, 65));
        protagonistFilter.setBackground(Color.yellow);
        protagonist.setPreferredSize(new Dimension(100, 25));
        actorName.setPreferredSize(new Dimension(90, 20));
        actorName.setForeground(Color.GRAY);
        actorName.setText("Enter a name");
        actorName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateList();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateList();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateList();
            }
        });
        actorName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (actorName.getText().equals("Enter a name")) {
                    actorName.setText("");
                    actorName.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (actorName.getText().isEmpty()) {
                    actorName.setText("Enter a name");
                    actorName.setForeground(Color.GRAY);
                }
            }
        });
        protagonistFilter.add(protagonist);
        protagonistFilter.add(actorName);
    }

    private void setContentRatingFilter() {

        contentRatingFilter.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        contentRatingFilter.setPreferredSize(new Dimension(100, 80));
        contentRatingFilter.setBackground(Color.yellow);
        JLabel contentRating = new JLabel("Content Rating");
        contentRating.setPreferredSize(new Dimension(100, 25));
        adult.setBackground(Color.yellow);
        nonAdult.setBackground(Color.yellow);
        adult.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        nonAdult.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        adult.addActionListener(e -> {
            nonAdult.setSelected(false);
            updateList();
        });
        nonAdult.addActionListener(e -> {
            adult.setSelected(false);
            updateList();
        });
        contentRatingFilter.add(contentRating);
        contentRatingFilter.add(adult);
        contentRatingFilter.add(nonAdult);
    }

    private void setCategoryFilter() {
        categoryPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        categoryPanel.setPreferredSize(new Dimension(100, 70));
        categoryPanel.setBackground(Color.yellow);
        JLabel category = new JLabel("Category");
        category.setPreferredSize(new Dimension(100, 25));
        categories.setPreferredSize(new Dimension(100, 20));
        categories.addActionListener(e -> updateList());
        categoryPanel.add(category);
        categoryPanel.add(categories);
    }

    private void setRatingPanel() {
        ratingPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        ratingPanel.setPreferredSize(new Dimension(100, 80));
        ratingPanel.setBackground(Color.decode("#568C76"));
        JLabel rating = new JLabel("Minimum Rating");
        rating.setPreferredSize(new Dimension(100, 25));
        this.rating.setPreferredSize(new Dimension(50, 20));
        this.rating.addActionListener(e -> updateList());
        ratingPanel.add(rating);
        ratingPanel.add(this.rating);

    }

    private void setDisplayPanel() {
        searchAndDisplayPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        searchAndDisplayPanel.setPreferredSize(new Dimension(390, 460));
        searchAndDisplayPanel.setBackground(Color.decode("#568C76"));
        setSearchBarAndButton();
        setDisplayListPanel();
        searchAndDisplayPanel.add(searchBar);
        searchAndDisplayPanel.add(searchButton);
        searchAndDisplayPanel.add(displayListPanel);
    }

    private void setSearchBarAndButton() {
        searchBar.setPreferredSize(new Dimension(275, 30));
        searchBar.setForeground(Color.GRAY);
        searchBar.setText("Enter the title here");
        searchBar.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchBar.getText().equals("Enter the title here")) {
                    searchBar.setText("");
                    searchBar.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchBar.getText().isEmpty()) {
                    searchBar.setText("Enter the title here");
                    searchBar.setForeground(Color.GRAY);
                }
            }
        });
        searchButton.setPreferredSize(new Dimension(80, 30));
        searchButton.addActionListener(e -> updateList());
    }

    private void setDisplayListPanel() {
        listModel.addAll(results.displayData(searchBar.getText(), movie.isSelected(), series.isSelected(), actorName.getText(), adult.isSelected(), nonAdult.isSelected(), Objects.requireNonNull(categories.getSelectedItem()).toString(), ((Objects.requireNonNull(rating.getSelectedItem()).toString()).equals("-")) ? 0 : (Integer.parseInt(Objects.requireNonNull(rating.getSelectedItem()).toString()))));
        display = new JList<>(listModel);
        display.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        display.setLayoutOrientation(JList.VERTICAL);
        display.setBorder(BorderFactory.createLineBorder(Color.black));
        if (listModel.isEmpty()) {
            displayListPanel.setViewportView(new JLabel("NO DATA FOUND", SwingConstants.CENTER));
        } else {
            displayListPanel.setViewportView(display);
        }
        display.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                try {
                    ShowDetails selected = new ShowDetails();
                    if (display.getSelectedValue().contains("Movie")) {
                        MovieSelectedPanel movieDetails=new MovieSelectedPanel(selected.showMovieDetails(display.getSelectedValue()));
                        showPanel(movieDetails.movieSelectedPanel);
                    }else {
                        SeriesSelectedPanel seriesDetails=new SeriesSelectedPanel(selected.showSeriesDetails(display.getSelectedValue()));
                        showPanel(seriesDetails.seriesSelectedPanel);
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        displayListPanel.setPreferredSize(new Dimension(360, 375));
    }

    private void showPanel(JPanel displayPanel) {
        searchAndDisplayPanel.remove(displayListPanel);
        searchAndDisplayPanel.add(displayPanel);
        searchAndDisplayPanel.revalidate();
        searchAndDisplayPanel.repaint();

    }
    private void updateList(){
        if(display.getSelectedValue() == null){
            listModel.removeAllElements();
            listModel.addAll(results.displayData(searchBar.getText(), movie.isSelected(), series.isSelected(), actorName.getText(), adult.isSelected(), nonAdult.isSelected(), Objects.requireNonNull(categories.getSelectedItem()).toString(), ((Objects.requireNonNull(this.rating.getSelectedItem()).toString()).equals("-")) ? 0 : (Integer.parseInt(Objects.requireNonNull(this.rating.getSelectedItem()).toString()))));
        }
    }
}
