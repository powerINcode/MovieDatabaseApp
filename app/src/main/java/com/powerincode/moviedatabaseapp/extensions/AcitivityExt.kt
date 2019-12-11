package com.powerincode.moviedatabaseapp.extensions

import com.powerincode.core.ui.activity.BaseActivity
import com.powerincode.moviedatabaseapp.MovieApplication
import com.powerincode.moviedatabaseapp.di.ApplicationComponent

fun BaseActivity.getApplicationComponent(): ApplicationComponent = (application as MovieApplication).component