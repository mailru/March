# March (🔧️ work-in-progress 🔧️)

It is still in its early stages of development and currently contains only core functionality. It is under heavy development.

## DataChannel
Allow delivering UI changes from *any* thread without thinking about lifecycle.

A tool that allows update UI from your presentation layer and encapsulates handling lifecycle aware behavior of Activity or Fragment.

How it works:

 * send data with *postValue(value: T)*
 * subscribe on channel with invoking *observe(observer: (T) -> Unit)*
 * on destroying Activity/Fragment, channel will unregister all his observers
 * create new channels with *DataChannelFactory*

There are two type of channels - *StateChannel* and *EventChannel*.

### StateChannel

 * subscriber will be notified only with unique values (with creating channel just set rules of comparison, by default is used *equals*)
 * all subscriber will be notified with last state in channel
 * guarantee that subscriber will be notified with *last* state
 * not guarantee that subscriber will be notified with all elements (if state still proceed, that new state will not be emitted)

Assume that will be used in situation like:
 * update text
 * update RecyclerView
 * show/hide view

### EventChannel

 * guarantee that subscriber will be notified with *all* events
 * subscriber will be notified with last *unobserved* event in channel
 * if last event will be observed, new subscriber will not get update on it

Assume that will be used in situation like:

 * show toast
 * show snackbar/dialog etc
 * navigation (like add new fragment or start new activity)

## Interactor

Used for logic implementation and saving current state of UI (domain layer)

### Lifecycle

 * Lifecycle of Interactor is tied on feature screen, not on Activity or Fragment.
 * Handle configuration changes (like changed orientation)
 * on opening feature screen, create() will be invoked, and destroy() on closing

Usage:

 * inherit your class from interface *Interactor*
 * obtain your class through *InteractorObtainer* via method *obtain*
 * method *obtain* accepts lambda as parameter to create an instance of your interactor
 * inside lambda create a new instance of interactor through constructor
 * instance of your interactor will be created only *once* when user will be on feature screen, and will be *reused* on all configuration changes
 * lambda creator gets implementation of *DataChannelFactory* for creating a new *DataChannel* inside your interactor

 ## Integration

 This library is available on **jcenter**, so you need to add this repository to your root build.gradle:

 ```groovy
 allprojects {
     repositories {
         ...
         jcenter()
     }
 }
 ```

 Add one of the following dependency:

 ```groovy
 dependencies {

     //core for java modules
     implementation 'ru.mail.march:core:0.0.1-alpha'

     //core-android for android modules
     implementation 'ru.mail.march:core-android:0.0.1-alpha'
 }
 ```