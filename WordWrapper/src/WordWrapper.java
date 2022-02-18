//Author: Lucas Christianson

//This class is responsible for "wordwrapping" a given string input with a certain column width.


public class WordWrapper {

    //Tab size would be pulled from the editor settings most likely.
    private static int tabSize = 8;

    public static void main(String[] args) {
        String text = "\tThis string begins with a tab.";
        String formatted = "\tThis\nstring begins\nwith a tab.";
        int l = 16;
        try {
            String answer = WordWrapper.wrap(text, l);
            System.out.println("The correct version: \n\n" + formatted + "\n\nThe wrap version: \n" + answer);
        } catch (LineOverflowException e) {
            System.out.println("No exception should occur.");
        }
    }
    
    public static String wrap(String text, int maxLineLength) throws LineOverflowException {

        //Declaration of method variables.
        int prevSpaceIndex = -1;  //-1 if no space for current line has been found yet.
        int prevNewlineIndex = -1;
        int tabCount = 0;

        //Checks if wrapping is needed and returns the original text if it is not.
        if(text == null || text.length() <= maxLineLength) return text;

        //Converts text from string to array of char's
        char [] arr = text.toCharArray();

        /* Cycles through the char array looking for a space character or a newline and updating prevSpaceIndex or prevNewlineIndex when either is found.
        Once i is greater than maxLineLength away from the previous new line, a new line is inserted at the most recent space character. */
        for(int i = 0; i < arr.length; i++) {

            //Checks and stores most recent space character index.
            if(arr[i] == ' ') prevSpaceIndex = i;

            //Checks for existing newlines and stores it.
            if(arr[i] == '\n') {
                tabCount = 0;
                prevNewlineIndex = i;
                continue;
            }

            if(arr[i] == '\t') {
                tabCount += tabSize - 1;
            }

            //calculates the distance between the current index and prevNewlineIndex and checks if the maxLineLength
            //has been exceeded. This takes in account if a tab was used on the current line.
            if(i + tabCount - prevNewlineIndex > maxLineLength) {

                //If there hasn't been a space character on the current line and it is ready to insert a new line, the current word is too long
                //and a LineOverflowException is thrown.
                if(prevSpaceIndex == -1){ 
                    throw new LineOverflowException("Word exceeded maximum line length.");
                }
                //insert newline char at previous word boundary.
                arr[prevSpaceIndex] = '\n';

                //After inserting a newline, the tab count is reset.
                tabCount = 0;
                
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
