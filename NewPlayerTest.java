public class NewPlayerTest {
    //Class created to test player creation/deletion process
    public static void main(String[] args) {
        Player.createPlayer("test", "Tester", 1);
        Player.deletePlayer("test", "Tester");
    }
}
