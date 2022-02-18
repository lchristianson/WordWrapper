//Author: Lucas Christianson

//This class is responsible for "wordwrapping" a given string input with a certain column width.


public class WordWrapper {

    //Declare line number, previous space character index, and previous new line character so they can be referenced for later use.
    private static int prevSpaceIndex = -1;  //-1 if no space for current line has been found yet.
    private static int prevNewlineIndex = -1;
    private static int maxLength;


    public static void main(String[] args) {

        //Main not in final build, used for test purposes only.
    }
    
    public static String wrap(String text, int maxLineLength) throws LineOverflowException {

        maxLength = maxLineLength;

        //Checks if wrapping is needed and returns the original text if it is not.
        if(text.length() <= maxLength) return text;

        //Converts text from string to array of char's
        char [] arr = text.toCharArray();

        /* Cycles through the char array looking for a space character or a newline and updating prevSpaceIndex or prevNewlineIndex when either is found.
        Once i is beyond the length of the maxLineLength and the distance between i and the previous newline is greater than maxLineLength - 1,
        the previous space is replaced with a new line. */
        for(int i = 0; i < arr.length; i++) {

            //Checks and stores most recent space character index.
            if(arr[i] == ' ') prevSpaceIndex = i;

            //Checks for existing newlines and stores it.
            //If a new line is present the wasted space of the line needs to be calculated with calculateWastedSpace().
            if(arr[i] == '\n') {
                prevNewlineIndex = i;
                continue;
            }

            //Checks if we've exceeded the max line length and are sufficiently far enough away from the 
            //previous manually entered newline to insert another newline.
            if(i - prevNewlineIndex > maxLength) {

                //If there hasn't been a space character on the current line and are ready to insert a new line, the current word is too long
                //and a LineOverflowException is thrown.
                if(prevSpaceIndex == -1){ 
                    throw new LineOverflowException("Word exceeded maximum line length.");
                }
                //insert newline char at previous word boundary.
                arr[prevSpaceIndex] = '\n';
                
                //update prevNewlineIndex
                prevNewlineIndex = prevSpaceIndex;

                //reset prevSpaceIndex.
                prevSpaceIndex = -1;
            }
        }
        
        //Converts array of formatted chars to a string in order to return.
        String formattedText = new String(arr);
        return formattedText;
    }

}
