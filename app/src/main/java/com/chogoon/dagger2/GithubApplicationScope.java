package com.chogoon.dagger2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;
import javax.inject.Singleton;

import dagger.Provides;

/**
 * Created by chogoon on 2017-05-31.
 */

@Scope // == @Singleton
@Retention(RetentionPolicy.CLASS)
public @interface GithubApplicationScope {
}
