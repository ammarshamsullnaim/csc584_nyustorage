package shelf;

public class Shelf {
    private String shelf_id;
    private String shelf_row;
    private int shelf_column;

    public Shelf(String shelf_id, String shelf_row, int shelf_column) {
        this.shelf_id = shelf_id;
        this.shelf_row = shelf_row;
        this.shelf_column = shelf_column;
    }

    public String getShelf_id() {
        return shelf_id;
    }

    public void setShelf_id(String shelf_id) {
        this.shelf_id = shelf_id;
    }

    public String getShelf_row() {
        return shelf_row;
    }

    public void setShelf_row(String shelf_row) {
        this.shelf_row = shelf_row;
    }

    public int getShelf_column() {
        return shelf_column;
    }

    public void setShelf_column(int shelf_column) {
        this.shelf_column = shelf_column;
    }

    public Shelf() {
        // Default constructor
    }
}
