package com.pilltracker.movies.network

import okio.IOException

class NoInternetException (errorMessage: String?): IOException(errorMessage)