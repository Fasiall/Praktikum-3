package com.faisal.myapplication

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faisal.myapplication.components.InfoItem
import com.faisal.myapplication.components.ProfileCard
import com.faisal.myapplication.components.ProfileHeader
import kotlinx.coroutines.launch
import myapplication.shared.generated.resources.Res
import myapplication.shared.generated.resources.avatar_placeholder
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color(0xFF4F46E5),
            onPrimary = Color.White,
            primaryContainer = Color(0xFFEEF2FF),
            onPrimaryContainer = Color(0xFF312E81),
            secondary = Color(0xFF06B6D4),
            surface = Color.White,
            background = Color(0xFFF8FAFC),
            onSurface = Color(0xFF1E293B),
            onSurfaceVariant = Color(0xFF64748B),
            outline = Color(0xFF94A3B8)
        )
    ) {
        val snackbarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()

        // Profile State (interactive & editable)
        var name by remember { mutableStateOf("Faisal H Sinambela") }
        var role by remember { mutableStateOf("NIM. 124140040 • Mobile Developer") }
        var bio by remember {
            mutableStateOf("Mahasiswa Teknik Informatika yang berfokus pada pengembangan aplikasi mobile modern dengan Kotlin & Compose Multiplatform.")
        }
        var email by remember { mutableStateOf("faisalnambela@gmail.com") }
        var phone by remember { mutableStateOf("+62 812-3456-7890") }
        var location by remember { mutableStateOf("Medan, Sumatera Utara, Indonesia") }

        // Interaction State
        var isFollowing by remember { mutableStateOf(false) }
        var followerCount by remember { mutableStateOf(1420) }
        var showEditDialog by remember { mutableStateOf(false) }

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "My Profile App",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        titleContentColor = MaterialTheme.colorScheme.onSurface
                    )
                )
            },
            snackbarHost = { SnackbarHost(snackbarHostState) },
            containerColor = MaterialTheme.colorScheme.background
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Top Gradient Accent Header Box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFF4F46E5),
                                    Color(0xFF6366F1),
                                    Color(0xFF06B6D4)
                                )
                            )
                        )
                )

                // 1. Reusable Component: ProfileHeader
                Box(
                    modifier = Modifier
                        .offset(y = (-45).dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    ProfileHeader(
                        name = name,
                        role = role,
                        bio = bio,
                        avatarPainter = painterResource(Res.drawable.avatar_placeholder),
                        isOnline = true
                    )
                }

                // Stats Section (Row + Cards + Texts)
                Card(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .offset(y = (-30).dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 14.dp, horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ProfileStatItem(count = "$followerCount", label = "Followers")
                        Box(
                            modifier = Modifier
                                .width(1.dp)
                                .height(32.dp)
                                .background(MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
                        )
                        ProfileStatItem(count = "285", label = "Following")
                        Box(
                            modifier = Modifier
                                .width(1.dp)
                                .height(32.dp)
                                .background(MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
                        )
                        ProfileStatItem(count = "18", label = "Projects")
                    }
                }

                // Action Buttons Row (Button, OutlinedButton, Row)
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .offset(y = (-20).dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Follow Button
                    val followButtonColor by animateColorAsState(
                        if (isFollowing) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.primary
                    )
                    val followTextColor = if (isFollowing) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary

                    Button(
                        onClick = {
                            isFollowing = !isFollowing
                            followerCount += if (isFollowing) 1 else -1
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    if (isFollowing) "Anda sekarang mengikuti $name" else "Berhenti mengikuti $name"
                                )
                            }
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = followButtonColor),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(
                            imageVector = if (isFollowing) Icons.Default.Check else Icons.Default.PersonAdd,
                            contentDescription = null,
                            tint = followTextColor,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isFollowing) "Mengikuti" else "Ikuti",
                            color = followTextColor,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    // Edit Profile Button
                    OutlinedButton(
                        onClick = { showEditDialog = true },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit Profile",
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Edit Profil", fontWeight = FontWeight.SemiBold)
                    }
                }

                // 2. Reusable Component: ProfileCard for Contact Information
                ProfileCard(
                    title = "Informasi Kontak",
                    icon = Icons.Default.ContactMail,
                    modifier = Modifier.offset(y = (-10).dp)
                ) {
                    InfoItem(
                        icon = Icons.Default.Email,
                        label = "Email",
                        value = email,
                        onClick = {
                            scope.launch {
                                snackbarHostState.showSnackbar("Email: $email")
                            }
                        }
                    )

                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)
                    )

                    InfoItem(
                        icon = Icons.Default.Phone,
                        label = "Nomor Telepon",
                        value = phone,
                        onClick = {
                            scope.launch {
                                snackbarHostState.showSnackbar("Telepon: $phone")
                            }
                        }
                    )

                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)
                    )

                    InfoItem(
                        icon = Icons.Default.LocationOn,
                        label = "Lokasi",
                        value = location,
                        onClick = {
                            scope.launch {
                                snackbarHostState.showSnackbar("Lokasi: $location")
                            }
                        }
                    )
                }

                // 3. Reusable Component: ProfileCard for About Me
                ProfileCard(
                    title = "Tentang Saya",
                    icon = Icons.Default.Person,
                    modifier = Modifier.offset(y = (-5).dp)
                ) {
                    Text(
                        text = "Saya adalah mahasiswa yang tertarik dalam rekayasa perangkat lunak multiplatform. " +
                                "Aplikasi profil ini dibangun menggunakan Kotlin Multiplatform dan Jetpack Compose " +
                                "untuk memenuhi Tugas Praktikum Minggu 3.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 22.sp,
                        modifier = Modifier.padding(8.dp)
                    )
                }

                // 4. Reusable Component: ProfileCard for Skills & Badges
                ProfileCard(
                    title = "Keahlian & Teknologi",
                    icon = Icons.Default.Code
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        SkillChip("Kotlin", MaterialTheme.colorScheme.primaryContainer, MaterialTheme.colorScheme.primary)
                        SkillChip("Compose", MaterialTheme.colorScheme.secondaryContainer, MaterialTheme.colorScheme.secondary)
                        SkillChip("Android", Color(0xFFDCFCE7), Color(0xFF16A34A))
                        SkillChip("Git", Color(0xFFFFEDD5), Color(0xFFEA580C))
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        // Interactive Edit Profile Dialog
        if (showEditDialog) {
            EditProfileDialog(
                currentName = name,
                currentRole = role,
                currentBio = bio,
                currentPhone = phone,
                currentLocation = location,
                onDismiss = { showEditDialog = false },
                onSave = { newName, newRole, newBio, newPhone, newLocation ->
                    name = newName
                    role = newRole
                    bio = newBio
                    phone = newPhone
                    location = newLocation
                    showEditDialog = false
                    scope.launch {
                        snackbarHostState.showSnackbar("Profil berhasil diperbarui!")
                    }
                }
            )
        }
    }
}

/**
 * Reusable stat item showing counter number and title.
 */
@Composable
private fun ProfileStatItem(count: String, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = count,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.outline
        )
    }
}

/**
 * Reusable skill badge/chip component using Box and Text.
 */
@Composable
private fun SkillChip(label: String, containerColor: Color, textColor: Color) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(containerColor)
            .padding(horizontal = 12.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color = textColor
        )
    }
}

/**
 * Dialog for editing profile information interactively.
 */
@Composable
private fun EditProfileDialog(
    currentName: String,
    currentRole: String,
    currentBio: String,
    currentPhone: String,
    currentLocation: String,
    onDismiss: () -> Unit,
    onSave: (name: String, role: String, bio: String, phone: String, location: String) -> Unit
) {
    var editName by remember { mutableStateOf(currentName) }
    var editRole by remember { mutableStateOf(currentRole) }
    var editBio by remember { mutableStateOf(currentBio) }
    var editPhone by remember { mutableStateOf(currentPhone) }
    var editLocation by remember { mutableStateOf(currentLocation) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Edit Profil", fontWeight = FontWeight.Bold)
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = editName,
                    onValueChange = { editName = it },
                    label = { Text("Nama") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = editRole,
                    onValueChange = { editRole = it },
                    label = { Text("Profesi / Subtitle") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = editBio,
                    onValueChange = { editBio = it },
                    label = { Text("Bio") },
                    maxLines = 3,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = editPhone,
                    onValueChange = { editPhone = it },
                    label = { Text("Telepon") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = editLocation,
                    onValueChange = { editLocation = it },
                    label = { Text("Lokasi") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onSave(editName, editRole, editBio, editPhone, editLocation)
                }
            ) {
                Text("Simpan")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Batal")
            }
        }
    )
}