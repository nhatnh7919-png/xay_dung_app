package com.example.xay_dung_app
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppNavigation()
            }
        }
    }
}

// ==========================================
// 1. SETUP NAVIGATION[cite: 1]
// ==========================================
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("list") { ComponentsListScreen(navController) }
        composable("text_detail") { TextDetailScreen(navController) }
        composable("image_detail") { ImageDetailScreen(navController) }
        composable("textfield_detail") { TextFieldDetailScreen(navController) }
        composable("row_layout") { RowLayoutScreen(navController) }
    }
}

// ==========================================
// 2. MÀN HÌNH WELCOME[cite: 1]
// ==========================================
@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Thay R.drawable.ic_launcher_foreground bằng logo của bạn
        Box(modifier = Modifier.size(100.dp).background(Color.LightGray)) // Placeholder logo
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Jetpack Compose", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { navController.navigate("list") },
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text("I'm ready")
        }
    }
}

// ==========================================
// 3. MÀN HÌNH DANH SÁCH COMPONENTS
// ==========================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentsListScreen(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("UI Components List", color = Color.Blue) }) }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding).padding(horizontal = 16.dp)) {
            item { SectionTitle("Display") }
            item { ComponentItem("Text", "Displays text", Color(0xFFBBDEFB)) { navController.navigate("text_detail") } }
            item { ComponentItem("Image", "Displays an image", Color(0xFFBBDEFB)) { navController.navigate("image_detail") } }

            item { SectionTitle("Input") }
            item { ComponentItem("TextField", "Input field for text", Color(0xFFBBDEFB)) { navController.navigate("textfield_detail") } }
            item { ComponentItem("PasswordField", "Input field for passwords", Color(0xFFBBDEFB)) { } }

            item { SectionTitle("Layout") }
            item { ComponentItem("Column", "Arranges elements vertically", Color(0xFFBBDEFB)) { } }
            item { ComponentItem("Row", "Arranges elements horizontally", Color(0xFFBBDEFB)) { navController.navigate("row_layout") } }

            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { ComponentItem("Tự tìm hiểu", "Tìm ra tất cả các thành phần UI Cơ bản", Color(0xFFFFCDD2)) { } }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(text = title, fontWeight = FontWeight.Bold, modifier = Modifier.padding(vertical = 8.dp))
}

@Composable
fun ComponentItem(title: String, subtitle: String, bgColor: Color, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp).clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = bgColor)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, fontWeight = FontWeight.Bold)
            Text(text = subtitle, fontSize = 12.sp, color = Color.DarkGray)
        }
    }
}

// ==========================================
// 4. MÀN HÌNH TEXT DETAIL[cite: 1, 2]
// ==========================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextDetailScreen(navController: NavController) {
    Scaffold(
        topBar = { TopBarWithBack(navController, "Text Detail") }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize(), contentAlignment = Alignment.Center) {
            val annotatedText = buildAnnotatedString {
                append("The ")
                withStyle(SpanStyle(textDecoration = TextDecoration.LineThrough)) { append("quick ") }
                withStyle(SpanStyle(color = Color(0xFF8B4513), fontSize = 28.sp)) { append("Brown\n") }
                append("fox ")
                withStyle(SpanStyle(fontStyle = FontStyle.Italic)) { append("j u m p s ") }
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append("over\n") }
                withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) { append("the ") }
                withStyle(SpanStyle(fontStyle = FontStyle.Italic)) { append("lazy ") }
                append("dog.")
            }
            Text(text = annotatedText, fontSize = 24.sp)
        }
    }
}

// ==========================================
// 5. MÀN HÌNH IMAGES
// ==========================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageDetailScreen(navController: NavController) {
    Scaffold(
        topBar = { TopBarWithBack(navController, "Images") }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            // Ảnh từ Internet
            AsyncImage(
                model = "https://example.com/your-image-url.jpg", // Thay URL thật của UTH vào đây
                contentDescription = "URL Image",
                modifier = Modifier.fillMaxWidth().height(150.dp).clip(RoundedCornerShape(8.dp))
            )
            Text("URL link", fontSize = 10.sp, color = Color.Gray, modifier = Modifier.padding(vertical = 8.dp))

            Spacer(modifier = Modifier.height(16.dp))

            // Ảnh Local (Cần chép 1 ảnh vào thư mục res/drawable và đặt tên là local_image)
            Box(modifier = Modifier.fillMaxWidth().height(150.dp).background(Color.LightGray)) {
                // Image(painter = painterResource(id = R.drawable.local_image), contentDescription = "Local")
            }
            Text("In app", fontSize = 12.sp, modifier = Modifier.padding(top = 8.dp))
        }
    }
}

// ==========================================
// 6. MÀN HÌNH TEXTFIELD[cite: 2]
// ==========================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldDetailScreen(navController: NavController) {
    var text by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopBarWithBack(navController, "TextField") }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp).fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Thông tin nhập:") },
                shape = RoundedCornerShape(16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Tự động cập nhật dữ liệu theo textfield", color = Color.Red)
        }
    }
}

// ==========================================
// 7. MÀN HÌNH ROW LAYOUT[cite: 2]
// ==========================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowLayoutScreen(navController: NavController) {
    Scaffold(
        topBar = { TopBarWithBack(navController, "Row Layout") }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp)) {
            val colors = listOf(Color(0xFF90CAF9), Color(0xFF42A5F5), Color(0xFF1E88E5))
            for (i in 0..3) {
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
                    for (j in 0..2) {
                        Box(modifier = Modifier.size(80.dp, 50.dp).clip(RoundedCornerShape(8.dp)).background(colors[j]))
                    }
                }
            }
        }
    }
}

// Component hỗ trợ TopBar có nút Back dùng chung
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithBack(navController: NavController, title: String) {
    TopAppBar(
        title = { Text(title, color = Color.Blue) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Blue)
            }
        }
    )
}