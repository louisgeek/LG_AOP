# LG_AOP



1 project gradle 

```groovy
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
}
```

 



2 project gradle

```groovy
 dependencies {
        classpath "com.android.tools.build:gradle:4.1.2"
		//add
        classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.10'

}
```





3 module gradle

```groovy
plugins {
    id 'com.android.application'
    //add
    id 'android-aspectjx'
}

```



4 module gradle [![](https://jitpack.io/v/louisgeek/LG_AOP.svg)](https://jitpack.io/#louisgeek/LG_AOP)

```groovy
dependencies {
	...
	//add 
    implementation 'com.github.louisgeek:LG_AOP:x.x.x'
}
```



使用

```java
id_tv_ccc.setOnClickListener(new View.OnClickListener() {
         @ThrottleClick
         @Override
         public void onClick(View v) {
                sss();
         }
});
//
  @CheckNetwork(tip = "无网络")
    private void sss() {
        Log.e(TAG, "sss: ");
//        Toast.makeText(this, "sss", Toast.LENGTH_SHORT).show();
}
```

