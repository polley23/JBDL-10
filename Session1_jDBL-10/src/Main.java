import java.util.*;

public class Main {

    public static void main(String[] args){
        int b = 5; // primitive
        Integer a = b; //Autoboxing
        int c = a; //unboxing
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);

        PdfDocument pdfDocument1 = new PdfDocument(2);
        PdfDocument pdfDocument2 = new PdfDocument(1);

        //        integers
//                .stream()
//                .forEach(i -> System.out.println(i));

        List<PdfDocument> integerSet = new ArrayList<>();
        integerSet.add(pdfDocument1);
        integerSet.add(pdfDocument2);
        Collections.sort(integerSet, new Comparator<PdfDocument>() {
            @Override
            public int compare(final PdfDocument o1, final PdfDocument o2) {
                return o1.documentId-o2.documentId;
            }
        });
        integerSet
                .stream()
                .forEach(i -> System.out.println(i.documentId));


        //Document document = new PdfDocument();
        //print(new XmlDocument());
//        try {
//            int a = 4;
//            int b = 2;
//            int c = a/b;
//            if(c==2){
//                throw new ExceptionOn2("output is 2 ");
//            }
//        }
//        catch (ExceptionOn2 exception){
//
//        }
//        finally {
//            System.out.println("In the finally block");
//        }
    }

    static void print(Document document){
        DocumentPrinter documentPrinter = new DocumentPrinterImpl();

            documentPrinter.print(document);

    }
}
