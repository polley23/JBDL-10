import java.util.Comparator;

public class PdfDocument extends Document {
    PdfDocument(int i){
        super.documentId = i;
    }
    @Override
    protected void open() {
        System.out.println("pdf document is open");
    }

    @Override
    protected void close() {
        System.out.println("pdf document is close");
    }

    @Override
    protected void print() {
        //code
        System.out.println("printing pdf document");
    }

}
