public class DatabaseTests {

    public static void main(String[] args) {
        //Modify method calls in this function to change test
        //NewPlayerTest();
        NewUserProfileTest();
    }

    //Class created to test player creation/deletion process
    public static void NewPlayerTest() {
        Player.createPlayer("test", "Tester", 1);
        Player.deletePlayer("test", "Tester");
    }

    public static void NewUserProfileTest() {
        Profile.createProfile("test@test.com", "test", "password");
        Profile.deleteProfile("test");
    }
}
