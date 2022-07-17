import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testcase.TestCase;

import java.util.List;


class Clue {
    int age;
    String name;

    Clue(String name, int age) {
        this.age = age;
        this.name = name;
    }
}


public class Tests extends StageTest<Clue> {

    @Override
    public List<TestCase<Clue>> generate() {
        return List.of(
            new TestCase<Clue>()
                .setInput("John\n1\n2\n1")
                .setAttach(new Clue("John", 22)),

            new TestCase<Clue>()
                .setInput("Nick\n2\n0\n0")
                .setAttach(new Clue("Nick", 35))
        );
    }

    @Override
    public CheckResult check(String reply, Clue clue) {

        String[] lines = reply.trim().split("\n");

        if (lines.length != 7) {
            return CheckResult.wrong(
                "You should output 7 lines. Lines found: " + lines.length + "\n" +
                    "Your output:\n" +
                    reply
            );
        }

        String lineWithName = lines[3].toLowerCase();
        String name = clue.name.toLowerCase();

        if (!lineWithName.contains(name)) {
            return CheckResult.wrong(
                "The name was " + clue.name + "\n" +
                    "But the 4-th line was:\n" +
                    "\"" + lines[3] + "\"\n\n" +
                    "4-th line should contain a name of the user"
            );
        }

        String lineWithAge = lines[6].toLowerCase();
        String age = Integer.toString(clue.age);

        if (!lineWithAge.contains(age)) {
            return CheckResult.wrong(
                "Can't find a correct age " +
                    "in the last line of output! " +
                    "Maybe you calculated the age wrong?\n\n" +
                    "Your last line: \n" + "\"" + lines[6] + "\""
            );
        }

        return CheckResult.correct();
    }

}
