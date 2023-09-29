
package file.tagging.poc;

public class GetSystemProperty {
    public static void main( String args[] ) {
        //Operating system name
        System.out.println("Your OS name -> " + System.getProperty("os.name"));

        //Operating system version
        System.out.println("Your OS version -> " + System.getProperty("os.version"));

        //Operating system architecture
        System.out.println("Your OS Architecture -> " + System.getProperty("os.arch"));
    }
}
