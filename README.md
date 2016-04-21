# Hello Google Agera
This is a sample project to demonstrate the power of [Agera](https://github.com/google/agera), Reactive Programming for Android.

**Note: This is for learning purposes only, I'm still exploring Agera**

## About the project
> "Act locally, sync globally!" - Adam W. Powell

When a user makes a request for new data, the user is presented with locally available data 
whiles the app makes an http request to get new data from server. When the request returns the user is presented
with the new data if available and the local copy of the data is updated.

The app fakes the network request by using `Thread.sleep(5000);`.


## Event chain
![Event chain](https://github.com/savekirk/hello-google-agera/blob/master/hello_agera.png)

## License

    Copyright 2016 Kirk .S. Agbenyegah

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
