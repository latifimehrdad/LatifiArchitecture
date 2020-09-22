# LatifiArchitecture

| Used From |
| --- |
| retrofit2 |
| Dagger |
| universalimageloader |
| butterknife |
| calligraphy |
| rxjava2 |
| multidex |



## How To Use?


***Step 1***

Add the JitPack repository to your build file. 
Add it in your root build.gradle at the end of repositories:

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

***Step 2***

Add the dependency

```
dependencies {
	        implementation 'com.github.latifimehrdad:LatifiArchitecture:1.0'
	}
```

***Step 3***

Add this code to build.gradle

```
android {
    ...
    defaultConfig {
        ...
        multiDexEnabled true
    }

    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }

}
```
