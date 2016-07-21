package com.example.circlemenu;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btCenter;
    Button btTop;
    Button btBottom;
    Button btLeft;
    Button btRight;
    private boolean isShow = true;
    private boolean isShow2 = false;

    ImageButton btMenu;
    Button btNibp;
    Button btGlu;
    Button btTemp;
    Button btFat;
    Button btFhr;
    boolean isAnimating  =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 圆形菜单
        btCenter = (Button) findViewById(R.id.bt_center);
        btTop = (Button) findViewById(R.id.bt_top);
        btBottom = (Button) findViewById(R.id.bt_bottom);
        btLeft = (Button) findViewById(R.id.bt_left);
        btRight = (Button) findViewById(R.id.bt_right);
        btCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isShow2 = !isShow2;
                if(isShow2){
                    showMenu();
                }else{
                    hideMenu();
                }

            }
        });


        //list型菜单
         btMenu  = (ImageButton) findViewById(R.id.bt_menu);
         btNibp  = (Button) findViewById(R.id.bt_nibp);
         btGlu  = (Button) findViewById(R.id.bt_glu);
         btTemp  = (Button) findViewById(R.id.bt_temp);
         btFat  = (Button) findViewById(R.id.bt_fat);
         btFhr  = (Button) findViewById(R.id.bt_fhr);
        btNibp.setOnClickListener(this);
        btGlu.setOnClickListener(this);
        btTemp.setOnClickListener(this);
        btFat.setOnClickListener(this);
        btFhr.setOnClickListener(this);

        btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showOrHideMenu();
            }
        });
        showOrHideMenu(100);

    }



    //圆形菜单显示
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void showMenu(){
        AnimatorSet set = new AnimatorSet();
        PropertyValuesHolder vBig = PropertyValuesHolder.ofFloat("scaleX",0f,1f);
        PropertyValuesHolder vAlpha = PropertyValuesHolder.ofFloat("alpha",0.4f,1f);
        PropertyValuesHolder vTop = PropertyValuesHolder.ofFloat("translationY",0f,-400f);
        PropertyValuesHolder vBottom = PropertyValuesHolder.ofFloat("translationY",0f,400f);
        PropertyValuesHolder vLeft = PropertyValuesHolder.ofFloat("translationX",0f,-400f);
        PropertyValuesHolder vRight = PropertyValuesHolder.ofFloat("translationX",0f,400f);

        ObjectAnimator aTop  = ObjectAnimator.ofPropertyValuesHolder(btTop,vBig,vTop,vAlpha);
        ObjectAnimator aButtom  = ObjectAnimator.ofPropertyValuesHolder(btBottom,vBig,vBottom,vAlpha);
        ObjectAnimator aLeft  = ObjectAnimator.ofPropertyValuesHolder(btLeft,vBig,vLeft,vAlpha);
        ObjectAnimator aRight  = ObjectAnimator.ofPropertyValuesHolder(btRight,vBig,vRight,vAlpha);


        set.playTogether(aTop,aButtom,aLeft,aRight);
        set.setDuration(500);
        set.setInterpolator(new BounceInterpolator());
        set.start();

    }

    //圆形菜单隐藏
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void hideMenu(){

        PropertyValuesHolder vBig = PropertyValuesHolder.ofFloat("scale",1f,0f);
        PropertyValuesHolder vAlpha = PropertyValuesHolder.ofFloat("alpha",1f,0f);
        PropertyValuesHolder vTop = PropertyValuesHolder.ofFloat("translationY",-400f,0f);
        PropertyValuesHolder vBottom = PropertyValuesHolder.ofFloat("translationY",400f,0f);
        PropertyValuesHolder vLeft = PropertyValuesHolder.ofFloat("translationX",-400f,0f);
        PropertyValuesHolder vRight = PropertyValuesHolder.ofFloat("translationX",400f,0f);

        ObjectAnimator aTop  = ObjectAnimator.ofPropertyValuesHolder(btTop,vBig,vTop,vAlpha);
        ObjectAnimator aBottom  = ObjectAnimator.ofPropertyValuesHolder(btBottom,vBig,vBottom,vAlpha);
        ObjectAnimator aLeft  = ObjectAnimator.ofPropertyValuesHolder(btLeft,vBig,vLeft,vAlpha);
        ObjectAnimator aRight  = ObjectAnimator.ofPropertyValuesHolder(btRight,vBig,vRight,vAlpha);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(aTop,aBottom,aLeft,aRight);
        set.setDuration(500);
        set.setInterpolator(new AnticipateInterpolator());
        set.start();
    }




    //list型菜单隐藏或者显示
    public void showOrHideMenu(){
        showOrHideMenu(100);
    }
    public void showOrHideMenu(int time ){
        if(isAnimating){
            return;
        }
        isAnimating =true;
        AnimatorSet set = new AnimatorSet();
        PropertyValuesHolder vRotate;
        if(isShow){
            vRotate = PropertyValuesHolder.ofFloat("rotationX",0.0f,90.0f);
        }else{
            vRotate = PropertyValuesHolder.ofFloat("rotationX",90.0f,0.0f);
        }
        ObjectAnimator  aRotate1  = ObjectAnimator.ofPropertyValuesHolder(btNibp,vRotate);
        ObjectAnimator  aRotate2  = ObjectAnimator.ofPropertyValuesHolder(btGlu,vRotate);
        ObjectAnimator  aRotate3  = ObjectAnimator.ofPropertyValuesHolder(btTemp,vRotate);
        ObjectAnimator  aRotate4  = ObjectAnimator.ofPropertyValuesHolder(btFat,vRotate);
        ObjectAnimator  aRotate5  = ObjectAnimator.ofPropertyValuesHolder(btFhr,vRotate);
        if(isShow){
            set.playSequentially(aRotate1,aRotate2,aRotate3,aRotate4,aRotate5);
        }else{
            set.playSequentially(aRotate5,aRotate4,aRotate3,aRotate2,aRotate1);
        }
        set.setDuration(time);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                isShow = !isShow;
                isAnimating =false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        set.start();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_nibp :
            Toast.makeText(this,"血压",Toast.LENGTH_SHORT).show();
                showOrHideMenu();
            break;
            case R.id.bt_glu :
            Toast.makeText(this,"血糖",Toast.LENGTH_SHORT).show();
                showOrHideMenu();
            break;
            case R.id.bt_temp :
            Toast.makeText(this,"体温",Toast.LENGTH_SHORT).show();
                showOrHideMenu();
            break;
            case R.id.bt_fat :
            Toast.makeText(this,"体脂",Toast.LENGTH_SHORT).show();
                showOrHideMenu();
            break;
            case R.id.bt_fhr :
            Toast.makeText(this,"胎心",Toast.LENGTH_SHORT).show();
                showOrHideMenu();
            break;
        }
    }
}
