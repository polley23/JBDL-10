public class JsonDocument extends Document{
    @Override
    protected void open() {
        System.out.println("json document is open");

    }

    @Override
    protected void close() {
        System.out.println("json document is closed");
    }

    @Override
    protected void print() {
        System.out.println("printing json document");
    }
}
