package com.example.imagesearchapp.ui.view;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.imagesearchapp.R;
import com.example.imagesearchapp.domain.comment.model.Comment;
import com.example.imagesearchapp.ui.viewmodel.ImageDescriptionViewModel;
import com.example.imagesearchapp.utils.Constants;
import com.example.imagesearchapp.utils.Status;

import java.util.ArrayList;
import java.util.List;

import kotlin.Lazy;

import static org.koin.android.viewmodel.compat.ViewModelCompat.viewModel;

public class PhotoDescriptionActivity extends AppCompatActivity {
    private Lazy<ImageDescriptionViewModel> viewModelLazy = viewModel(this, ImageDescriptionViewModel.class);
    private ImageView imageViewDesc;
    private EditText titleEditText;
    private String imageId;
    private String title;
    private String imageUrl;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_description);
        extractDataFromIntent();
        setupUI();
        setupObserver();
    }

    private void setupObserver() {
        viewModelLazy.getValue().getPhotoEntity().observe(this, listResource -> {
            if (listResource.component1() == Status.SUCCESS) {
                if (listResource.component2() != null) {
                    titleEditText.setText(listResource.component2().component2());
                }
            } else if (listResource.component1() == Status.ERROR) {
                Toast.makeText(this, listResource.component3(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setupUI() {
        imageViewDesc = findViewById(R.id.imageViewDesc);
        titleEditText = findViewById(R.id.title);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Glide.with(this)
                .load(imageUrl)
                .into(imageViewDesc);
        viewModelLazy.getValue().fetchStoredComments(imageId);
    }

    private void extractDataFromIntent() {
        title = getIntent().getStringExtra(Constants.TITLE_KEY);
        imageUrl = getIntent().getStringExtra(Constants.IMAGE_KEY);
        imageId = getIntent().getStringExtra(Constants.ID_KEY);
    }

    public void onSubmit(View view) {
        if (titleEditText.getText().toString().length() > 0) {
            List list = new ArrayList<Comment>();
            list.add(new Comment(imageId, titleEditText.getText().toString()));
            viewModelLazy.getValue().insertComment(list);
        } else {
            Toast.makeText(this, "Please enter comment", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
