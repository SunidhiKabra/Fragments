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
import android.widget.TextView;
import android.widget.Toast;


public class EditFragment extends Fragment {
    public EditFragment() {
        // Required empty public constructor
    }

    InterfaceEditDisplay inter;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach( activity );
        Log.d( "demo", "Edit Fragment onAttach" );
        try{
            inter = (InterfaceEditDisplay) activity;
        }
        catch (ClassCastException c){
            throw new ClassCastException( "Interface should be implemented" );
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_edit, container, false );
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );

        final EditText editTextName, editTextEmail;
        final TextView textViewDeptLabel, textViewMoodLabel;
        final RadioGroup radioGroup;
        RadioButton rbSIS, rbCS, rbBio, rbOthers;
        final SeekBar seekBar;
        Button buttonSave;

        getActivity().setTitle( "Edit Information" );
        editTextName = getActivity().findViewById( R.id.editTextName );
        editTextEmail = getActivity().findViewById( R.id.editTextEmail );
        textViewDeptLabel = getActivity().findViewById( R.id.textViewDeptLabel);
        textViewMoodLabel = getActivity().findViewById( R.id.textViewMoodLabel );
        radioGroup = getActivity().findViewById( R.id.radioGroup );
        rbSIS = getActivity().findViewById( R.id.rbSIS );
        rbCS = getActivity().findViewById( R.id.rbCS );
        rbBio = getActivity().findViewById( R.id.rbBio );
        rbOthers = getActivity().findViewById( R.id.rbOthers );
        seekBar = getActivity().findViewById( R.id.seekBar );
        buttonSave = getActivity().findViewById( R.id.buttonSave );

        editTextName.setVisibility( View.INVISIBLE );
        editTextEmail.setVisibility( View.INVISIBLE );
        textViewDeptLabel.setVisibility( View.INVISIBLE  );
        textViewMoodLabel.setVisibility( View.INVISIBLE );
        radioGroup.setVisibility( View.INVISIBLE );
        seekBar.setVisibility( View.INVISIBLE );

        buttonSave.setVisibility( View.VISIBLE );


        Bundle b = getArguments();
        final Student editStudent;
        final int reqCode = getArguments().getInt( "reqCode" );
        editStudent = (Student)getArguments().getSerializable("EditStudent");

        editTextName.setText(editStudent.getName());
        editTextEmail.setText(editStudent.getEmail());
        seekBar.setProgress( editStudent.getMood() );

        String department = editStudent.getDepartment();

        if(department.matches( "CS" ))
        {
            rbCS.setChecked(true);
        }
        if(department.matches( "SIS" ))
        {
            rbSIS.setChecked( true );
        }
        if(department.matches( "BIO" ))
        {
            rbBio.setChecked( true );
        }
        if(department.matches( "Others" ))
        {
            rbOthers.setChecked( true );
        }

        if (reqCode == 1){
            editTextName.setVisibility( View.VISIBLE );
            editStudent.setName( editTextName.getText().toString() );
        }
        if (reqCode == 2){
            editTextEmail.setVisibility( View.VISIBLE );
            editStudent.setEmail( editTextEmail.getText().toString() );
        }
        if (reqCode == 3){
            textViewDeptLabel.setVisibility( View.VISIBLE );
            radioGroup.setVisibility( View.VISIBLE );

            int id = radioGroup.getCheckedRadioButtonId();
            RadioButton radioButton = getActivity().findViewById( id );
            String rbText = radioButton.getText().toString();

            editStudent.setDepartment( rbText );
        }
        if (reqCode == 4){
            textViewMoodLabel.setVisibility( View.VISIBLE );
            seekBar.setVisibility( View.VISIBLE );
            editStudent.setMood( seekBar.getProgress() );
        }

        buttonSave.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reqCode == 1){
                    editStudent.setName( editTextName.getText().toString() );
                    inter.methodInterfaceEditDisplay(editStudent);
                }
                else if (reqCode == 2){
                    if(android.util.Patterns.EMAIL_ADDRESS.matcher(editTextEmail.getText()).matches())
                    {
                        editStudent.setEmail( editTextEmail.getText().toString() );
                        inter.methodInterfaceEditDisplay(editStudent);
                    }
                    else
                    {
                        Toast.makeText( getActivity(), "Enter Valid Email", Toast.LENGTH_SHORT ).show();
                    }
                }
                else if (reqCode == 3){
                    int id = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = getActivity().findViewById( id );
                    String rbText = radioButton.getText().toString();
                    editStudent.setDepartment( rbText );
                    inter.methodInterfaceEditDisplay(editStudent);
                }
                else if (reqCode == 4){
                    editStudent.setMood( seekBar.getProgress() );
                    inter.methodInterfaceEditDisplay(editStudent);
                }
//                goToDisplayObj.goToDisplay(editStudent);
            }
        } );
    }

    public interface InterfaceEditDisplay {
        // TODO: Update argument type and name
        void methodInterfaceEditDisplay(Student student);
    }
}
