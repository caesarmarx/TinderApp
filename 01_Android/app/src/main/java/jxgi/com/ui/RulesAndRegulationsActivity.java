package jxgi.com.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jxgi.com.R;
import jxgi.com.consts.CommonConsts;
import jxgi.com.util.SharedPrefManager;

public class RulesAndRegulationsActivity extends AppCompatActivity {

    @Bind(R.id.btn_next)
    Button btnNext;
    @Bind(R.id.chk_rules_regulations)
    CheckBox chkRules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.rules_and_regulations);

        chkRules.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    btnNext.setEnabled(true);
                    btnNext.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_default));
                } else {
                    btnNext.setEnabled(false);
                    btnNext.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg_disable));
                }
            }
        });
    }

    @OnClick(R.id.btn_next)
    void onClickBtnNext() {
        int usertype = SharedPrefManager.getInstance(this).getUserType();
        if(usertype == CommonConsts.USER_TYPE_CLIENT) {
            Intent intent = new Intent(RulesAndRegulationsActivity.this, ClientHomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        } else if (usertype == CommonConsts.USER_TYPE_CONTRACTOR) {
            Intent intent = new Intent(RulesAndRegulationsActivity.this, ContractorHomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}