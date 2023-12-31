package com.example.act4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.act4.Data.DataForm
import com.example.act4.Data.DataObjek.jenis
import com.example.act4.Data.DataObjek.sts
import com.example.act4.ui.theme.Act4Theme
import com.example.act4.ui.theme.CbViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Act4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Layar()
                }
            }
        }
    }
}

@Composable
fun Layar() {
    Card(modifier = Modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 15.dp)) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier =  Modifier
                .padding(10.dp)) {
            TampilForm()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TampilForm(cobaViewModel: CbViewModel = viewModel()){
    var textNama by remember { mutableStateOf("") }
    var textTlp by remember { mutableStateOf("") }
    var textEmail by remember { mutableStateOf("") }
    var textAlamat by remember { mutableStateOf("") }

    val context = LocalContext.current
    val dataForm: DataForm
    val uiState by cobaViewModel.uiState.collectAsState()
    dataForm = uiState

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 7.dp)){
        Button(onClick = {},
            modifier = Modifier) {
        }
        Text(text = "Register",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 120.dp, top = 10.dp)
        )
    }
    Divider(Modifier.fillMaxWidth())
    Text(text = "Create Your Account",
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(5.dp))

    OutlinedTextField(value = textNama,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Username")},
        onValueChange = {
            textNama = it
        }
    )
    OutlinedTextField(value = textTlp,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Telepon")},
        onValueChange = {
            textTlp = it
        }
    )
    OutlinedTextField(value = textEmail,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Email")},
        onValueChange = {
            textEmail = it
        })

    PilihJenis(
        options = jenis.map {id -> context.resources.getString(id)},
        onSelectionChanged = {cobaViewModel.setJenisK(it)})
    PilihStatus(option = sts.map { id -> context.resources.getString(id) },
        onSelectionChanged = {cobaViewModel.setStatusnkh(it)})
    OutlinedTextField(value = textEmail,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Alamat")},
        onValueChange = {
            textAlamat = it
        })
    Button(onClick = {
        cobaViewModel.BacaData(dataForm.sex, dataForm.status, textEmail,textAlamat)
    }
    ) {
        Text(text = stringResource(R.string.submit),
            fontSize = 16.sp)
    }

    Hasil(statnya = cobaViewModel.jenisSt,
        mailnya = cobaViewModel.eMail,
        kindnya = cobaViewModel.jenisKl,
        adrsnya = cobaViewModel.Tempat)
}

@Composable
fun PilihJenis(
    options: List<String>,
    onSelectionChanged: (String) -> Unit = {}
){
    var selectedValue by rememberSaveable { mutableStateOf("") }
    Text(text = "Jenis Kelamin",
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp))
    Row(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()) {
        options.forEach{item ->
            Row(
                modifier = Modifier.selectable(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                ),
                verticalAlignment = CenterVertically,

            ) {
                RadioButton(selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                )
                Text(item)
            }
        }
    }
}
@Composable
fun Hasil(statnya: String, mailnya: String, kindnya: String, adrsnya: String){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Text(text = "Jenis Kelamin: " + kindnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
        Text(text = "Status: " + statnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
        Text(text = "Alamat: " + adrsnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
        Text(text = "Email: " + mailnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
    }
}
@Composable
fun PilihStatus(
    option: List<String>,
    onSelectionChanged: (String) -> Unit
){
    var selectedValue by rememberSaveable { mutableStateOf("") }
    Text(text = "Status", modifier = Modifier
        .fillMaxWidth())
    Row (modifier = Modifier
        .fillMaxWidth()){
        option.forEach{
            item ->
            Row(modifier = Modifier.selectable(
                selected = selectedValue == item,
                onClick = {
                    selectedValue = item
                    onSelectionChanged(item)
                }
            ),
                verticalAlignment = CenterVertically
            ){
                RadioButton(selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                )
                Text(text = item)
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Act4Theme {
        Layar()
    }
}