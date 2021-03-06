mocktopus
=========

v 0.6.0
-----

    compile 'com.lacronicus.mocktopus.annotation:mocktopus-annotation:0.6.0'
    compile 'com.lacronicus.mocktopus:mocktopus-core:0.6.0'
    
(follow the patterns in the sample to get going)

This thing is approaching usable. You can look at it now

how to use

1. annotate your model objects
2. inject mocktopus service in place of real service
3. start your app and change stuff in the config screen
4. see results!


todo:
-----

* get a proper gitignore
* error handling 
    * better exception handling
    * get other people to implement the library and see where they screw up
        * make sure these hot spots are noted in the readme
        * make sure these failures have good logging
* full logging support
    * interface parsing
    * object generation
* rotation support in ConfigurationActivity
* support lists of "Primitives" (integers, etc)
* expand on demo reddit api - get a feel for how to integrate with various apis and prove it's capable of doing so
    * story detail
    * user detail
* improve demo 
    * show what a "real" consumer activity would look like
    * move json viewer into another activity
* improve config activity
    * show currently selected value
* improve configuration
    * method options
        * error/success 
    * observable options
        * delay
        * indefinite loading?
* add recursive child limits (if MyObject contains a MyObject, tries to add more forever. depth limit?)
* inheritance support
* private field support
* add callback support (for better retrofit support) - do people use this? kinda leaky iirc
* more annotations?
    * ignore (used to tell mocktopus to ignore certain fields that wont make sense. like if a model contains a Date or a LatLng for convenience reasons
    * string
    * int
* list modder options/settings?
* modder pattern for other objects?
* add some flavors to show how to use next to a "real" api
* label annotations
    * if, for example, your model fields arent "tester readable" you could give them alternate names for display in the config
    * maybe a description instead?


Proposals
---------
        
* add api passthrough (so some methods are mocked, some make real requests)

api passthrough should be an api level setting, it has its own "settings" that adds a new default, "passthrough"

instead of an object builder, you use an object modifier that iterates through 
all the fields in the response and modifies them to be the values set in the settings object, 
ignoring fields set to "passthrough." This would make working with recursive objects easier, 
as well as providing more realistic data.

* how should this deal with recursive objects?
    * brute force depth limit ("works" but not really intelligent)
    * modders for plain objects?
        * "Modders" modify an object after it has been inflated
        * allowing for more customized logic in object building
        * have an "inflation" pass, then a "modification" pass
            * how would this work with passthrough? just ignore them?
            
            
            

* mocktopus "contexts"
    * large apps may use 5+ distinct services, each activity may only use 1-2, provide activities a way to open just relevent services
    
* does it really have to be a singleton?
    * if it's not, there's probably going to be a lot of boilerplate code for a user to write. more work for everyone to enable a use case that I can't think of much less ever actually needed? sounds not worth it
