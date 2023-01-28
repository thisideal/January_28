import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        String filePath = "src\\example_text.txt";
        String string = readFile(filePath);

        System.out.println(string);
        System.out.println(getWords(string));
        printStringData(string);
    }

    public static String readFile(String filePath) throws IOException {
        String content = "";

        Path path = Paths.get(filePath);
        byte[] bytes = Files.readAllBytes(path);

        content = new String(bytes);
        return  content;
    }

    public static List<String> getWords(String text) {
        String filtered_text = text;
        String filer = ",.!?\n";

        for (char c : filer.toCharArray()) {
            filtered_text = filtered_text.replace(String.valueOf(c), "");
        }
        filtered_text = filtered_text.toLowerCase();

        List<String> words = Arrays.asList(filtered_text.split(" "));
        return words;
    }

    public static void printStringData(String text) {
        int charCount = text.length();
        List<String> words = getWords(text);
        int wordCount = words.size();

        ArrayList<String> unique_list = new ArrayList<>();
        ArrayList<Integer> frequency_list = new ArrayList<>();
        for(String word : words) {

            if(!unique_list.contains(word))
                unique_list.add(word);
        }
        for (String unique_word : unique_list) {
            int count;
            count = (int)words.stream().filter(unique_word::equals).count();
            frequency_list.add(count);
        }

        System.out.println("Character count: " + charCount);
        System.out.println("Word count: " + wordCount);
        System.out.println("Unique word count: " + unique_list.size());
        System.out.println("Unique words: ");
        for (int i = 0; i < unique_list.size(); i++) {
            String s = unique_list.get(i) + " - " + frequency_list.get(i);
            System.out.printf("%-22s", s);
//            if(i%4 == 0)
//                System.out.print("\n");
            System.out.print("\n");
        }

    }
}
