//Author: Lucas Christianson

//This class is responsible for "wordwrapping" a given string input with a certain column width.


public class WordWrapper {

    //Declare line number, previous space character index, and previous new line character so they can be referenced for later use.
    private static int lineNumber = 1;
    private static int prevSpaceIndex = -1;  //-1 if no space for current line has been found yet.
    private static int prevNewlineIndex = 0;
    private static int prevLineCharCount = 0;
    private static int wastedSpace = 0; //sum total of wasted line space.
    private static int maxLength;


    public static void main(String[] args) {

        //Main not in final build, used for test purposes only.


        String text = "This line of       text has multiple spaces where a line will be inserted";
        String formatted = "This line of   \n   text has\nmultiple spaces\nwhere a line\nwill be inserted";
        int l = 16;
        try{
            String answer = WordWrapper.wrap(text, l);
            System.out.println("This is the correct version:");
            System.out.println();
            System.out.println("This is the version from wrap()\n");
            System.out.println(formatted);
            System.out.println();
            System.out.println(answer);
        } catch (LineOverflowException e) {
            System.out.println("Exception caught.");
        }
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
                calculateWastedSpace(i);
            }
               

            //Checks if we've exceeded the max line length and are sufficiently far enough away from the 
            //previous manually entered newline to insert another newline.
            if((i + wastedSpace / maxLength) >= lineNumber && i - prevNewlineIndex > maxLength) {

                //If there hasn't been a space character on the current line and are ready to insert a new line, the current word is too long
                //and a LineOverflowException is thrown.
                if(prevSpaceIndex == -1){ 
                    throw new LineOverflowException("Word exceeded maximum line length.");
                }
                //insert newline char at previous word boundary.
                arr[prevSpaceIndex] = '\n';

                //update prevNewlineIndex
                prevNewlineIndex = prevSpaceIndex;


                //Updates the wastedSpace count.
                //increment the line number and reset prevSpaceIndex.
                calculateWastedSpace(i);
                lineNumber++;
                prevSpaceIndex = -1;
            }
        }

        //Converts array of formatted chars to a string in order to return.
        String formattedText = String.valueOf(arr);
        return formattedText;
    }

    //This method calculates the char length of the previous line.
    //Then, it updates the running total of "wasted space" present in the text
    //used
    private static void calculateWastedSpace(int j) {
        prevLineCharCount = (j-1) - prevLineCharCount;
        wastedSpace += lineNumber * maxLength - prevLineCharCount;
    }
}
