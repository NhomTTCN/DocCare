package com.example.doccare.Fragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.doccare.Model.LoginResponse;
import com.example.doccare.Network.ApiService;
import com.example.doccare.R;
import com.example.doccare.ViewModel.InfoViewModel;
import com.example.doccare.ViewModel.TokenViewModel;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    private static final int RESULT_OK = 1;
    EditText edt_name, edt_phone, edt_career;
    CircleImageView avatar;
    TextView tv_birthday;
    RadioGroup rd_gender;
    ImageButton img_birthday;
    Button btn_save;
    Uri m_uri;
    ProgressDialog progressDialog;
    TokenViewModel tokenViewModel;
    InfoViewModel infoViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_profile, container, false);

        mapping(view);

        tokenViewModel = new ViewModelProvider(getActivity()).get(TokenViewModel.class);
        infoViewModel = new ViewModelProvider(getActivity()).get(InfoViewModel.class);

        progressDialog = new ProgressDialog(getContext());

        // gettoken
        //get latest data
        getLatestData();

        return view;
    }

    private void getLatestData() {
        //  Uri.parse("file://" + filePath)
        LoginResponse loginResponse = infoViewModel.getLoginResponse();
        edt_name.setText(loginResponse.getInfo().getName());
        edt_phone.setText(loginResponse.getInfo().getPhone());
        edt_career.setText(loginResponse.getInfo().getCareer());
        tv_birthday.setText(loginResponse.getInfo().getBirthday());

        if(loginResponse.getInfo().getAccount().getAvatar()!=null) {
            Glide.with(getContext())
                    .load(loginResponse.getInfo().getAccount().getAvatar())
                    .into(avatar);
        }
    }

    private void mapping(View view) {

        edt_name = view.findViewById(R.id.edt_edit_name);
        edt_phone = view.findViewById(R.id.edt_edit_phone);
        edt_career = view.findViewById(R.id.edt_edit_career);
        avatar = view.findViewById(R.id.img_edit_avatar);
        tv_birthday = view.findViewById(R.id.tv_edit_birthday);
        rd_gender = view.findViewById(R.id.radio_edit_gender);
        img_birthday = view.findViewById(R.id.btn_edit_birthday);
        btn_save = view.findViewById(R.id.btn_edit_save);
        avatar.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        img_birthday.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_edit_avatar:
                chooseimagefromdevice();
                Log.d("click avatar", "click avatar");
                break;
            case R.id.btn_edit_save:
                Log.d("click save", "click save");
                saveownedit();
                break;
            case R.id.btn_edit_birthday:
                pickbirthday();
                break;

        }
    }

    private void saveownedit() {
        String accesstoken = "Bearer " + tokenViewModel.getAccessToken();
        if (!checkblank())
            return;
        //multipart - data
        RequestBody requestBody_name = RequestBody.create(MediaType.parse("multipart/form-data"), edt_name.getText().toString());
        RequestBody requestBody_phone = RequestBody.create(MediaType.parse("multipart/form-data"), edt_phone.getText().toString());
        RequestBody requestBody_birthday = RequestBody.create(MediaType.parse("multipart/form-data"), tv_birthday.getText().toString());
        RequestBody requestBody_gender = RequestBody.create(MediaType.parse("multipart/form-data"), getGender());
        RequestBody requestBody_career = RequestBody.create(MediaType.parse("multipart/form-data"), edt_career.getText().toString());
        MultipartBody.Part multiPartBody_avatar = null;

        if (m_uri != null) {
            File file = new File(getRealPathFromURI(m_uri));
            //File file = new File( "1ezdewycX/PNG_transparency_demonstration_1.png");
            //File file = new File(RealPathUtil.getRealPath(getContext(), m_uri));
            RequestBody requestBody_avatar = RequestBody.create(MediaType.parse("image/jpg"), file);
            multiPartBody_avatar = MultipartBody.Part.createFormData("avatar", file.getName(), requestBody_avatar);
        }

        progressDialog.setMessage(getString(R.string.please_wait));
        progressDialog.show();
        ApiService.apiService.editownprofile(accesstoken, requestBody_name, requestBody_phone, requestBody_birthday, requestBody_gender, requestBody_career, multiPartBody_avatar).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialog.cancel();
                if (response.message().equals("OK")) {
                    Log.d("edit_okela", response.body().toString());
                    // update data for viewmodel
                    infoViewModel.getLoginResponse().getInfo().setName(edt_name.getText().toString());
                    infoViewModel.getLoginResponse().getInfo().setPhone(edt_phone.getText().toString());
                    infoViewModel.getLoginResponse().getInfo().setCareer(edt_career.getText().toString());
                    infoViewModel.getLoginResponse().getInfo().setBirthday(tv_birthday.getText().toString());
                    infoViewModel.getLoginResponse().getInfo().getAccount().setAvatar(getRealPathFromURI(m_uri));

                    Toast.makeText(requireContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                    //getlatestdata for Profile Fragment
                    getLatestData();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("fail_edit", t.getMessage().toString());
                progressDialog.cancel();
            }
        });
    }

    private String getGender() {
        if (rd_gender.getCheckedRadioButtonId() == R.id.radio_male)
            return "Male";
        return "Female";
    }

    private void pickbirthday() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1++;
                tv_birthday.setText(i + "-" + i1 + "-" + i2);

            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private boolean checkblank() {
        if (edt_name.getText().toString().isEmpty()) {
            Toast.makeText(getContext(),
                    R.string.first_nameisnotempty,
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        if (edt_phone.getText().toString().isEmpty()) {
            Toast.makeText(getContext(),
                    R.string.phoneisnotempty,
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if (tv_birthday.getText().toString().isEmpty()) {
            Toast.makeText(getContext(),
                    R.string.birthdayisnotempty,
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if (edt_career.getText().toString().isEmpty()) {
            Toast.makeText(getContext(),
                    R.string.careerisnotempty,
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    private void chooseimagefromdevice() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri chosenImageUri = data.getData();
        m_uri = chosenImageUri;
        Log.d("m_uri", m_uri.toString());

        Bitmap mBitmap = null;
        try {

            mBitmap = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), chosenImageUri);
            avatar.setImageBitmap(mBitmap);
            Log.d("bitmap", mBitmap.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getRealPathFromURI(Uri contentURI) {
        String result=infoViewModel.getLoginResponse().getInfo().getAccount().getAvatar();
        if(contentURI!=null) {
            Cursor cursor = getActivity().getContentResolver().query(contentURI, null, null, null, null);
            if (cursor == null) { // Source is Dropbox or other similar local file path
                result = contentURI.getPath();
            } else {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                result = cursor.getString(idx);
                cursor.close();
            }
        }
        return result;
    }
}
