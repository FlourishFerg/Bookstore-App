package com.example.bookstoreapp;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainController {
    private List<Book> allBooks;
    private TabPane tabPane;

    public VBox getUI() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.getStyleClass().add("root");

        TextField searchField = new TextField();
        searchField.setPromptText("Search for books...");
        searchField.getStyleClass().add("search-field");

        Button searchButton = new Button("Search");
        searchButton.getStyleClass().add("search-button");
        searchButton.setOnAction(e -> filterBooks(searchField.getText()));

        HBox searchBox = new HBox(10);
        searchBox.getChildren().addAll(searchField, searchButton);

        tabPane = new TabPane();
        tabPane.getStyleClass().add("tab-pane");

        allBooks = getAllBooks();
        createTabs();

        root.getChildren().addAll(searchBox, tabPane);

        return root;
    }

    private void createTabs() {
        tabPane.getTabs().clear();
        tabPane.getTabs().add(createCategoryTab("Romance", filterBooksByCategory("Romance")));
        tabPane.getTabs().add(createCategoryTab("Motivational Books", filterBooksByCategory("Motivational")));
        tabPane.getTabs().add(createCategoryTab("Dark Romance", filterBooksByCategory("Dark Romance")));
        tabPane.getTabs().add(createCategoryTab("Supernatural",filterBooksByCategory("Supernatural")));
    }

    private Tab createCategoryTab(String category, List<Book> books) {
        Tab tab = new Tab(category);
        tab.getStyleClass().add("tab");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(20);
        grid.setVgap(20);
        grid.getStyleClass().add("grid-pane");

        int row = 0;
        int col = 0;
        for (Book book : books) {
            VBox bookBox = createBookBox(book);
            grid.add(bookBox, col, row);

            col++;
            if (col == 4) {
                col = 0;
                row++;
            }
        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(grid);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.getStyleClass().add("scroll-pane");

        tab.setContent(scrollPane);
        return tab;
    }

    private VBox createBookBox(Book book) {
        VBox bookBox = new VBox(10);
        bookBox.getStyleClass().add("book-box");

        ImageView imageView = new ImageView(new Image("file:" + book.getImagePath()));
        imageView.setFitHeight(200);
        imageView.setFitWidth(150);
        imageView.getStyleClass().add("image-view");

        Label nameLabel = new Label(book.getName());
        nameLabel.getStyleClass().add("label");

        Label authorLabel = new Label("by " + book.getAuthor());
        authorLabel.getStyleClass().add("label");

        Label ratingLabel = new Label("Rating: " + book.getRating());
        ratingLabel.getStyleClass().add("label");

        Label priceLabel = new Label("N" + book.getPrice());
        priceLabel.getStyleClass().add("label");

        Label descriptionLabel = new Label(book.getDescription());
        descriptionLabel.getStyleClass().add("description-label");
        descriptionLabel.setWrapText(true);

        bookBox.getChildren().addAll(imageView, nameLabel, authorLabel, ratingLabel, priceLabel, descriptionLabel);

        bookBox.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            BookDetailView detailView = new BookDetailView(book);
            detailView.show();
        });

        return bookBox;
    }

    private List<Book> getAllBooks() {
        return Arrays.asList(
                new Book("Icebreaker", "Hannah Grace", 4.5, 100, "", "src/main/resources/com/example/bookstoreapp/covers/_Icebreaker_-_Hannah_Grace_cover.jpg",  "Romance"),
                new Book("Atomic Habits", "James Clear", 4.0, 100, "", "src/main/resources/com/example/bookstoreapp/covers/_OceanofPDF.com_It_Ends_with_Us_-_Colleen_Hoover_cover.jpg", "Motivational"),
                new Book("It Ends With Us", "Colleen Hoover", 4.3, 100, "", "src/main/resources/com/example/bookstoreapp/covers/_OceanofPDF.com_It_Ends_with_Us_-_Colleen_Hoover_cover.jpg", "Romance"),
                new Book("Haunting Adeline", "H.D. Carlton", 5.0, 100, "", "src/main/resources/com/example/bookstoreapp/covers/_Haunting_Adeline_-_H_D_Carlton_cover.jpg", "Dark Romance"),
                new Book("The Daily Laws", "Robert Greene", 4.6, 100, "", "src/main/resources/com/example/bookstoreapp/covers/_OceanofPDF.com_The_Daily_Laws_366_Meditations_on_Power_Seduction_Mastery_Strategy_and_Human_Nature_-_Robert_Greene_cover.jpg", "Motivational"),
                new Book("Skin of a Sinner", "Avina St. Graves", 4.1, 100, "", "src/main/resources/com/example/bookstoreapp/covers/_OceanofPDF.com_Skin_of_a_Sinner_-_Avina_St_Graves_cover.jpg", "Dark Romance"),
                new Book("King of Wrath", "Ana Huang", 4.8, 100, "", "src/main/resources/com/example/bookstoreapp/covers/_OceanofPDF.com_King_of_Wrath_-_Ana_Huang_cover.jpg", "Romance"),
                new Book("King of Greed", "Ana Huang", 4.7, 100, "", "src/main/resources/com/example/bookstoreapp/covers/_OceanofPDF.com_King_of_Greed_-_Ana_Huang_cover.jpg", "Romance"),
                new Book("When She Loves", "Gabrielle Sands", 4.9, 100, "", "src/main/resources/com/example/bookstoreapp/covers/_OceanofPDF.com_When_She_Loves_-_Gabrielle_Sands_cover.jpg", "Romance"),
                new Book("A Courth of Thorns and Roses", "Sarah J. Maas", 4.5, 100, "", "src/main/resources/com/example/bookstoreapp/covers/_A_Court_of_Thorns_and_Roses_-_Sarah_J_Maas_cover.jpg", "Romance"),
                new Book("Fourth Wing", "Rebecca Yarros", 4.0, 100, "", "src/main/resources/com/example/bookstoreapp/covers/Fourth_Wing_Special_Edition_-_Rebecca_Yarros_cover.jpg", "Supernatural"),
                new Book("Iron Flame", "Rebecca Yarros", 4.3, 100, "", "src/main/resources/com/example/bookstoreapp/covers/_OceanofPDF.com_Iron_Flame_-_Rebecca_Yarros_cover.jpg", "Supernatural"),
                new Book("Pretty Ugly Promises", "C.W. Farnsworth", 5.0, 100, "", "src/main/resources/com/example/bookstoreapp/covers/_OceanofPDF.com_Pretty_Ugly_Promises_-_CW_Farnsworth_cover.jpg", "Dark Romance"),
                new Book("Twisted Love", "Ana Huang", 4.6, 100, "", "src/main/resources/com/example/bookstoreapp/covers/_OceanofPDF.com_Twisted_Love_-_Ana_Huang_cover.jpg", "Dark Romance"),
                new Book("Twisted Games", "Ana Huang", 4.1, 100, "", "src/main/resources/com/example/bookstoreapp/covers/_OceanofPDF.com_Twisted_Games_cover.jpg", "Dark Romance"),
                new Book("Twisted Hate", "Ana Huang", 4.8, 100, "", "src/main/resources/com/example/bookstoreapp/covers/_OceanofPDF.com_Twisted_Hate_-_Ana_Huang_cover.jpg", "Dark Romance"),
                new Book("Neon Gods", "Katee Robert", 4.7, 100, "", "src/main/resources/com/example/bookstoreapp/covers/Neon Gods (Katee Robert) (Z-Library)_cover.jpg", "Romance"),
                new Book("God of Malice", "Rina Kent", 4.1, 1000, "", "src/main/resources/com/example/bookstoreapp/covers/God_of_Malice_-_Rina_Kent_cover.jpg", "Dark Romance"),
                new Book("God of Fury", "Rina Kent", 4.8, 100, "", "src/main/resources/com/example/bookstoreapp/covers/_God_of_fury_-_Rina_kent_cover.jpg", "Dark Romance"),
                new Book("The Sweetest Oblivion", "Danielle Lori", 5.0, 100, "", "src/main/resources/com/example/bookstoreapp/covers/_OceanofPDF.com_The_Sweetest_Oblivion_-_Danielle_Lori_cover.jpg", "Romance")

        );
    }

    private List<Book> filterBooksByCategory(String category) {
        return allBooks.stream().filter(book -> book.getCategory().equalsIgnoreCase(category)).collect(Collectors.toList());
    }

    private void filterBooks(String searchText) {
        List<Book> filteredBooks = allBooks.stream()
                .filter(book -> book.getName().toLowerCase().contains(searchText.toLowerCase())
                        || book.getAuthor().toLowerCase().contains(searchText.toLowerCase())
                        || book.getDescription().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());

        Tab searchTab = createCategoryTab("Search Results", filteredBooks);
        searchTab.setClosable(true);

        tabPane.getTabs().removeIf(tab -> tab.getText().equals("Search Results"));
        tabPane.getTabs().add(searchTab);
        tabPane.getSelectionModel().select(searchTab);
    }

    private List<Book> filterBooksByCategoryAndSearch(List<Book> books, String category) {
        return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

}