package com.example.conductorexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;

public class InputsController extends Controller {

    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflater.inflate(R.layout.controller_inputs, container, false);

        // Views
        EditText et1 = view.findViewById(R.id.et1);
        EditText et2 = view.findViewById(R.id.et2);
        Button btn = view.findViewById(R.id.btn);

        btn.setOnClickListener(v -> doCalculation(Integer.valueOf(et1.getText().toString().trim()), Integer.valueOf(et2.getText().toString().trim())));

        return view;
    }

    private void doCalculation(Integer valueOf1, Integer valueOf2) {
        // Calculations
        int sum = valueOf1 + valueOf2;
        int product = valueOf1 * valueOf2;
        int difference, quotient;
        if (valueOf1 > valueOf2) {
            difference = valueOf1 - valueOf2;
            quotient = valueOf1 / valueOf2;
        } else {
            difference = valueOf2 - valueOf1;
            quotient = valueOf2 / valueOf1;
        }

        // Send to output Controller
        RouterTransaction routerTransaction = RouterTransaction
                .with(new OutputController())
                .popChangeHandler(new HorizontalChangeHandler())
                .pushChangeHandler(new HorizontalChangeHandler());

        ((MainActivity) getActivity()).router.pushController(routerTransaction);
    }


}
