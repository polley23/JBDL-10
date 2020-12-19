public class DocumentPrinterImpl  implements DocumentPrinter, DocumentCloser{
    @Override
    public void print(final Document document) {
        document.print();
    }

    @Override
    public void closer(final Document document) {
        document.close();
    }
}
