package so54115753.so54115753;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MyDB1.db";
    public static final String TABLE_USER = "User"; //<<<<<<<<<< CHANGED to public
    private static final String COLUMN_USER_NAME = "User_name";
    private static final String COLUMN_USER_ID = "User_id";
    private static final String COLUMN_USER_EMAIL = "User_email";
    private static final String COLUMN_USER_PASSWORD = "User_password";

    public static final String TABLE_PLAYERS = "Player"; //<<<<<<<<< CHANGED to public
    private static final String COLUMN_PLAYER_NAME = "Player_name";
    private static final String COLUMN_PLAYER_AGE = "Player_age";
    private static final String COLUMN_PLAYER_WEIGHT = "Player_weight";
    private static final String COLUMN_PLAYER_HEIGHT = "Player_height";
    private static final String COLUMN_PLAYER_ID = "Player_id";
    private static final String FOREIGN_PLAYER_ID = COLUMN_USER_ID;
    // private static final Image COLUMN_PLAYER_IMAGE ;

    // Table 1 : Login/Register
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" +
            COLUMN_USER_NAME + " TEXT," +
            COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_USER_EMAIL + " TEXT," +
            COLUMN_USER_PASSWORD + " TEXT" + ")";


    // Table 2 : Adding players
    private String CREATE_PLAYER_TABLE = "CREATE TABLE " + TABLE_PLAYERS + "(" +
            COLUMN_PLAYER_NAME + " TEXT," +
            COLUMN_PLAYER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_PLAYER_AGE + " INTEGER," +
            COLUMN_PLAYER_WEIGHT + " INTEGER," +
            COLUMN_PLAYER_HEIGHT + " INTEGER, " +
            FOREIGN_PLAYER_ID + " INTEGER," + "FOREIGN KEY(" + FOREIGN_PLAYER_ID + ") REFERENCES " +
            TABLE_USER + "(" +
            COLUMN_USER_ID +
            ") " +
            ")";

    //<<<<<<<<<< Note AUTOINCREMENT IS NOT NECESSARY SUGGEST TO NOT USE i.e. just have INTEGER PRIMARY KEY


    // Drop tables

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER ;
    private String DROP_PLAYER_TABLE = "DROP TABLE IF EXISTS " + TABLE_PLAYERS ;


    public DatabaseHelper(Context context){
        //String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_PLAYER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_PLAYER_TABLE);
        onCreate(db);
    }


    // Adding a user to Users table
    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(FOREIGN_PLAYER_ID, user.getForeignID()); //<<<<<<<<< SUGGEST NOT INCLUDING THIS LINE BUT

        db.insert(TABLE_USER, null, values);
        db.close();
    }

    /**
     * NOTE RE BUT ABOVE
     *
     * Ideally you let SQLite autogenerate an id, rather than tell SQLite what it would be.
     * 
     * I also suggest to use COL_USER_ID whenever referring to the User_ID column as
     *  a user doesn't have a foreign player id a user may have many players or none
     *  so even though the resolution of the constants is the same it could be confusing
     *
     */

    // Adding a player to players table

    public void addPlayer(Player player) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        // Table 2 : Add players info
        values.put(COLUMN_PLAYER_NAME, player.getPlayerName());
        values.put(COLUMN_PLAYER_AGE, player.getPlayerAge());
        values.put(COLUMN_PLAYER_HEIGHT, player.getPlayerHeight());
        values.put(COLUMN_PLAYER_WEIGHT, player.getPlayerWeight());
        values.put(FOREIGN_PLAYER_ID, player.getForeignKey());

        db.insert(TABLE_PLAYERS, null, values);
        db.close();

    }

    // Checking the users email
    public boolean checkUser(String email){
        String[] columns = {
                COLUMN_USER_ID

        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = { email };

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }
        return false;
    }

    //
    public String getColumnUserName(String email){

        String user = "";
        String[] columns = {
                COLUMN_USER_ID

        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = { email };

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();


        if (cursor.moveToFirst()) // data?{
            //user =  cursor.getString(cursor.getColumnIndex("EMAIL")); //<<<<<<<<< NOT A COLUMN NAME
            user = cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID));

        cursor.close(); // that's important too, otherwise you're gonna leak cursors
        db.close();

        if (cursorCount > 0){
            return user;
        }
        return user;
    }

    // Checking the users email and password
    public boolean checkUser(String email, String password){
        String[] columns = {
                COLUMN_USER_ID

        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " =?";
        String[] selectionArgs = { email, password };

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }
        return false;
    }
}
