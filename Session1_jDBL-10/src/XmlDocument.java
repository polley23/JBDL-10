public class XmlDocument extends Document{
    @Override
    protected void open() {
        System.out.println("xml document is open");

    }

    @Override
    protected void close() {
        System.out.println("xml document is closed");
    }

    @Override
    protected void print() {
        System.out.println("xml json document");
    }
}
