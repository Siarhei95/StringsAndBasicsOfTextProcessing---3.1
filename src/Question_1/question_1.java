package Question_1;//Cоздать приложение, разбирающее  текст  (текст хранится в строке) и отсортировать  абзацы по количеству предложений;
                   //в  каждом предложении отсортировать слова по длине;отсортировать лексемы в  предложении  по убыванию количества вхождений заданного символа.
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Collections;


public class question_1 {
    public static void main(String[] args) {
        String text = (" Sport plays a big role in our life. Many people do sport in our country." +
                " They want to stay healthy. Sport is a good mean of struggling with stress." +
                " In my opinion sport is important for healthy free time." +
                " Sport also makes people strong and prepares a lot of joy.");

        System.out.println("Original text: " + text);
        System.out.println();
        System.out.println("New Paragraphs: ");
        divideTextIntoParagraph(text);
        System.out.println();
        System.out.println("Sort words-length: ");
        sortWordLength(text);
        System.out.println();
        System.out.println("Find numbers of occurrences of characters: ");
        numberOfOccurrencesOfCharacters(text);
    }

        //1)разделим целый текст на абзацы(предложение - абзац).
        public static void divideTextIntoParagraph(String text) {
            Pattern pattern = Pattern.compile("([^.!?]+)");
            Matcher matcher = pattern.matcher(text.trim());
            while (matcher.find()) {
                System.out.println(matcher.group(1) + ".");
            }
        }

        //2)расположим каждое слово в предложениях по возрастанию.
        public static void sortWordLength(String text) {
            String[] results = text.toLowerCase().split("([.!?]+)");
            for (String s : results) {
                String[] arr = s.split(" ");
                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < arr.length - 1 - i; j++) {
                        if (arr[j].length() > arr[j + 1].length()) {
                            String str = arr[j];
                            arr[j] = arr[j + 1];
                            arr[j + 1] = str;
                        }
                    }
                }
                System.out.println(Arrays.toString(arr));
            }
        }

        //3)найдем количество вхождений по каждому слову и расположим в порядке убывания. (Использовал информацию из интернета, проанализировав написал следующий код)
        public static void numberOfOccurrencesOfCharacters(String text) {

        StringTokenizer tokenizer = new StringTokenizer(text, " "); //отделим каждое слово
        ArrayList<String> token = new ArrayList<String>();
        while (tokenizer.hasMoreTokens()) {
            token.add(tokenizer.nextToken());
        }
        String[] string = token.toArray(new String[token.size()]); //найдем все лексемы с помощью StringTokenizer и поместим в ArrayList

        TreeMap<String, Integer> map = new TreeMap<>(Collections.reverseOrder()); //узнаем количество вхождений

        for (int i = 0; i < string.length; i++) {
            if (map.containsKey(string[i])) {
                map.put(string[i], map.get(string[i]) + 1);
            } else {
                map.put(string[i], 1);
            }
        }
        List list = new ArrayList((map.entrySet()));                           //отсортируем массив по значениям
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        for (Object num : list) {
            String str = num.toString();
            System.out.println(str.replace('=', '-'));
        }
    }
}