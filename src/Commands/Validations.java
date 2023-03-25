package Commands;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class Validations {

    private static final String INVALID_URL = "invalid URL";
    private static final String INVALID_COMMAND = "invalid command";
    private static final String INVALID_OPTION = "invalid option";
    private static final List<String> FLAGS = Arrays.asList("b", "c", "h", "i");


    /**
     * Checks if a given URL is valid.
     *
     * @param url the URL to be checked
     * @return true if the URL is valid, false otherwise
     * @throws RuntimeException if the URL is invalid
     */
    public static boolean checkUrl(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e)  {
            throw new RuntimeException(INVALID_URL);
        }
    }

    /**
     * Checks if the data provided for a command is null.
     *
     * @param data the data provided for a command
     * @param numOfParams the number of parameters the command should have
     * @throws IllegalArgumentException if the data is null
     */
    public static void dataIsNull(String[] data, int numOfParams) {
        if(data == null) return;
        numOfParameters(data.length, numOfParams+1);
        if(!data[0].equals("")) throw new IllegalArgumentException(INVALID_COMMAND);
    }

    /**
     * Checks if the number of parameters for a command is correct.
     *
     * @param commandLen the length of the command
     * @param numOfParams the number of parameters the command should have
     * @throws IllegalArgumentException if the number of parameters is incorrect
     */
    public static void numOfParameters(int commandLen, int numOfParams) {
        if (commandLen != numOfParams) {
            throw new IllegalArgumentException(INVALID_COMMAND);
        }
    }


    /**
     * Checks if the number of parameters provided for a command is one of the accepted numbers.
     * @param commandLen the number of parameters provided for a command.
     * @param numOfParams the accepted numbers of parameters.
     * @return true if the number of parameters is one of the accepted numbers.
     * @throws IllegalArgumentException if the number of parameters is not one of the accepted numbers.
     */
    public static boolean numOfParametersFlex(int commandLen, int ...numOfParams) {

        for (int i : numOfParams) {
            if (commandLen == i) {
                return true;
            }
        }

        throw new IllegalArgumentException(INVALID_COMMAND);
    }

    /**
     * Checks if the given options are valid.
     *
     * @param flags the options to be checked
     * @throws IllegalArgumentException if the options are invalid
     */
    public static void checkOptions(String[] flags) {

        if(flags.length == 0) {return;}

        if(!flags[0].equals("-")) {
            throw new IllegalArgumentException(INVALID_COMMAND);
        }
        for (int i = 0 ; i < flags.length ; i++) {
            if (i == 0) continue;
            if (!FLAGS.contains(flags[i])) {
                throw new IllegalArgumentException(INVALID_OPTION);
            }
        }
    }
}