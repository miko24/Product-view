package com.example.product_view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.product_view.DB.DBHandler;
import com.example.product_view.databinding.FragmentFirstBinding;
import com.google.android.material.tabs.TabLayout;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    private EditText LabelInput;

    private EditText StockInput;

    private EditText PriceInput;

    private Button Btn_Insert;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        LabelInput = binding.ProductLabelInput;
        StockInput = binding.ProductStockInput;
        PriceInput = binding.ProductPriceInput;
        Btn_Insert = binding.btnAddProduct;
        DBHandler dbHandler = new DBHandler(getActivity());
        Btn_Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Label = LabelInput.getText().toString();
                Integer Stock = Integer.valueOf(StockInput.getText().toString());
                Float Price = Float.valueOf(PriceInput.getText().toString());
                if (Label.isEmpty() && Stock == null && Price == null ) {
                    Toast.makeText(getActivity(), "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }
                dbHandler.addNewProduct(Label,Stock,Price);
                Toast.makeText(getActivity(), "Course has been added.", Toast.LENGTH_SHORT).show();
                LabelInput.setText("");
                StockInput.setText("");
                PriceInput.setText("");
            }
        });

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}