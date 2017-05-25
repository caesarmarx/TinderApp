package jxgi.com.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jxgi.com.R;
import jxgi.com.consts.CommonConsts;
import jxgi.com.util.SharedPrefManager;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_contractor)
    void onClickBtnContractor() {
        SharedPrefManager.getInstance(this).saveUserType(CommonConsts.USER_TYPE_CONTRACTOR);
        Intent intent = new Intent(FirstActivity.this, SignInActivity.class);
        startActivity(intent);

        finish();
    }

    @OnClick(R.id.btn_client)
    void onClickBtnClient() {
        SharedPrefManager.getInstance(this).saveUserType(CommonConsts.USER_TYPE_CLIENT);
        Intent intent = new Intent(FirstActivity.this, SignInActivity.class);
        startActivity(intent);

        finish();
    }
}