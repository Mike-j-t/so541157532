package so54115753.so54115753;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        //Add some users
        addSomeUsers();
        logUsers();
        playWithUsers();
        addSomePlayers();
        logPlayers();
    }

    /**
     * Add some users if none already exist
     */
    private void addSomeUsers() {
        // Only add some users if there are not any
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        if (DatabaseUtils.queryNumEntries(db,DatabaseHelper.TABLE_USER) > 0) return;
        db.close();
        // Add 3 testing users
        //  USER0 with email USER0@USER0.email.com password is password
        //  USER1 with email USER1@USER1.email.com password is password
        //  USER2 with email USER2@USER2.email.com password is password
        int users_to_add = 3;
        for (int i = 0; i < users_to_add; i++ ) {
            String thisusername = "USER" + String.valueOf(i);
            String thisemail = thisusername + "@" + thisusername + ".email.com";
            User u = new User(thisusername,thisemail,"password", Long.valueOf(i));
            databaseHelper.addUser(u);
        }
        logUsers();
    }

    /**
     * Use some of the user methods
     */
    private void playWithUsers() {

        String logtag = "USERTESTING";
        String notauseremail = "notauser@noemail.com";
        String validuseremail = "USER2@USER2.email.com";
        if (databaseHelper.checkUser(notauseremail)) {
            Log.d(logtag,"Ooops - " + notauseremail + " appears to exist when it should not!!!!");
        } else {
            Log.d(logtag,"OK - User with email " + notauseremail + "not found as expected.");
        }
        if (databaseHelper.checkUser(validuseremail)) {
            Log.d(logtag,"OK - User with email " + validuseremail + " found as expected");
        } else {
            Log.d(logtag,"Ooops - " + validuseremail + " does not exist as expected!!!");
        }

        Log.d(
                logtag,
                "Username for user " +
                        notauseremail +
                        " is " +
                        databaseHelper.getColumnUserName(notauseremail)
        );
        Log.d(
                logtag,
                "Username for user " +
                        validuseremail +
                        " is" +
                        databaseHelper.getColumnUserName(validuseremail)
        );
        if (databaseHelper.checkUser(notauseremail,"password")) {
            Log.d(logtag,"Ooops - " + notauseremail + " appears to exist when it should not!!!!");
        } else {
            Log.d(logtag,"OK - User with email " + notauseremail + "not found as expected.");
        }
        if (databaseHelper.checkUser(validuseremail,"password")) {
            Log.d(logtag,"OK - User with email " + validuseremail + " found as expected (password matched)");
        } else {
            Log.d(logtag,"Ooops - " + validuseremail + " does not exist as expected!!!");
        }
    }

    private void addSomePlayers() {

        // Only add some players if none exist
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        if (DatabaseUtils.queryNumEntries(db,DatabaseHelper.TABLE_PLAYERS) > 0) return;
        db.close();
        String logtag = "PLAYERTESTING";

        Player invalid = new Player();
        invalid.setPlayerName("Harold");
        invalid.setPlayerID(100); //<<<<<< ouch if added to db
        invalid.setForeignKey(1000); //<<< overdides id but also ouch if added to db
        invalid.setPlayerAge(20);
        invalid.setPlayerWeight(65);
        invalid.setPlayerHeight(120);
        databaseHelper.addPlayer(invalid);
        /*
        Does not add the player but does not crash
        E/SQLiteDatabase: Error inserting Player_name=Harold Player_age=20 User_id=1000 Player_height=120 Player_weight=65
    android.database.sqlite.SQLiteConstraintException: FOREIGN KEY constraint failed (code 787 SQLITE_CONSTRAINT_FOREIGNKEY)
         */


        Player ok = new Player();
        ok.setPlayerName("Anne");
        ok.setPlayerID(1);
        ok.setPlayerAge(20);
        ok.setPlayerWeight(65);
        ok.setPlayerHeight(120);
        databaseHelper.addPlayer(ok);
        /*
            Does not add because foreign key is used not the id, no crash
         */
        ok.setForeignKey(1);
        databaseHelper.addPlayer(ok);
        /*
            May not add if user row with id = 1
         */
    }

    private void logUsers() {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        DatabaseUtils.dumpCursor(db.query(DatabaseHelper.TABLE_USER,null,null,null,null,null,null));
        db.close();
    }

    private void logPlayers() {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        DatabaseUtils.dumpCursor(db.query(DatabaseHelper.TABLE_PLAYERS,null,null,null,null,null,null));
        db.close();
    }
}
