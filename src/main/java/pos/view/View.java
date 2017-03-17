package pos.view;

import java.util.List;

/**
 * The view class for formatted outputting of the data into console
 */
public class View {

    /**
     * This method outputs the text message to the console without line feed
     *
     * @param message - message to output
     */
    public void printMessage(String message) {
        System.out.print(message);
    }

    /**
     * This method outputs the text message to the console with line feed
     *
     * @param message - message to output
     */
    public void printLineMessage(String message) {
        System.out.println(message);
    }


    /**
     * This method successively outputs the elements from the list into the console
     * applying to toString() method of each one previously
     *
     * @param list - {@link List} of elements
     */
    public void printList(List list) {
        for (Object aList : list) {
            System.out.print(aList.toString() + " ");
        }
        System.out.println();
    }

    /**
     * This method successively outputs the elements from the array into the console
     * applying to toString() method of each one previously
     *
     * @param objects - array of elements
     */
    public void printLineArray(Object[] objects) {
        for (Object object : objects) {
            System.out.println(object.toString());
        }
    }

}