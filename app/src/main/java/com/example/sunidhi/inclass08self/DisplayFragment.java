package com.example.sunidhi.inclass08self;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class DisplayFragment extends Fragment {

    TextView editTextName2, editTextEmail2, editTextDpt2, editTextMood2;
    ImageButton imageButtonName, imageButtonEmail, imageButtonDpt, imageButtonMood;

    String name, email, dpt;
    int mood;

    public DisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate( R.layout.fragment_display, container, false );
    }



    InterfaceFragmentDisplay inter;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach( activity );
        Log.d( "demo", "DisplayFragment onAttach" );
        try{
            inter = (InterfaceFragmentDisplay) activity;
        }
        catch (ClassCastException c){
            throw new ClassCastException( "Interface should be implemented" );
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );

        editTextName2 = getActivity().findViewById( R.id.editTextName2 );
        editTextEmail2 = getActivity().findViewById( R.id.editTextEmail2 );
        editTextDpt2 = getActivity().findViewById( R.id.editTextDpt2 );
        editTextMood2 = getActivity().findViewById( R.id.editTextMood2 );

        imageButtonName = getActivity().findViewById( R.id.imageButtonName );
        imageButtonEmail = getActivity().findViewById( R.id.imageButtonEmail );
        imageButtonDpt = getActivity().findViewById( R.id.imageButtonDpt );
        imageButtonMood = getActivity().findViewById( R.id.imageButtonMood );

        Bundle b = getArguments();
        final Student displayStudent = (Student)getArguments().getSerializable("student");

        Log.d( "demo", "displayStudent.toString = " + displayStudent.toString() );

        editTextName2.setText( displayStudent.getName() );
        editTextEmail2.setText( displayStudent.getEmail() );
        editTextDpt2.setText( displayStudent.getDepartment() );
        editTextMood2.setText( displayStudent.getMood() + "% Positive" );

        imageButtonName.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inter.methodInterfaceFragmentDisplay(displayStudent, 1);
            }
        } );

        imageButtonEmail.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inter.methodInterfaceFragmentDisplay(displayStudent, 2);
            }
        } );

        imageButtonDpt.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inter.methodInterfaceFragmentDisplay(displayStudent, 3);
            }
        } );

        imageButtonMood.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inter.methodInterfaceFragmentDisplay(displayStudent, 4);
            }
        } );
    }

    public interface InterfaceFragmentDisplay {
        // TODO: Update argument type and name
        public void methodInterfaceFragmentDisplay(Student student, int code);
    }
}
