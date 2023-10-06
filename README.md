# Powerful Android Validator Crawaler⚔
Validator box library that can inspect any type of form, provides multiple validation functions with an inclusion of clearing views(❁´◡`❁)

[![Build Status](https://travis-ci.com/AliAzaz/AndroidValidatorCrawler.svg?branch=master)](https://travis-ci.com/AliAzaz/AndroidValidatorCrawler) [![](https://jitpack.io/v/AliAzaz/AndroidValidatorCrawler.svg)](https://jitpack.io/#AliAzaz/AndroidValidatorCrawler) [![License: MIT](https://img.shields.io/badge/License-MIT-brightgreen.svg)](https://opensource.org/licenses/MIT) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Powerful%20Android%20Validator%20Crawaler-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/8077) 

Validator Crawler, that would help the developers to get two different functionalities i.e validate and clear.

Get started from validation, this library provides multiple functionality having 10+ validate functions. The user have an authority to validate whole form or even single view, procedure is described below. 
The view includes:
- Edittext
- RadioGroup
- Checkbox (include Multi checklist)
- Spinner

Secondly, it also provides multiple functions  to clear whole form views or single view that are filled. The clearing functionality is work for all above described views including:
- Switch (Toggle)

## Description:✌
Checkout the core functionality from here: ***[AndroidValidatorCrawaler](https://proandroiddev.com/powerful-android-validator-crawaler-9a72bd9917d5)***

## Some Screens🎞 

<img alt="Pic-1" src="https://github.com/AliAzaz/AndroidValidatorCrawler/blob/master/pictures/pic01.png" width="200"/> <img alt="Pic-2" src="https://github.com/AliAzaz/AndroidValidatorCrawler/blob/master/pictures/pic02.png" width="200"/> <img alt="Pic-3" src="https://github.com/AliAzaz/AndroidValidatorCrawler/blob/master/pictures/pic03.png" width="200"/>

## How to use it??

### Implementation🧨
In project.gradle add this code it in root build.gradle at the end of repositories:
```sh
dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
```

Now, add the dependency in app.gradle:
```sh
dependencies {
    implementation 'com.github.AliAzaz:AndroidValidatorCrawler:X.X.X'
}
```

### Sample Usage🔔
Implement Validation:

    //fldGrpSecA01: is the view group in which whole layout is defined in xml
    Validator.emptyCheckingContainer(this, fldGrpSecA01);


Implement Clear:

    //fldGrpSecA01: is the view group in which whole layout is defined in xml
    Clear.clearAllFields(fldGrpSecA01);

## OUTPUT📇
```sh
Removed required error tag from RadioGroup
```

![](https://github.com/AliAzaz/AndroidValidatorCrawler/blob/master/output.gif)


## CONNECT👍
Medium: https://medium.com/@ali.azaz.alam

Twitter: https://twitter.com/AliAzazAlam1

Github: https://github.com/aliazaz

LinkedIn: https://www.linkedin.com/in/aliazazalam/

## LICENSE📃
----
Distributed under the MIT license. See [LICENSE](https://github.com/AliAzaz/AndroidValidatorCrawler/blob/master/LICENSE) information.
