package com.example.sunidhi.inclass08self;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;


public class MainFragment extends Fragment {

    EditText nametext, emailtext;
    RadioGroup rgtext;
    RadioButton radioButton;
    SeekBar sbtext;
    Button buttonSubmit;

    String name, email, department;
    int selectedId, mood;

    public MainFragment() {
        // Required empty public constructor
    }

    InterfaceFragmentA inter;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach( activity );
        Log.d( "demo", "MainFragment onAttach" );
        try{
            inter = (InterfaceFragmentA) activity;
        }
        catch (ClassCastException c){
            throw new ClassCastException( "Interface should be implemented" );
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );

        nametext = getView().findViewById(R.id.labelName);
        emailtext = getView().findViewById(R.id.labelEmail);
        rgtext = getView().findViewById(R.id.radioGroup);
        sbtext = getView().findViewById(R.id.seekBarMood);
        buttonSubmit = getView().findViewById( R.id.buttonSubmit );

        buttonSubmit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nametext.getText().toString();
                email = emailtext.getText().toString();

                selectedId = rgtext.getCheckedRadioButtonId();
                radioButton = (RadioButton) getView().findViewById(selectedId);
                department = (String) radioButton.getText();

                mood = sbtext.getProgress();

                if (name.matches( "" ) || email.matches( "" ) || department.matches( "" ) || mood==0){
                    Toast.makeText( getActivity(), "Details required", Toast.LENGTH_SHORT ).show();
                }
                else{
                    Log.d("demo", "name" + name);
                    Log.d("demo", "email" + email);
                    Log.d("demo", "department" + department);
                    Log.d("demo", "mood" + mood);
                    Student student = new Student( name, email, department, mood );
                    Log.d( "demo", "Inside MainFragment, student = " + student.toString() );
                    inter.onFragmentInteraction( student );
                }
            }
        } );
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate( R.layout.fragment_main, container, false );
    }

    public interface InterfaceFragmentA {
        // TODO: Update argument type and name
        void onFragmentInteraction(Student student);
    }
}
