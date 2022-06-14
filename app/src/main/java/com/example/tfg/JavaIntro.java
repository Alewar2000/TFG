package com.example.tfg;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.github.appintro.AppIntro2;
import com.github.appintro.AppIntroFragment;
import com.github.appintro.AppIntroPageTransformerType;


public class JavaIntro extends AppIntro2 {


    @Override
    protected void onPageSelected(int position) {
        super.onPageSelected(position);
    }

    @Override
    protected int getLayoutId() {
        return super.getLayoutId();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        addSlide(AppIntroFragment.newInstance("Bienvenido a EcoEco",
                "",
                R.drawable.logo2,
                getResources().getColor(R.color.green)
        ));

        addSlide(AppIntroFragment.newInstance("Compra todo tipo de productos",
                "Totalmente eco-friendly",
                R.drawable.intro1,
                getResources().getColor(R.color.yellow)

        ));

        addSlide(AppIntroFragment.newInstance(
                "Vende tus productos de una manera sencilla y rapida",
                "Pública y personaliza tus productos, con un control sencillo de estos",
                R.drawable.intro2,
                getResources().getColor(R.color.colorPrimary)
        ));


        // Fade Transition
        setTransformer(AppIntroPageTransformerType.Zoom.INSTANCE);

        // Show/hide status bar
        showStatusBar(false);

        //Speed up or down scrolling
        setScrollDurationFactor(3);

        //Enable the color "fade" animation between two slides (make sure the slide implements SlideBackgroundColorHolder)
        setColorTransitionsEnabled(true);

        //Prevent the back button from exiting the slides
        setSystemBackButtonLocked(true);

        //Activate wizard mode (Some aesthetic changes)
        setWizardMode(true);

        //Show/hide skip button
        setSkipButtonEnabled(true);

        //Enable/disable immersive mode (no status and nav bar)
        setImmersive(true);

        //Enable/disable page indicators
        setIndicatorEnabled(true);

        //Dhow/hide ALL buttons
        setButtonsEnabled(true);
    }

    @Override
    protected void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
    }

    @Override
    protected void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
    }
}
