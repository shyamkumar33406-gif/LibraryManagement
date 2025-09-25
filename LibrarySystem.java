import java.util.*;

// Book Class
class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean issued) {
        this.isIssued = issued;
    }

    @Override
    public String toString() {
        String status = isIssued ? "Issued" : "Available";
        return bookId + ": " + title + " by " + author + " (" + status + ")";
    }
}

// User Class
class User {
    private int userId;
    private String name;
    private List<Integer> issuedBooks;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
        this.issuedBooks = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getIssuedBooks() {
        return issuedBooks;
    }

    @Override
    public String toString() {
        return userId + ": " + name + " (Books: " + issuedBooks + ")";
    }
}

// Library Class
class Library {
    private Map<Integer, Book> books = new HashMap<>();
    private Map<Integer, User> users = new HashMap<>();

    public void addBook(Book book) {
        books.put(book.getBookId(), book);
    }

    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public void issueBook(int bookId, int userId) {
        Book book = books.get(bookId);
        User user = users.get(userId);

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        if (book.isIssued()) {
            System.out.println("Book '" + book.getTitle() + "' is already issued.");
        } else {
            book.setIssued(true);
            user.getIssuedBooks().add(bookId);
            System.out.println("Book '" + book.getTitle() + "' issued to " + user.getName() + ".");
        }
    }

    public void returnBook(int bookId, int userId) {
        Book book = books.get(bookId);
        User user = users.get(userId);

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        if (user.getIssuedBooks().contains(bookId)) {
            book.setIssued(false);
            user.getIssuedBooks().remove(Integer.valueOf(bookId));
            System.out.println("Book '" + book.getTitle() + "' returned by " + user.getName() + ".");
        } else {
            System.out.println(user.getName() + " does not have this book.");
        }
    }

    public void showBooks() {
        for (Book b : books.values()) {
            System.out.println(b);
        }
    }

    public void showUsers() {
        for (User u : users.values()) {
            System.out.println(u);
        }
    }
}

// Test the system
public class LibrarySystem {
    public static void main(String[] args) {
        Library lib = new Library();

        // Add Books
        lib.addBook(new Book(1, "The Alchemist", "Paulo Coelho"));
        lib.addBook(new Book(2, "1984", "George Orwell"));
        lib.addBook(new Book(3, "Java Basics", "John Doe"));

        // Add Users
        lib.addUser(new User(1, "Alice"));
        lib.addUser(new User(2, "Bob"));

        // Show books
        System.out.println("\nBooks in library:");
        lib.showBooks();

        // Issue book
        System.out.println("\nIssuing book:");
        lib.issueBook(2, 1); // Book 2 to User 1

        // Show books after issuing
        System.out.println("\nBooks after issuing:");
        lib.showBooks();

        // Return book
        System.out.println("\nReturning book:");
        lib.returnBook(2, 1);

        // Show books after returning
        System.out.println("\nBooks after returning:");
        lib.showBooks();
    }
}