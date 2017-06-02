package com.chogoon.dagger2.screens.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.chogoon.dagger2.R;
import com.chogoon.dagger2.models.ItemData;
import com.squareup.picasso.Picasso;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("ViewConstructor")
public class RepoListItem extends FrameLayout {

    private final Picasso picasso;

    @BindView(R.id.user_avatar)
    ImageView avatarImage;

    @BindView(R.id.repo_name)
    TextView name;

    @BindView(R.id.repo_description)
    TextView description;

    @BindView(R.id.repo_stars)
    TextView stars;

    @BindView(R.id.repo_issues)
    TextView issues;

    @BindView(R.id.repo_forks)
    TextView forks;

    @BindView(R.id.repo_updated_at)
    TextView updatedAt;

//    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.fullDate();

    public RepoListItem(Context context, Picasso picasso) {
        super(context);
        this.picasso = picasso;
        inflate(getContext(), R.layout.list_item_repo, this);
        ButterKnife.bind(this);
    }

    public void setRepo(ItemData data) {
        Locale locale = getResources().getConfiguration().locale;

        name.setText(data.name);

        stars.setText(String.format(locale, "%d", 0));
        issues.setText(String.format(locale, "%d", 0));
        forks.setText(String.format(locale, "%d", 0));

//        updatedAt.setText(getResources()
//                .getString(R.string.last_pushed, DATE_TIME_FORMATTER.print(githubRepo.updatedAt)));

        picasso.load("http://image.goodchoice.kr" + data.imagePath)
                .placeholder(R.drawable.ic_person_black_24dp)
                .into(avatarImage);
    }
}
