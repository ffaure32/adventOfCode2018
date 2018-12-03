import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class DayTwo {
    public Optional<String> partTwo() throws Exception {
        Path path = Paths.get(getClass().getClassLoader()
                .getResource("inputDay2.txt").toURI());

        List<String> lines = Files.readAllLines(path);

        return findSiblingsForAllLines(lines);
    }

    public Optional<String> findSiblingsForAllLines(List<String> lines) {
        Optional<String> result = Optional.empty();
        int startIndex = 0;
        while(!result.isPresent()) {
            result = findSiblings(lines, startIndex);
            startIndex++;
        }
        return result;
    }


    public Optional<String> findSiblings(List<String> strings, int index) {
        String s = strings.get(index);
        List<String> limitedString = strings.subList(index + 1, strings.size() - 1);
        Optional<String> first = limitedString.stream().filter(st -> areSiblings(st, s)).findFirst();
        return first.map(st -> removeDistinctChar(st, s));
    }


    public String removeDistinctChar(String st, String s) {
        char[] chars = st.toCharArray();
        char[] otherChars = s.toCharArray();
        int diffIndex = -1;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != otherChars[i]) {
                diffIndex = i;
                break;
            }
        }
        return st.substring(0, diffIndex) + st.substring(diffIndex+1);

    }

    public boolean areSiblings(String st, String s) {
        char[] chars = st.toCharArray();
        char[] otherChars = s.toCharArray();
        int diffs = 0;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] != otherChars[i]) {
                diffs++;
            }
            if(diffs>1) {
                return false;
            }
        }
        return diffs == 1;
    }
}
