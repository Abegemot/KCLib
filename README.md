# KCLib
Testing Jetpack Compose.

For testing compose I've took an App from 2017. I started with the very first activity that shows up, the clasical Login.

<pre>


class Loggin:KBaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { LoginUi()}
    }
    fun oKLogin(){
        setResult(Activity.RESULT_CANCELED)
        finish()
    }
    fun oKRegister(){
        setResult(Activity.RESULT_OK)
        finish()
    }
}

@Preview2  
@Composable fun LoginUi() {
    val kt = +state{kTheme.LIGHT}
    val ctx=+ambient(ContextAmbient)
    val s1=+state{"field 1"}
    val s2=+state{"field 2"}
    KWindow(kt.value,270){
       KHeader("Login"){ kt.value = kTheme.next(kt.value)}
       KField("email",s1)
       Kline()
       KField("password",s2)
       KButtonBar() {
           Button(text = "Loggin", onClick = { Toast.makeText(ctx, "ok Login  field1 $s1 field2 $s2", Toast.LENGTH_SHORT).show(),(ctx as Loggin).oKLogin()})
           Button(text = "Register" ,onClick = { Toast.makeText(ctx, "ok Register", Toast.LENGTH_SHORT).show()})
       }
   }
}
</pre>
Witch amounts to this: </br></br>  <img src="https://github.com/Abegemot/KCLib/blob/master/kclib/src/main/res/mipmap-hdpi/KLogin.png" width="196"/>


Making a separate Android lib allows you to keep application and source code as much as possible separated reducing the amount 
of dependencies and includes, when new jetpack compose version will launch, by updating the lib you will get the app updated too.
</br>
[making android library ](https://code.luasoftware.com/tutorials/android/create-android-library-to-be-shared-with-multiple-projects/)

So far the library it's just a toy tool to test jetpack compose, it shows how easily useful components are made and how you can achieve a nice 
decoupling between model and ui, at least compared to the present situation.

Next step will be to encompas the rest of stuff model, livedata ... whatever, and of course cope with the changes of the new upcoming dev04.







