//Author: Lucas Christianson

//This class is responsible for "wordwrapping" a given string input with a certain column width.


public class WordWrapper {

    public static void main(String[] args) {
       //main not implemented in final build, used for debugging purposes.
    }
    
    public static String wrap(String text, int maxLineLength) {

        //Checks if wrapping is needed and returns the original text if it is not.
        if(text.length() <= maxLineLength) return text;

        //Converts text from string to array of char's
        char [] arr = text.toCharArray();

        //Declare line number and previous space character index so they can be referenced for later use.
        int lineNumber = 1;
        int prevSpaceIndex = 0;
        int prevNewlineIndex = 0;

        //Cycles throught the char array looking for a space character and updating prevSpaceIndex.
        //Once i is beyond the length of the maxLineLength, the previous space is replaced with a new line, \n.
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == ' ') prevSpaceIndex = i;
            if(arr[i] == '\n') prevNewlineIndex = i;

            if(i / maxLineLength >= lineNumber && i - prevNewlineIndex > 14) {
                arr[prevSpaceIndex] = '\n';
                prevNewlineIndex = prevSpaceIndex;
                lineNumber++;
            }
        }
        //Converts array of formatted chars to a string in order to return.
        String formattedText = String.valueOf(arr);
        return formattedText;
    }
}