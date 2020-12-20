//import java.io.*;
//
//public class FileHandler {
//
//    public static void main(String[] args) {
//        FileWriter fileWriter = null;
//        try {
//            fileWriter = new FileWriter("test.txt",false);
//            String str = "Today we will be learning multi threading\nAlso File ";
//            for(Character c : str.toCharArray()){
//                fileWriter.append(c);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                fileWriter.close();
//
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//        try {
//            FileReader fileReader = new FileReader("test.txt");
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            String line = bufferedReader.readLine();
//            while (line !=null){
//                System.out.println(line);
//                line = bufferedReader.readLine();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
