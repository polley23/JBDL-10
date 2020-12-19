import java.util.Comparator;

public abstract class Document {
    protected Integer documentId;
    protected String documentType;

    protected abstract void open();
    protected abstract void close();
    protected abstract void print();
}
