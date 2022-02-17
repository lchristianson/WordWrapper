//Author: Lucas Christianson

//This class is responsible for "wordwrapping" a given string input with a certain column width.


public class WordWrapper {

    public static void main(String[] args) {
       //main not implemented in final build, used for debugging purposes.
    }
    
    public static String wrap(String text, int maxLineLength) throws LineOverflowException {

        //Checks if wrapping is needed and returns the original text if it is not.
        if(text.length() <= maxLineLength) return text;

        //Converts text from string to array of char's
        char [] arr = text.toCharArray();

        //Declare line number, previous space character index, and previous new line character so they can be referenced for later use.
        int lineNumber = 1;
        int prevSpaceIndex = -1;  //-1 if no space for current line has been found yet.
        int prevNewlineIndex = 0;

        /* Cycles through the char array looking for a space character or a newline and updating prevSpaceIndex or prevNewlineIndex when either is found.
        Once i is beyond the length of the maxLineLength and the distance between i and the previous newline is greater than maxLineLength - 1,
        the previous space is replaced with a new line. */
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == ' ') prevSpaceIndex = i;
            if(arr[i] == '\n') prevNewlineIndex = i;

            //Checks if we've exceeded the max line length and are sufficiently far enough away from the previous newline to insert another newline.
            if(i / maxLineLength >= lineNumber && i - prevNewlineIndex > maxLineLength - 1) {

                //If there hasn't been a space character on the current line and are ready to insert a new line, the current word is too long
                //and a LineOverflowException is thrown.
                if(prevSpaceIndex == -1){ 
                    throw new LineOverflowException("Word exceeded maximum line length");
                }
                //insert newline char at previous word boundary.
                arr[prevSpaceIndex] = '\n';

                //update prevNewlineIndex
                prevNewlineIndex = prevSpaceIndex;

                //increment the line number and reset prevSpaceIndex.
                lineNumber++;
                prevSpaceIndex = -1;
            }
        }
        //Converts array of formatted chars to a string in order to return.
        String formattedText = String.valueOf(arr);
        return formattedText;
    }
}
