package com.example.varda.poolshop;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddItemDialogFragment extends AppCompatDialogFragment {

    private EditText nameEditText;
    private EditText quantityEditText;
    private AddItemDialogListener listener;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_add_list, null);

        builder.setView(view)
                .setTitle("Add Item")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = nameEditText.getText().toString();
                        String quantity = quantityEditText.getText().toString();
                        listener.applyTexts(name,quantity);
                    }
                });
        nameEditText =  view.findViewById(R.id.name_val);
        quantityEditText = view.findViewById(R.id.quantity_val);
        return builder.create();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            listener = (AddItemDialogListener)context;
        }catch (Exception e){
            throw new ClassCastException(context.toString() + "must implement AddItemDialog Listener");
        }

    }

    public interface AddItemDialogListener{
        void applyTexts(String item, String quantity);
    }
//    public static AddItemDialogFragment newInstance(String title) {
//        AddItemDialogFragment frag = new AddItemDialogFragment();
//        Bundle args = new Bundle();
//        args.putString("title", title);
//        frag.setArguments(args);
//        return frag;
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_add_list, container);
//
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        // Get field from view
//
//        // Fetch arguments from bundle and set title
//        String title = getArguments().getString("title", "Enter Name");
//        getDialog().setTitle(title);
//        // Show soft keyboard automatically and request focus to field
//        nameEditText.requestFocus();
//
//
//        String quantity = getArguments().getString("quantity","Enter quantity");
//        getDialog().getWindow().setSoftInputMode(
//                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);



//    }


}
