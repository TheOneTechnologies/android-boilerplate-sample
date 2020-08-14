package com.theonetech.android.presentation.view.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;
import com.theonetech.android.R;
import com.theonetech.android.databinding.ActivityHomeBinding;
import com.theonetech.android.domain.utils.AlertDialogHelper;
import com.theonetech.android.domain.utils.SharedPrefUtils;
import com.theonetech.android.domain.utils.Utils;
import com.theonetech.android.presentation.baseclass.BaseActivity;
import com.theonetech.android.presentation.dialog.ImagePickerDialog;
import com.theonetech.android.presentation.view.fragment.ListFragment;
import com.theonetech.android.presentation.view.fragment.PaginationListFragment;

import java.io.IOException;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityHomeBinding binding = null;
    private Fragment mFragment;
    private TextView tvToolbarTitle;
    private ImageView imageNavHeader;

    private int GALLERY = 1, CAMERA = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        initialization();

    }

    private void initialization() {
        setToolbar();
        setDrawer();
        setListener();
    }

    private void setToolbar() {
        ImageView imageView = findViewById(R.id.ivLeft);
        tvToolbarTitle = findViewById(R.id.tvTitle);
        imageView.setImageResource(R.drawable.ic_menu);
        imageView.setOnClickListener(v -> binding.drawer.openDrawer(GravityCompat.START));
    }

    private void setDrawer() {

        mFragment = new PaginationListFragment();
        replaceFragment(mFragment);

    }

    private void setListener() {
        binding.navView.setNavigationItemSelectedListener(this);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        imageNavHeader = headerView.findViewById(R.id.nav_header_imageView);

        imageNavHeader.setOnClickListener(v -> {

            String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            Permissions.check(this, permissions, null, null, new PermissionHandler() {
                @Override
                public void onGranted() {
                    // do your task.
                    showPictureDialog();
                }
            });


        });
    }

    private void showPictureDialog() {

        ImagePickerDialog imagePickerDialog = new ImagePickerDialog(this, new ImagePickerDialog.OnItemClickListener() {
            @Override
            public void onCameraClick(View view, ImagePickerDialog dialog) {
                takePhotoFromCamera();
                dialog.dismiss();

            }

            @Override
            public void onGalleryClick(View view, ImagePickerDialog dialog) {
                choosePhotoFromGallery();
                dialog.dismiss();
            }
        });
        imagePickerDialog.show();


    }

    public void choosePhotoFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY);

    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == this.RESULT_CANCELED) {
            return;
        }

        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {

                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = Utils.saveImage(bitmap, this);
                    Toast.makeText(HomeActivity.this, getString(R.string.str_image_save), Toast.LENGTH_SHORT).show();
                    imageNavHeader.setImageBitmap(bitmap);

                } catch (IOException e) {

                    e.printStackTrace();
                    Toast.makeText(HomeActivity.this, getString(R.string.str_failed), Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {

            Bitmap thumbnail = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
            imageNavHeader.setImageBitmap(thumbnail);
            Utils.saveImage(thumbnail, this);
            Toast.makeText(HomeActivity.this, getString(R.string.str_failed), Toast.LENGTH_SHORT).show();

        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.nav_item_list_pagination:
                mFragment = new PaginationListFragment();
                replaceFragment(mFragment);
                break;


            case R.id.nav_item_list_image:

                mFragment = new ListFragment();
                replaceFragment(mFragment);
                break;

            case R.id.nav_log_out:

                AlertDialogHelper.showAlertDialog(HomeActivity.this, R.drawable.ic_alert, getString(R.string.str_logout), getString(R.string.str_are_you_sure_want_to_logout), getString(R.string.str_yes), getString(R.string.str_no), view -> {
                    SharedPrefUtils.removePre(HomeActivity.this);
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    finish();
                }, false);
                break;

        }
        binding.drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(Fragment mFragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(binding.container.getId(), mFragment, "").commit();
    }

    private void addFragment(Fragment mFragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(binding.container.getId(), mFragment, "").commit();
    }

    public void setToolbarTitle(String title) {
        tvToolbarTitle.setText(title);
    }
}
