package com.example.user.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {
    private Intent mRequestFileIntent;
    private ParcelFileDescriptor mInputPFD;
    Button a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a=(Button) findViewById(R.id.action);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { mRequestFileIntent = new Intent(Intent.ACTION_PICK);
                mRequestFileIntent.setType("document/pdf");}
                protected void requestFile(){
                    /**
                     * When the user requests a file, send an Intent to the
                     * server app.
                     * files.
                     */
                    startActivityForResult(mRequestFileIntent, 0);

                }

                public void onActivityResult(int requestCode, int resultCode,
                Intent returnIntent) {
                    // If the selection didn't work
                    if (resultCode != RESULT_OK) {
                        // Exit without doing anything else
                        return;
                    } else {
                        // Get the file's content URI from the incoming Intent
                        Uri returnUri = returnIntent.getData();
            /*
             * Try to open the file for "read" access using the
             * returned URI. If the file isn't found, write to the
             * error log and return.
             */
                        try {
                /*
                 * Get the content resolver instance for this context, and use it
                 * to get a ParcelFileDescriptor for the file.
                 */
                            mInputPFD = getContentResolver().openFileDescriptor(returnUri, "r");
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            Log.e("MainActivity", "File not found.");
                            return;
                        }
                        // Get a regular file descriptor for the file
                        FileDescriptor fd = mInputPFD.getFileDescriptor();

                    }
                }

        });
    }
    }

