# Hello Google Agera
This is a sample project to demonstrate the power of [Agera](https://github.com/google/agera), Reactive Programming for Android.

**Note: This is for learning purposes only, I'm still exploring Agera**

## About the project
> "Act locally, sync globally!" - Adam W. Powell

When a user makes a request for new data (refresh), the user is presented with locally available data 
whiles the app makes an http request to get new data from server. When the request returns the user is presented
with the new data if available and the local copy of the data is updated.

The app fakes the network request by using `Thread.sleep(5000);`.


## Event chain
![Event chain](https://github.com/google/agera/raw/master/doc/images/downstream.png)