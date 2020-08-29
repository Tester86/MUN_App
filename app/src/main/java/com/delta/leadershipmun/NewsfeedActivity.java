package com.delta.leadershipmun;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class NewsfeedActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST_CODE = 100;

    private Button btnChooseImage, btnUploadImage;
    private EditText fileName;
    private TextView showUploads;
    private ImageView firebaseImage;
    private Uri firebaseImageUri;
    private ProgressBar firebaseProgressBar;

    private StorageReference storageReference;
    private DatabaseReference databaseReference;

    private String downloadUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);

        btnChooseImage = (Button)findViewById(R.id.btnChooseImage);
        btnUploadImage = (Button)findViewById(R.id.btnUploadImage);
        fileName = (EditText)findViewById(R.id.fileName);
        showUploads = (TextView)findViewById(R.id.showUploads);
        firebaseImage = (ImageView)findViewById(R.id.firebaseImage);
        firebaseProgressBar = (ProgressBar)findViewById(R.id.firebaseProgressBar);

        storageReference = FirebaseStorage.getInstance().getReference("uploads");
        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");

        btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });

        showUploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFirebaseImageActivity();
            }
        });

    }

    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private String getDownloadUrl(){

        final StorageReference ref = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(firebaseImageUri));

        UploadTask uploadTask = ref.putFile(firebaseImageUri);

        Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if(!task.isSuccessful()) throw task.getException();
                return ref.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    downloadUrl = downloadUri.toString();
                }
            }
        });

        return downloadUrl;
    }

    private void uploadFile() {

        if(firebaseImageUri != null){
            StorageReference fileRef = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(firebaseImageUri));
            fileRef.putFile(firebaseImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    firebaseProgressBar.setProgress(0);
                                }
                            }, 500);

                            Toast.makeText(getApplicationContext(), "Upload successful", Toast.LENGTH_LONG).show();

                            FirebaseUpload upload = new FirebaseUpload(fileName.getText().toString().trim(),
                                    getDownloadUrl());

                            String uploadID = databaseReference.push().getKey();
                            databaseReference.child(uploadID).setValue(upload);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();

                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                    long progress = (100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    firebaseProgressBar.setProgress((int)progress);
                }
            });

        } else{
            Toast.makeText(this, "No file selected", Toast.LENGTH_LONG).show();
        }

    }

    private void openFirebaseImageActivity() {
        Intent intent = new Intent(this, FirebaseImageActivity.class);
        startActivity(intent);
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null){
            firebaseImageUri = data.getData();

            Picasso.get().load(firebaseImageUri).into(firebaseImage);
        }
    }
}

/*public class NewsfeedActivity extends AppCompatActivity {

    private RecyclerView newsRecyclerView;
    private ProgressBar newsProgressBar;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<ParseItem> parseItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);

        newsRecyclerView = (RecyclerView)findViewById(R.id.newsRecyclerView);
        newsProgressBar = (ProgressBar)findViewById(R.id.newsProgressBar);
        recyclerViewAdapter = new RecyclerViewAdapter(parseItems, this);

        newsRecyclerView.setHasFixedSize(true);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsRecyclerView.setAdapter(recyclerViewAdapter);

        Content content = new Content();
        content.execute();
    }

    private class Content extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            String title = "";
            String url = "https://www.nytimes.com/es/";
            Document doc;
            try {
                doc = Jsoup.connect(url).get();
                Elements data = doc.select("ol.css-11jjg.ekkqrpp2");

                for(int i = 0; i < data.size(); i++){

                    title = data.select("h2.css-l2vidh.e4e4i5l1")
                            .select("a")
                            .eq(i)
                            .text();

                    if(!title.equals("")) {
                        parseItems.add(new ParseItem(title));
                    } else {
                        title = data.select("h2.css-y3otqb.e134j7ei0")
                                .select("a")
                                .eq(i)
                                .text();

                        if(!title.equals("")) parseItems.add(new ParseItem(title));
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            newsProgressBar.setVisibility(View.VISIBLE);
            newsProgressBar.startAnimation(AnimationUtils.loadAnimation(NewsfeedActivity.this, R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            newsProgressBar.setVisibility(View.GONE);
            newsProgressBar.startAnimation(AnimationUtils.loadAnimation(NewsfeedActivity.this, R.anim.fade_out));
            recyclerViewAdapter.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}*/