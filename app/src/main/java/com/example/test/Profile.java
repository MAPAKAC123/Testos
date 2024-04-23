package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

public class Profile extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST_CODE = 1;
    private Uri selectedImageUri;
    private ImageView avatarImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        avatarImageView = findViewById(R.id.imageButton4);
    }
    public void onClickiMain(View view) {
        startActivity(new Intent(this, MainScreen.class));
    }
    public void onClickFavor(View view) { startActivity(new Intent(this, Favourites.class)); }
    public void onClickPhoto(View view) {
        Intent pickImageIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImageIntent, PICK_IMAGE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                selectedImageUri = data.getData();
                avatarImageView.setImageURI(selectedImageUri);
                avatarImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                avatarImageView.setAdjustViewBounds(true);

                uploadImageToFirebaseStorage();
            }
        }
    }

    private void uploadImageToFirebaseStorage() {
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        StorageReference imagesRef = storageRef.child("images/" + selectedImageUri.getLastPathSegment());
        imagesRef.putFile(selectedImageUri)
                .addOnSuccessListener(taskSnapshot -> {
                    // Изображение успешно загружено на Firebase Storage
                    imagesRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        String imageUrl = uri.toString();
                        saveImageUrlToDatabase(imageUrl);
                    });
                })
                .addOnFailureListener(e -> {
                    // Обработка ошибок при загрузке изображения
                });
    }

    private void saveImageUrlToDatabase(String imageUrl) {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("users");
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseRef.child(userId).child("avatarUrl").setValue(imageUrl);
    }
}
