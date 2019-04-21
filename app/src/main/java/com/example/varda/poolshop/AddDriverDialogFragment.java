package com.example.varda.poolshop;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class AddDriverDialogFragment extends AppCompatDialogFragment {

    private EditText dnameEditText;
    private EditText daddressEditText;
    private EditText ddateEditText;
    private EditText dtimeEditText;
    private AddDriverDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_add_driver, null);

        builder.setView(view)
                .setTitle("Add Driver")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //String name = dnameEditText.getText().toString();
                        String address =daddressEditText.getText().toString();
                        String date = ddateEditText.getText().toString();
                        String time = dtimeEditText.getText().toString();
                        listener.applyTexts("Vardan",address,date,time);
                    }
                });
        //dnameEditText =  view.findViewById(R.id.dname_val);
        daddressEditText = view.findViewById(R.id.daddress_val);
        ddateEditText = view.findViewById(R.id.ddate_val);
        dtimeEditText = view.findViewById(R.id.dtime_val);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            listener = (AddDriverDialogListener) context;
        }catch (Exception e){
            throw new ClassCastException(context.toString() + "must implement AddItemDialog Listener");
        }
    }

    public interface AddDriverDialogListener {
        void applyTexts(String name, String address, String date, String time);
    }
}
