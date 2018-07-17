package com.example.sunidhi.inclass08self;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MainFragment.InterfaceFragmentA, DisplayFragment.InterfaceFragmentDisplay, EditFragment.InterfaceEditDisplay{

    Student studentOfMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        getFragmentManager().beginTransaction().add( R.id.mainActivity, new MainFragment(), "mainFragment" ).commit();
    }

    @Override
    public void onFragmentInteraction(Student student) {
        studentOfMain = student;
        Bundle args = new Bundle(  );
        args.putSerializable( "student", studentOfMain );
        DisplayFragment displayFragment = new DisplayFragment();
        displayFragment.setArguments( args );
        getFragmentManager().beginTransaction()
                .replace( R.id.mainActivity, displayFragment, "displayFragment" )
                .addToBackStack( null )
                .commit();

    }

    @Override
    public void methodInterfaceFragmentDisplay(Student student, int code) {
        studentOfMain = student;
        Bundle args = new Bundle(  );
        args.putSerializable( "EditStudent", studentOfMain);
        EditFragment editFragment = new EditFragment();
        args.putInt( "reqCode", code );
        editFragment.setArguments( args );
        getFragmentManager().beginTransaction()
                .replace( R.id.mainActivity, editFragment, "editFragment" )
                .addToBackStack( null )
                .commit();
    }

    @Override
    public void methodInterfaceEditDisplay(Student student) {
        studentOfMain = student;
        Bundle args = new Bundle(  );
        args.putSerializable( "student", studentOfMain );
        DisplayFragment displayFragment = new DisplayFragment();
        displayFragment.setArguments( args );

        getFragmentManager().beginTransaction()
                .replace( R.id.mainActivity, displayFragment, "displayFragment" )
                .addToBackStack( null )
                .commit();
    }
}
