# UnitedRemoteMobileChallenge

The project was created for the UnitedRemote internship hiring process.

The project is an Android app that list the most starred Github repos that were created in the last 30days.

## Usage

Use Android API 26 or higher.

## Libraries

The app uses the [Github search api](https://developer.github.com/v3/search/#search-repositories) to get the most starred repos.

The app uses [Glide](https://github.com/bumptech/glide) for fetching the avatar.

The app uses [Retrofit](https://github.com/square/retrofit) for calling the endpoints.

## User Interfaces

This part presents the different interfaces that the app takes when requesting the Github API. All the following interfaces belong to the same Fragment.

The Loading screen until the first data page is brought.

<img width="200" height="400" alt="loading frame" src="https://github.com/larhlimiHamza/UnitedRemoteMobileChallenge/blob/master/loading_frame.jpg">

The next screen shows the repo fetched in a single row for each repo.

<img width="200" height="400" alt="loading frame" src="https://github.com/larhlimiHamza/UnitedRemoteMobileChallenge/blob/master/fetching_data.jpg">

The last screen is the bonus part, once all the rows corresponding to the first page are displayed, the app calls another endpoint in order to display another data page without refreshing the fragment.

<img width="200" height="400" alt="loading frame" src="https://github.com/larhlimiHamza/UnitedRemoteMobileChallenge/blob/master/scroll_loading_data.jpg">

## Contact

Contact me on larhlimihamza@gmail.com
