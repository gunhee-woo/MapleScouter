package com.example.maplescouter.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.maplescouter.ui.theme.MapleScouterTheme
import com.example.maplescouter.viewmodel.DetailViewModel
import com.maplescouter.domain.model.ApiResult
import com.maplescouter.domain.model.CharacterBasic
import com.maplescouter.domain.model.CharacterInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    name: String?,
    onBackClick: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val characterBasicState by viewModel.characterBasicState.collectAsState()

    LaunchedEffect(name) {
        name?.let {
            viewModel.fetchCharacterBasic(it)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("캐릭터 상세 정보") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "뒤로가기")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (val result = characterBasicState) {
                is ApiResult.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is ApiResult.Success -> {
                    DetailContent(characterBasic = result.data)
                }
                is ApiResult.Error -> {
                    Text(
                        text = "에러 발생: ${result.message}",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun DetailContent(characterBasic: CharacterBasic) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "이름: ${characterBasic.name}", style = MaterialTheme.typography.headlineMedium)
        Text(text = "월드: ${characterBasic.worldName}", modifier = Modifier.padding(top = 8.dp))
        Text(text = "직업: ${characterBasic.className}")
        Text(text = "레벨: ${characterBasic.level}")
        Text(text = "OCID: ${characterBasic.ocid}", style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(top = 16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    MapleScouterTheme {
        DetailContent(
            characterBasic = CharacterBasic(
                name = "데몬슬레이어",
                world = "루나",
                job = "데몬슬레이어",
                level = 285
            )
        )
    }
}
