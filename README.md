# so541157532
Player thinggy core DB code

Currently this just tests the code DB methods

Note the only real issue is that in the **getColumnUserName** method the column name was hard-coded as "EMAIL" and was thus
causing a -1 index issue. That is the **getColumnIndex** method returns -1 if the column name is not found inn the Cursor 
and thus when an attempt is made to get data from the column at offset -1, then a hissy fit is thrown (aka exception).

The fix was to change

            //user =  cursor.getString(cursor.getColumnIndex("EMAIL")); //<<<<<<<<< NOT A COLUMN NAME
            user = cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID));
            
When the App is first run the following output is expected in the log :-

        2019-01-11 06:34:53.592 3979-3979/so54115753.so54115753 I/System.out: >>>>> Dumping cursor android.database.sqlite.SQLiteCursor@6967caf
        2019-01-11 06:34:53.594 3979-3979/so54115753.so54115753 I/System.out: 0 {
        2019-01-11 06:34:53.595 3979-3979/so54115753.so54115753 I/System.out:    User_name=USER0
        2019-01-11 06:34:53.595 3979-3979/so54115753.so54115753 I/System.out:    User_id=0
        2019-01-11 06:34:53.595 3979-3979/so54115753.so54115753 I/System.out:    User_email=USER0@USER0.email.com
        2019-01-11 06:34:53.595 3979-3979/so54115753.so54115753 I/System.out:    User_password=password
        2019-01-11 06:34:53.595 3979-3979/so54115753.so54115753 I/System.out: }
        2019-01-11 06:34:53.595 3979-3979/so54115753.so54115753 I/System.out: 1 {
        2019-01-11 06:34:53.595 3979-3979/so54115753.so54115753 I/System.out:    User_name=USER1
        2019-01-11 06:34:53.595 3979-3979/so54115753.so54115753 I/System.out:    User_id=1
        2019-01-11 06:34:53.595 3979-3979/so54115753.so54115753 I/System.out:    User_email=USER1@USER1.email.com
        2019-01-11 06:34:53.595 3979-3979/so54115753.so54115753 I/System.out:    User_password=password
        2019-01-11 06:34:53.595 3979-3979/so54115753.so54115753 I/System.out: }
        2019-01-11 06:34:53.595 3979-3979/so54115753.so54115753 I/System.out: 2 {
        2019-01-11 06:34:53.595 3979-3979/so54115753.so54115753 I/System.out:    User_name=USER2
        2019-01-11 06:34:53.595 3979-3979/so54115753.so54115753 I/System.out:    User_id=2
        2019-01-11 06:34:53.595 3979-3979/so54115753.so54115753 I/System.out:    User_email=USER2@USER2.email.com
        2019-01-11 06:34:53.595 3979-3979/so54115753.so54115753 I/System.out:    User_password=password
        2019-01-11 06:34:53.595 3979-3979/so54115753.so54115753 I/System.out: }
        2019-01-11 06:34:53.595 3979-3979/so54115753.so54115753 I/System.out: <<<<<
        2019-01-11 06:34:53.598 3979-3979/so54115753.so54115753 I/System.out: >>>>> Dumping cursor android.database.sqlite.SQLiteCursor@46dd39a
        2019-01-11 06:34:53.598 3979-3979/so54115753.so54115753 I/System.out: 0 {
        2019-01-11 06:34:53.598 3979-3979/so54115753.so54115753 I/System.out:    User_name=USER0
        2019-01-11 06:34:53.598 3979-3979/so54115753.so54115753 I/System.out:    User_id=0
        2019-01-11 06:34:53.598 3979-3979/so54115753.so54115753 I/System.out:    User_email=USER0@USER0.email.com
        2019-01-11 06:34:53.598 3979-3979/so54115753.so54115753 I/System.out:    User_password=password
        2019-01-11 06:34:53.598 3979-3979/so54115753.so54115753 I/System.out: }
        2019-01-11 06:34:53.598 3979-3979/so54115753.so54115753 I/System.out: 1 {
        2019-01-11 06:34:53.598 3979-3979/so54115753.so54115753 I/System.out:    User_name=USER1
        2019-01-11 06:34:53.598 3979-3979/so54115753.so54115753 I/System.out:    User_id=1
        2019-01-11 06:34:53.598 3979-3979/so54115753.so54115753 I/System.out:    User_email=USER1@USER1.email.com
        2019-01-11 06:34:53.598 3979-3979/so54115753.so54115753 I/System.out:    User_password=password
        2019-01-11 06:34:53.598 3979-3979/so54115753.so54115753 I/System.out: }
        2019-01-11 06:34:53.598 3979-3979/so54115753.so54115753 I/System.out: 2 {
        2019-01-11 06:34:53.598 3979-3979/so54115753.so54115753 I/System.out:    User_name=USER2
        2019-01-11 06:34:53.598 3979-3979/so54115753.so54115753 I/System.out:    User_id=2
        2019-01-11 06:34:53.598 3979-3979/so54115753.so54115753 I/System.out:    User_email=USER2@USER2.email.com
        2019-01-11 06:34:53.598 3979-3979/so54115753.so54115753 I/System.out:    User_password=password
        2019-01-11 06:34:53.598 3979-3979/so54115753.so54115753 I/System.out: }
        2019-01-11 06:34:53.599 3979-3979/so54115753.so54115753 I/System.out: <<<<<
        
 The above shows that the 3 users have been added (twice), then :-    
 
        2019-01-11 06:34:53.602 3979-3979/so54115753.so54115753 D/USERTESTING: OK - User with email notauser@noemail.comnot found as expected.
        2019-01-11 06:34:53.606 3979-3979/so54115753.so54115753 D/USERTESTING: OK - User with email USER2@USER2.email.com found as expected
        2019-01-11 06:34:53.609 3979-3979/so54115753.so54115753 D/USERTESTING: Username for user notauser@noemail.com is 
        2019-01-11 06:34:53.612 3979-3979/so54115753.so54115753 D/USERTESTING: Username for user USER2@USER2.email.com is2
        2019-01-11 06:34:53.616 3979-3979/so54115753.so54115753 D/USERTESTING: OK - User with email notauser@noemail.comnot found as expected.
        2019-01-11 06:34:53.619 3979-3979/so54115753.so54115753 D/USERTESTING: OK - User with email USER2@USER2.email.com found as expected (password matched)
 The above are the results from using the checkUser methods 
        
        2019-01-11 06:34:53.626 3979-3979/so54115753.so54115753 E/SQLiteDatabase: Error inserting Player_name=Harold Player_age=20 User_id=1000 Player_height=120 Player_weight=65
            android.database.sqlite.SQLiteConstraintException: FOREIGN KEY constraint failed (code 787 SQLITE_CONSTRAINT_FOREIGNKEY)
                at android.database.sqlite.SQLiteConnection.nativeExecuteForLastInsertedRowId(Native Method)
                at android.database.sqlite.SQLiteConnection.executeForLastInsertedRowId(SQLiteConnection.java:796)
                at android.database.sqlite.SQLiteSession.executeForLastInsertedRowId(SQLiteSession.java:788)
                at android.database.sqlite.SQLiteStatement.executeInsert(SQLiteStatement.java:86)
                at android.database.sqlite.SQLiteDatabase.insertWithOnConflict(SQLiteDatabase.java:1564)
                at android.database.sqlite.SQLiteDatabase.insert(SQLiteDatabase.java:1433)
                at so54115753.so54115753.DatabaseHelper.addPlayer(DatabaseHelper.java:126)
                at so54115753.so54115753.MainActivity.addSomePlayers(MainActivity.java:109)
                at so54115753.so54115753.MainActivity.onCreate(MainActivity.java:23)
                at android.app.Activity.performCreate(Activity.java:7136)
                at android.app.Activity.performCreate(Activity.java:7127)
                at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1271)
                at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2894)
                at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:3049)
                at android.app.servertransaction.LaunchActivityItem.execute(LaunchActivityItem.java:78)
                at android.app.servertransaction.TransactionExecutor.executeCallbacks(TransactionExecutor.java:108)
                at android.app.servertransaction.TransactionExecutor.execute(TransactionExecutor.java:68)
                at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1809)
                at android.os.Handler.dispatchMessage(Handler.java:106)
                at android.os.Looper.loop(Looper.java:193)
                at android.app.ActivityThread.main(ActivityThread.java:6680)
                at java.lang.reflect.Method.invoke(Native Method)
                at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:493)
                at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:858)
The above is an exception as an attemppt is made to add a player that references the User with a User_id of 1000 (), it doesn't result in a crash (RUNTIME EXCEPTION) though

Lastly the players that have been added are listed 

        2019-01-11 06:34:53.641 3979-3979/so54115753.so54115753 I/System.out: >>>>> Dumping cursor android.database.sqlite.SQLiteCursor@8685a2
        2019-01-11 06:34:53.641 3979-3979/so54115753.so54115753 I/System.out: 0 {
        2019-01-11 06:34:53.642 3979-3979/so54115753.so54115753 I/System.out:    Player_name=Anne
        2019-01-11 06:34:53.642 3979-3979/so54115753.so54115753 I/System.out:    Player_id=1
        2019-01-11 06:34:53.642 3979-3979/so54115753.so54115753 I/System.out:    Player_age=20
        2019-01-11 06:34:53.642 3979-3979/so54115753.so54115753 I/System.out:    Player_weight=65
        2019-01-11 06:34:53.642 3979-3979/so54115753.so54115753 I/System.out:    Player_height=120
        2019-01-11 06:34:53.642 3979-3979/so54115753.so54115753 I/System.out:    User_id=0
        2019-01-11 06:34:53.642 3979-3979/so54115753.so54115753 I/System.out: }
        2019-01-11 06:34:53.642 3979-3979/so54115753.so54115753 I/System.out: 1 {
        2019-01-11 06:34:53.642 3979-3979/so54115753.so54115753 I/System.out:    Player_name=Anne
        2019-01-11 06:34:53.642 3979-3979/so54115753.so54115753 I/System.out:    Player_id=2
        2019-01-11 06:34:53.642 3979-3979/so54115753.so54115753 I/System.out:    Player_age=20
        2019-01-11 06:34:53.643 3979-3979/so54115753.so54115753 I/System.out:    Player_weight=65
        2019-01-11 06:34:53.643 3979-3979/so54115753.so54115753 I/System.out:    Player_height=120
        2019-01-11 06:34:53.643 3979-3979/so54115753.so54115753 I/System.out:    User_id=1
        2019-01-11 06:34:53.643 3979-3979/so54115753.so54115753 I/System.out: }
        2019-01-11 06:34:53.643 3979-3979/so54115753.so54115753 I/System.out: <<<<<

Running the App again, unless the App's data is deleted, will shown the same less the foreign key exception.
