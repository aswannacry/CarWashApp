package com.securiforce.carwash

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.securiforce.carwash.linkedlist.LinkedList
import com.securiforce.carwash.ui.theme.CarWashTheme
import java.util.*
import kotlin.collections.ArrayList

var test = mutableStateOf(0)
var ticket_list = java.util.LinkedList<String>()
private var extraText = ""

var peekCar = ""
var re = ""
var firstCar = ""
var ticket = ""

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarWashTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavigationItem()
                }
            }
        }
    }
}

fun Queue(){

if (!ticket_list.isEmpty()){
////    peekCar = ticket_list.peekFirst()
    firstCar = ticket_list.first
////    re = ticket_list.removeFirst()
}

    if(firstCar.equals(null)){
        firstCar = ticket_list.first

    }else{
        object : CountDownTimer(5_000,1_000){
            override fun onTick(remaining: Long) {
                extraText = "Currently Wash"
            }

            override fun onFinish() {
                extraText = "Done Wash"
                ticket_list.removeFirst()
            }

        }
    }
//    println("TEST $re")
//    println("TEST $ticket_list")
//    println("TEST $asd")

}

@Composable
fun Home(){
//    var test = 0
    test = remember {
        mutableStateOf(0)
    }
    val showDialogWashCar = remember{ mutableStateOf(false)}
    val showDialogPickUp = remember { mutableStateOf(false)}

//    val txtFieldError = remember { mutableStateOf("") }
    var txtField by remember { mutableStateOf(TextFieldValue("")) }

//    val focusManager = LocalFocusManager.current

    Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /*******************************************************/
//        val listState = rememberLazyListState()
//        LazyColumn{
//            items(ticket_list.size+1) { ticket ->
//                if (ticket == 0){
//                    Text("")
//                }else {
//                    Text(ticket.toString())
//                }
//            }
//        }
        /*******************************************/
        Spacer(modifier = Modifier.padding(vertical = 100.dp))
        Button(onClick = {
            //your onclick code here
            test.value++
            ticket_list.add(test.value.toString())
            Queue()

            showDialogWashCar.value = true
        }) {
            Text(text = "Wash Car")
        }

        Spacer(modifier = Modifier.padding(vertical = 25.dp))
        TextField(

            value = txtField,
            label = { Text(text = "Ticket Number") },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFFFFFFF)
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { it ->
                txtField = it
            }
        )


        Button(onClick = {

            showDialogPickUp.value = true
        }) {
            Text(text = "Pick up Car")
        }

        if (showDialogWashCar.value){

            AlertDialog(
                onDismissRequest = { showDialogWashCar.value = false },
                title = {Text(text = "Ticket Number",)},
                text = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
//                        test = rememberSaveable {
//                            mutableStateOf(test.value)
//                        }
                        Text(
                            text = test.value.toString(),
                            fontSize = 30.sp
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = { showDialogWashCar.value = false },
//                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF0D2E58))
                    ) {
                        Text(text = "Okay")
                    }
                }
            )

        }

        if (showDialogPickUp.value){

            AlertDialog(
                onDismissRequest = { showDialogPickUp.value = false },
                title = {Text(text = "Pick Up")},
                text = {if (txtField.equals(ticket)){Text(text = "Thank You")}else{Text(text = "Not Finish Yet")} },
                confirmButton = {
                    Button(
                        onClick = { showDialogPickUp.value = false },
//                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF0D2E58))
                    ) {
                        Text(text = "Okay")
                    }
                }
            )
        }


    }
}

@Composable
fun CheckCar(){

    Column(modifier = Modifier.fillMaxSize()
        .padding(start = 20.dp, top = 20.dp),
        verticalArrangement = Arrangement.Top,
//        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LazyColumn{
            Queue()
            items(ticket_list) { ticket->
                    if(firstCar == ticket){
                        Text("$ticket($extraText)")
                    }else{
                        Text("$ticket(Waiting)")
                    }


            }
        }
    }
}

@Composable
fun NavigationController(navHostController: NavHostController){
    
    NavHost(navController = navHostController, startDestination =  NavigationItem.Home.route ){
        
        composable(NavigationItem.Home.route){
            Home()
        }
        composable(NavigationItem.ListCar.route){
            CheckCar()
        }
    }
    
}

@Composable
fun NavigationItem() {

    val navController = rememberNavController()
    val items = listOf(NavigationItem.Home, NavigationItem.ListCar)

    Scaffold(topBar = { TopAppBar(title = { Text(text = "Car Wash App") }) },
        bottomBar = {

            BottomNavigation(backgroundColor = MaterialTheme.colors.background) {

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach {
                    BottomNavigationItem(selected = currentRoute == it.route,
                        label = {
                            Text(
                                text = it.label,
                                color = if (currentRoute == it.route) Color.DarkGray else Color.LightGray
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = it.icons,
                                contentDescription = null,
                                tint =
                                if (currentRoute == it.route) Color.DarkGray else Color.LightGray
                            )
                        },

                        onClick = {
                            if (currentRoute != it.route) {
                                navController.graph?.startDestinationRoute?.let {
                                    navController.popBackStack(it, true)
                                }

                                navController.navigate(it.route) {
                                    launchSingleTop = true
                                }

                            }
                        })
                }

            }
        }) {
        NavigationController(navHostController = navController)
    }
}


//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!")
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    CarWashTheme {
//        Home()
//    }
//}