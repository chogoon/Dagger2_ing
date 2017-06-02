package com.chogoon.dagger2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by chogoon on 2017-05-31.
 */

@Scope // == @Singleton
@Retention(RetentionPolicy.CLASS)
public @interface GithubApplicationScope {
}
