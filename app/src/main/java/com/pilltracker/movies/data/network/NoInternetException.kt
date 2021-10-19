package com.pilltracker.movies.data.network

import okio.IOException

class NoInternetException (errorMessage: String?): IOException(errorMessage)